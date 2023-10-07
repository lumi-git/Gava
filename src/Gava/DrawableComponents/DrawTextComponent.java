package Gava.DrawableComponents;

import Gava.Camera;
import Gava.Game;
import Gava.GameObject;

import java.awt.*;

public class DrawTextComponent extends ColorComponent{

    String text = "";
    int size = 20;
    String fontType = "TimesRoman";
    Font font = new Font(fontType, Font.PLAIN, size);

    public DrawTextComponent(Color color,String text, GameObject parent) {
        super(parent);
        this.text = text;
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
        Camera camera = Game.getInstance().getCamera();
        g.setColor(color);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setFont(font);

        g2d.drawString(text,(int)GetDrawingTransform().getPosition().x,
                (int) GetDrawingTransform().getPosition().y);
    }

    public void setText(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }

    public void setSize(int size){
        this.size = size;
        font = new Font(fontType, Font.PLAIN, size);
    }

    public void setFontType(String fontType){
        this.fontType = fontType;
        font = new Font(fontType, Font.PLAIN, size);
    }

}
