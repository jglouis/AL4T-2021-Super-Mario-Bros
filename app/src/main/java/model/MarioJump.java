package model;

import manager.GameEngine;
import model.hero.Mario;

public class MarioJump implements Command {
    private final GameEngine gameEngine;
    private final Mario mario;

    public MarioJump(GameEngine gameEngine, Mario mario) {
        this.gameEngine = gameEngine;
        this.mario = mario;
    }

    @Override
    public void execute() {
        mario.jump(gameEngine);
    }
}
