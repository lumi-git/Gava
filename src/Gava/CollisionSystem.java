package Gava;

import Gava.DefaultComponent.ColliderComponent;

import java.awt.*;

public abstract class CollisionSystem {
    public abstract void update();
    public abstract void insert(ColliderComponent go);
    public abstract void clear();
    public abstract void draw(Graphics g);

    public abstract int getCollsCount();
}
