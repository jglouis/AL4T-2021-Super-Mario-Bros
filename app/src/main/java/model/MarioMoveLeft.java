package model;

import manager.Camera;
import model.hero.Mario;

public class MarioMoveLeft implements Command {
    private final Mario mario;
    private final Camera camera;

    public MarioMoveLeft(Mario mario, Camera camera) {
        this.mario = mario;
        this.camera = camera;
    }

    @Override
    public void execute() {
        mario.move(false, camera);
    }
}
