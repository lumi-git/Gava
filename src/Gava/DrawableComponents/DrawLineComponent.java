package Gava.DrawableComponents;

import Gava.*;
import Gava.DefaultGameObjects.LineObject;

import java.awt.*;

public class DrawLineComponent extends DrawableComponent {
    LineObject lineObject;
    public DrawLineComponent(GameObject parent) {
        super(parent);
        lineObject = (LineObject) parent;
        setDrawLayer(3);
    }

    @Override
    public void draw(Graphics g) {
        Camera camera = Game.getInstance().getCamera();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(lineObject.getColor());
        g2d.drawLine((int)(lineObject.getObject(0).getReadonlyTransform().getCenteredPosition().x - camera.getPosition().x),(int)(lineObject.getObject(0).getReadonlyTransform().getCenteredPosition().y - camera.getPosition().y),(int)(lineObject.getObject(1).getReadonlyTransform().getCenteredPosition().x - camera.getPosition().x),(int)((lineObject.getObject(1).getReadonlyTransform().getCenteredPosition().y) - camera.getPosition().y));

    }
}
