package Gava.DefaultGameObjects;

import Gava.Debug;
import Gava.DrawableComponents.DrawTextComponent;
import Gava.GameObject;

import java.awt.*;

public class TextGameObject extends GameObject {
    DrawTextComponent dc;

    public TextGameObject(){
        dc = new DrawTextComponent(new Color(0,0,0),"",this);
    }

    @Override
    public void start() {
        this.addDrawableComponent(dc);
    }

    @Override
    public void update(double dt) {

    }

    public void setText(String text){
        dc.setText(text);
    }

    public void setColor(Color color){
        dc.setColor(color);
    }

}
