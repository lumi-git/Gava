package Gava.DefaultGameObjects;

import Gava.DrawableComponent;
import Gava.DrawableComponents.DrawTextComponent;
import Gava.Game;
import Gava.Vector2D;

import java.awt.*;

public class GameObjectsDisplay extends Gava.GameObject{
    @Override
    public void start() {
        DrawableComponent dc = new DrawTextComponent(Color.black,"text",this);
        dc.setDrawLayer(1);
        this.addDrawableComponent(dc);

        this.getTransform().setScale(new Vector2D(100,10));
        this.getTransform().setPosition(new Vector2D(10,50));

    }

    @Override
    public void update(double dt) {
        getDrawableComponent(DrawTextComponent.class).setText("GameObjects: "+Game.getInstance().getCurrentScene().getGameObjects().size());
    }
}
