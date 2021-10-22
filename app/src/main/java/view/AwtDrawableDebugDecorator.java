package view;

import model.GameObject;

import java.awt.*;

public class AwtDrawableDebugDecorator implements AwtDrawable {

    private final GameObject decorated;

    public AwtDrawableDebugDecorator(GameObject gameObject) {
        decorated = gameObject;
    }

    @Override
    public void draw(Graphics g) {
        decorated.draw(g);
        g.setColor(Color.RED);
        g.drawRect((int) decorated.getX(),
                (int) decorated.getY(),
                (int) decorated.getDimension().getWidth(),
                (int) decorated.getDimension().getHeight());
    }
}
