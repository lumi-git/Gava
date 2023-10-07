package Gava.DefaultGameObjects;

public class VoidGameObject extends Gava.GameObject{

    @Override
    public void start() {
        getModificationTransform().setPosition(new Gava.Vector2D(0,0));
    }
}
