package Gava.DrawableComponents;

import Gava.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawSpriteComponent extends DrawableComponent {
    BufferedImage image;
    public DrawSpriteComponent(String imagePath, GameObject parent){
        super(parent);
        image = Game.getInstance().getImageLibrary().getImage(imagePath);
    }

    @Override
    public void draw(Graphics g) {
        Camera camera = Game.getInstance().getCamera();
        g.drawImage(image,(int)( parent.getReadonlyTransform().getPosition().x - camera.getPosition().x),
                (int) (parent.getReadonlyTransform().getPosition().y - camera.getPosition().y),(int)parent.getReadonlyTransform().getScale().x,(int)parent.getReadonlyTransform().getScale().y,null);

    }
}
