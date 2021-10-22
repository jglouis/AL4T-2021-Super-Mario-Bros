package model;

import manager.Camera;
import model.hero.Mario;

public class MarioMoveRight implements Command {
    private final Mario mario;
    private final Camera camera;

    public MarioMoveRight(Mario mario, Camera camera) {
        this.mario = mario;
        this.camera = camera;
    }

    @Override
    public void execute() {
        mario.move(false, camera);
    }
}
