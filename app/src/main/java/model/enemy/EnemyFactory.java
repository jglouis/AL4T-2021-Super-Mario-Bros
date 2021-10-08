package model.enemy;

import model.game.DifficultySettings;
import view.ImageLoader;

import java.awt.image.BufferedImage;

public class EnemyFactory {

    private final BufferedImage goombaLeft, goombaRight, koopaLeft, koopaRight;

    public EnemyFactory(ImageLoader imageLoader, BufferedImage spriteSheet) {
        this.goombaLeft = imageLoader.getSubImage(spriteSheet, 2, 4, 48, 48);
        this.goombaRight = imageLoader.getSubImage(spriteSheet, 5, 4, 48, 48);
        this.koopaLeft = imageLoader.getSubImage(spriteSheet, 1, 3, 48, 64);
        this.koopaRight = imageLoader.getSubImage(spriteSheet, 4, 3, 48, 64);

    }

    public Enemy create(EnemyType type, int xLocation, int yLocation, DifficultySettings difficultySettings) {
        switch (type) {
            case GOOMBA -> {
                Goomba enemy = new Goomba(xLocation, yLocation, this.goombaLeft);
                enemy.setRightImage(goombaRight);
                if (difficultySettings.equals(DifficultySettings.HARD)) {
                    enemy.setVelX(2);
                }
                return enemy;
            }
            case KOOPA_TROOPA -> {
                KoopaTroopa enemy = new KoopaTroopa(xLocation, yLocation, this.koopaLeft);
                enemy.setRightImage(koopaRight);
                if (difficultySettings.equals(DifficultySettings.HARD)) {
                    enemy.setVelX(3);
                }
                return enemy;
            }
            default -> throw new IllegalArgumentException("Unknown enemy");
        }
    }
}
