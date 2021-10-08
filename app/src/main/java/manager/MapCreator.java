package manager;

import model.EndFlag;
import model.brick.*;
import model.enemy.*;
import model.game.DifficultySettings;
import model.prize.*;
import view.ImageLoader;
import model.Map;
import model.hero.Mario;

import java.awt.*;
import java.awt.image.BufferedImage;

class MapCreator {

    private ImageLoader imageLoader;

    private BufferedImage backgroundImage;
    private BufferedImage superMushroom, oneUpMushroom, fireFlower, coin;
    private BufferedImage ordinaryBrick, surpriseBrick, groundBrick, pipe;
    private final BufferedImage endFlag;

    private final EnemyFactory enemyFactory;

    private final DifficultySettings difficultySettings = DifficultySettings.NORMAL;

    MapCreator(ImageLoader imageLoader) {

        this.imageLoader = imageLoader;
        BufferedImage spriteSheet = imageLoader.loadImage("/sprite.png");

        this.backgroundImage = imageLoader.loadImage("/background.png");
        this.superMushroom = imageLoader.getSubImage(spriteSheet, 2, 5, 48, 48);
        this.oneUpMushroom = imageLoader.getSubImage(spriteSheet, 3, 5, 48, 48);
        this.fireFlower = imageLoader.getSubImage(spriteSheet, 4, 5, 48, 48);
        this.coin = imageLoader.getSubImage(spriteSheet, 1, 5, 48, 48);
        this.ordinaryBrick = imageLoader.getSubImage(spriteSheet, 1, 1, 48, 48);
        this.surpriseBrick = imageLoader.getSubImage(spriteSheet, 2, 1, 48, 48);
        this.groundBrick = imageLoader.getSubImage(spriteSheet, 2, 2, 48, 48);
        this.pipe = imageLoader.getSubImage(spriteSheet, 3, 1, 96, 96);
        this.endFlag = imageLoader.getSubImage(spriteSheet, 5, 1, 48, 48);

        enemyFactory = new EnemyFactory(imageLoader, spriteSheet);
    }

    Map createMap(String mapPath, double timeLimit) {
        BufferedImage mapImage = imageLoader.loadImage(mapPath);

        if (mapImage == null) {
            System.out.println("Given path is invalid...");
            return null;
        }

//        Map createdMap = new Map(timeLimit, backgroundImage);
        String[] paths = mapPath.split("/");
        Map.Builder mapBuilder = new Map.Builder()
                .setPath(paths[paths.length - 1])
                .setRemainingTime(timeLimit)
                .setBackgroundImage(backgroundImage);


        int pixelMultiplier = 48;

        int mario = new Color(160, 160, 160).getRGB();
        int ordinaryBrick = new Color(0, 0, 255).getRGB();
        int surpriseBrick = new Color(255, 255, 0).getRGB();
        int groundBrick = new Color(255, 0, 0).getRGB();
        int pipe = new Color(0, 255, 0).getRGB();
        int goomba = new Color(0, 255, 255).getRGB();
        int koopa = new Color(255, 0, 255).getRGB();
        int end = new Color(160, 0, 160).getRGB();

        for (int x = 0; x < mapImage.getWidth(); x++) {
            for (int y = 0; y < mapImage.getHeight(); y++) {

                int currentPixel = mapImage.getRGB(x, y);
                int xLocation = x * pixelMultiplier;
                int yLocation = y * pixelMultiplier;

                if (currentPixel == ordinaryBrick) {
                    Brick brick = new OrdinaryBrick(xLocation, yLocation, this.ordinaryBrick);
                    mapBuilder.addBrick(brick);
                } else if (currentPixel == surpriseBrick) {
                    Prize prize = generateRandomPrize(xLocation, yLocation);
                    Brick brick = new SurpriseBrick(xLocation, yLocation, this.surpriseBrick, prize);
                    mapBuilder.addBrick(brick);
                } else if (currentPixel == pipe) {
                    Brick brick = new Pipe(xLocation, yLocation, this.pipe);
                    mapBuilder.addGroundBrick(brick);
                } else if (currentPixel == groundBrick) {
                    Brick brick = new GroundBrick(xLocation, yLocation, this.groundBrick);
                    mapBuilder.addGroundBrick(brick);
                } else if (currentPixel == goomba) {
                    Enemy enemy = enemyFactory.create(EnemyType.GOOMBA, xLocation, yLocation, difficultySettings);
                    mapBuilder.addEnemy(enemy);
                } else if (currentPixel == koopa) {
                    Enemy enemy = enemyFactory.create(EnemyType.KOOPA_TROOPA, xLocation, yLocation, difficultySettings);
                    mapBuilder.addEnemy(enemy);
                } else if (currentPixel == mario) {
                    Mario marioObject = new Mario(xLocation, yLocation);
                    mapBuilder.setMario(marioObject);
                } else if (currentPixel == end) {
                    EndFlag endPoint = new EndFlag(xLocation + 24, yLocation, endFlag);
                    mapBuilder.setEndPoint(endPoint);
                }
            }
        }

        System.out.println("Map is created..");
        return mapBuilder.build();
    }

    private Prize generateRandomPrize(double x, double y) {
        Prize generated;
        int random = (int) (Math.random() * 12);

        if (random == 0) { //super mushroom
            generated = new SuperMushroom(x, y, this.superMushroom);
        } else if (random == 1) { //fire flower
            generated = new FireFlower(x, y, this.fireFlower);
        } else if (random == 2) { //one up mushroom
            generated = new OneUpMushroom(x, y, this.oneUpMushroom);
        } else { //coin
            generated = new Coin(x, y, this.coin, 50);
        }

        return generated;
    }


}
