package manager;

import dagger.Lazy;
import model.MarioJump;
import model.MarioMoveLeft;
import model.MarioMoveRight;
import model.hero.Mario;
import view.ImageLoader;
import view.StartScreenSelection;
import view.UIManager;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.*;
import java.awt.*;

@Singleton
public class GameEngine implements Runnable {

    private final static int WIDTH = 1268, HEIGHT = 708;

    private final MapManager mapManager;
    @Inject
    Lazy<UIManager> uiManager;
    private final SoundManager soundManager;
    @Inject
    Lazy<InputManager> inputManager;
    private final ImageLoader imageLoader;

    private GameStatus gameStatus;
    private boolean isRunning;
    private Camera camera;
    private Thread thread;
    private StartScreenSelection startScreenSelection = StartScreenSelection.START_GAME;
    private int selectedMap = 0;

    @Inject
    public GameEngine(Camera camera, MapManager mapManager, SoundManager soundManager, ImageLoader imageLoader) {
        this.mapManager = mapManager;
        this.soundManager = soundManager;
        this.imageLoader = imageLoader;
        this.camera = camera;
    }

    public synchronized void start() {
        if (isRunning)
            return;

        gameStatus = GameStatus.START_SCREEN;


        JFrame frame = new JFrame("Super Mario Bros.");
        frame.add(this.uiManager.get());
        frame.addKeyListener(inputManager.get());
        frame.addMouseListener(inputManager.get());
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    private void reset() {
        resetCamera();
        setGameStatus(GameStatus.START_SCREEN);
    }

    public void resetCamera() {
        camera = new Camera();
        soundManager.restartBackground();
    }

    public void selectMapViaMouse() {
        String path = uiManager.get().selectMapViaMouse(uiManager.get().getMousePosition());
        if (path != null) {
            createMap(path);
        }
    }

    public void selectMapViaKeyboard() {
        String path = uiManager.get().selectMapViaKeyboard(selectedMap);
        if (path != null) {
            createMap(path);
        }
    }

    public void changeSelectedMap(boolean up) {
        selectedMap = uiManager.get().changeSelectedMap(selectedMap, up);
    }

    private void createMap(String path) {
        boolean loaded = mapManager.createMap(path);
        if (loaded) {
            setGameStatus(GameStatus.RUNNING);
            soundManager.restartBackground();
        } else
            setGameStatus(GameStatus.START_SCREEN);
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();

        while (isRunning && !thread.isInterrupted()) {

            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                if (gameStatus == GameStatus.RUNNING) {
                    gameLoop();
                }
                delta--;
            }
            render();

            if (gameStatus != GameStatus.RUNNING) {
                timer = System.currentTimeMillis();
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                mapManager.updateTime();
            }
        }
    }

    private void render() {
        uiManager.get().repaint();
    }

    private void gameLoop() {
        updateLocations();
        checkCollisions();
        updateCamera();

        if (isGameOver()) {
            setGameStatus(GameStatus.GAME_OVER);
        }

        int missionPassed = passMission();
        if (missionPassed > -1) {
            mapManager.acquirePoints(missionPassed);
            //setGameStatus(GameStatus.MISSION_PASSED);
        } else if (mapManager.endLevel())
            setGameStatus(GameStatus.MISSION_PASSED);
    }

    private void updateCamera() {
        Mario mario = mapManager.getMario();
        double marioVelocityX = mario.getVelX();
        double shiftAmount = 0;

        if (marioVelocityX > 0 && mario.getX() - 600 > camera.getX()) {
            shiftAmount = marioVelocityX;
        }

        camera.moveCam(shiftAmount, 0);
    }

    private void updateLocations() {
        mapManager.updateLocations();
    }

    private void checkCollisions() {
        mapManager.checkCollisions(this);
    }

