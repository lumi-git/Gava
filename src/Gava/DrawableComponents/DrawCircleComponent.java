package Gava.DrawableComponents;

import Gava.GameObject;

import java.awt.*;

public class DrawCircleComponent extends ColorComponent {

    public DrawCircleComponent(Color color, GameObject parent) {
        super(parent);
        this.color = color;
    }

    @Override
    public void start() {
    }

    @Override
    public void update(double dt) {
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        Graphics2D g2d = (Graphics2D) g;

        g2d.fillOval((int) parent.getTransform().getPosition().x,
                (int) parent.getTransform().getPosition().y, (int) parent.getTransform().getScale().x, (int) parent.getTransform().getScale().y);

    }


}
