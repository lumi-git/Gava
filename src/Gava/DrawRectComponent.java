package Gava;

import java.awt.*;

public class DrawRectComponent extends DrawableComponent{

    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;
    public DrawRectComponent(int width, int height, Color color,GameObject parent){
        super(parent);
        this.width = width;
        this.height = height;
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
        g.setColor(color);
        g.fillRect((int) parent.getTransform().getPosition().x, (int) parent.getTransform().getPosition().y, width, height);
    }
}
