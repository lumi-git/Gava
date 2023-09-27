package Gava.DrawableComponents;

import Gava.DrawableComponent;
import Gava.Game;
import Gava.GameObject;
import Gava.ImageLibrary;

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
        g.drawImage(image,(int)parent.getTransform().getPosition().x,(int)parent.getTransform().getPosition().y,(int)parent.getTransform().getScale().x,(int)parent.getTransform().getScale().y,null);

    }
}
