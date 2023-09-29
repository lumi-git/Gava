package Gava.DefaultGameObjects;

import Gava.Game;
import Gava.GameObject;
import Gava.Vector2D;

public class CameraObject extends GameObject{
    @Override
    public void start() {
    }

    @Override
    public void update(double dt) {
        Game.getInstance().getCamera().setOffset(new Vector2D(Game.getInstance().getScreenWidth()/2 - getReadonlyTransform().getScale().x/2,Game.getInstance().getScreenHeight()/2 - getReadonlyTransform().getScale().y/2));
        Game.getInstance().getCamera().setPosition(getReadonlyTransform().getPosition().subtract(Game.getInstance().getCamera().getOffset()));
    }

}
