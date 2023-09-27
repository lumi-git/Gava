package Gava.DrawableComponents;

import Gava.DrawableComponent;
import Gava.GameObject;

import java.awt.*;

public abstract class ColorComponent extends DrawableComponent {
    protected Color color = Color.BLACK;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ColorComponent(GameObject parent) {
        super(parent);
    }

    public abstract void draw(Graphics g);
}
