package Gava.DefaultGameObjects;

import Gava.DrawableComponent;
import Gava.DrawableComponents.DrawTextComponent;
import Gava.Game;
import Gava.GameObject;
import Gava.Vector2D;

import java.awt.*;

public class CollisionsDisplay extends GameObject {
    @Override
    public void start() {
        setStaticOnScreen(true);
        DrawableComponent dc = new DrawTextComponent(Color.black,"text",this);
        dc.setDrawLayer(1);
        this.addDrawableComponent(dc);

        this.getModificationTransform().setScale(new Vector2D(100,10));
        this.getModificationTransform().setPosition(new Vector2D(10,80));

    }

    @Override
    public void update(double dt) {
        getDrawableComponent(DrawTextComponent.class).setText("with spacial hash: "+ Game.getInstance().getCollisionSystem().getCollsCount());
    }
}
