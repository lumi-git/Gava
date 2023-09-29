package Gava.DrawableComponents;

import Gava.*;

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
        Camera camera = Game.getInstance().getCamera();
        g.setColor(color);
        Graphics2D g2d = (Graphics2D) g;
        if (parent.getReadonlyTransform().getRotation() != 0) {
            g2d.fill(RotateRect.rotate(new Rectangle((int)( parent.getReadonlyTransform().getPosition().x - camera.getPosition().x),
                    (int) (parent.getReadonlyTransform().getPosition().y - camera.getPosition().y), (int) parent.getReadonlyTransform().getScale().x, (int) parent.getReadonlyTransform().getScale().y), parent.getReadonlyTransform().getRotation()));
        }else{
            g2d.fillRect((int)( parent.getReadonlyTransform().getPosition().x - camera.getPosition().x),
                    (int) (parent.getReadonlyTransform().getPosition().y - camera.getPosition().y), (int) parent.getReadonlyTransform().getScale().x, (int) parent.getReadonlyTransform().getScale().y);
        }
    }

}