    public void receiveInput(ButtonAction input) {

        if (gameStatus == GameStatus.START_SCREEN) {
            if (input == ButtonAction.SELECT && startScreenSelection == StartScreenSelection.START_GAME) {
                startGame();
            } else if (input == ButtonAction.SELECT && startScreenSelection == StartScreenSelection.VIEW_ABOUT) {
                setGameStatus(GameStatus.ABOUT_SCREEN);
            } else if (input == ButtonAction.SELECT && startScreenSelection == StartScreenSelection.VIEW_HELP) {
                setGameStatus(GameStatus.HELP_SCREEN);
            } else if (input == ButtonAction.GO_UP) {
                selectOption(true);
            } else if (input == ButtonAction.GO_DOWN) {
                selectOption(false);
            }
        } else if (gameStatus == GameStatus.MAP_SELECTION) {
            if (input == ButtonAction.SELECT) {
                selectMapViaKeyboard();
            } else if (input == ButtonAction.GO_UP) {
                changeSelectedMap(true);
            } else if (input == ButtonAction.GO_DOWN) {
                changeSelectedMap(false);
            }
        } else if (gameStatus == GameStatus.RUNNING) {
            Mario mario = mapManager.getMario();
            if (input == ButtonAction.JUMP) {
                new MarioJump(this, mario).execute();
            } else if (input == ButtonAction.M_RIGHT) {
                new MarioMoveRight(mario, camera).execute();
            } else if (input == ButtonAction.M_LEFT) {
                new MarioMoveLeft(mario, camera).execute();
            } else if (input == ButtonAction.ACTION_COMPLETED) {
                mario.setVelX(0);
            } else if (input == ButtonAction.FIRE) {
                mapManager.fire(this);
            } else if (input == ButtonAction.PAUSE_RESUME) {
                pauseGame();
            }
        } else if (gameStatus == GameStatus.PAUSED) {
            if (input == ButtonAction.PAUSE_RESUME) {
                pauseGame();
            }
        } else if (gameStatus == GameStatus.GAME_OVER && input == ButtonAction.GO_TO_START_SCREEN) {
            reset();
        } else if (gameStatus == GameStatus.MISSION_PASSED && input == ButtonAction.GO_TO_START_SCREEN) {
            reset();
        }

        if (input == ButtonAction.GO_TO_START_SCREEN) {
            setGameStatus(GameStatus.START_SCREEN);
        }
    }

    private void selectOption(boolean selectUp) {
        startScreenSelection = startScreenSelection.select(selectUp);
    }

    private void startGame() {
        if (gameStatus != GameStatus.GAME_OVER) {
            setGameStatus(GameStatus.MAP_SELECTION);
        }
    }

    private void pauseGame() {
        if (gameStatus == GameStatus.RUNNING) {
            setGameStatus(GameStatus.PAUSED);
            soundManager.pauseBackground();
        } else if (gameStatus == GameStatus.PAUSED) {
            setGameStatus(GameStatus.RUNNING);
            soundManager.resumeBackground();
        }
    }

    public void shakeCamera() {
        camera.shakeCamera();
    }

    private boolean isGameOver() {
        if (gameStatus == GameStatus.RUNNING)
            return mapManager.isGameOver();
        return false;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public StartScreenSelection getStartScreenSelection() {
        return startScreenSelection;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getScore() {
        return mapManager.getScore();
    }

    public int getRemainingLives() {
        return mapManager.getRemainingLives();
    }

    public int getCoins() {
        return mapManager.getCoins();
    }

    public int getSelectedMap() {
        return selectedMap;
    }

    public void drawMap(Graphics2D g2) {
        mapManager.drawMap(g2);
    }

    public Point getCameraLocation() {
        return new Point((int) camera.getX(), (int) camera.getY());
    }

    private int passMission() {
        return mapManager.passMission();
    }

    public void playCoin() {
        soundManager.playCoin();
    }

    public void playOneUp() {
        soundManager.playOneUp();
    }

    public void playSuperMushroom() {
        soundManager.playSuperMushroom();
    }

    public void playMarioDies() {
        soundManager.playMarioDies();
    }

    public void playJump() {
        soundManager.playJump();
    }

    public void playFireFlower() {
        soundManager.playFireFlower();
    }

    public void playFireball() {
        soundManager.playFireball();
    }

    public void playStomp() {
        soundManager.playStomp();
    }

    public MapManager getMapManager() {
        return mapManager;
    }

    public int getRemainingTime() {
        return mapManager.getRemainingTime();
    }
}
