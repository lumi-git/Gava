package Gava.DrawableComponents;

import Gava.Camera;
import Gava.Game;
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
        Camera camera = Game.getInstance().getCamera();
        g.setColor(color);
        Graphics2D g2d = (Graphics2D) g;

        g2d.fillOval((int)( parent.getReadonlyTransform().getPosition().x - camera.getPosition().x),
                (int) (parent.getReadonlyTransform().getPosition().y - camera.getPosition().y), (int) parent.getReadonlyTransform().getScale().x, (int) parent.getReadonlyTransform().getScale().y);

    }


}
