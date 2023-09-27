package Gava.DrawableComponents;

import Gava.DrawableComponent;
import Gava.GameObject;
import Gava.RotateRect;

import java.awt.*;

public class DrawRectComponent extends ColorComponent {

    public DrawRectComponent(Color color, GameObject parent){
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
        if (parent.getTransform().getRotation() != 0) {
            g2d.fill(RotateRect.rotate(new Rectangle((int) parent.getTransform().getPosition().x,
                    (int) parent.getTransform().getPosition().y, (int) parent.getTransform().getScale().x, (int) parent.getTransform().getScale().y), parent.getTransform().getRotation()));
        }else{
            g2d.fillRect((int) parent.getTransform().getPosition().x,
                    (int) parent.getTransform().getPosition().y, (int) parent.getTransform().getScale().x, (int) parent.getTransform().getScale().y);
        }
    }

}
