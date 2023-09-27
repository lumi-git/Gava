package Gava.DefaultGameObjects;

import Gava.Game;
import Gava.GameObject;

import java.awt.*;

public class EmbededAwtGameObject extends GameObject {
    Component c;

    public EmbededAwtGameObject(Component comp){
        c = comp;
    }

    @Override
    public void start() {
        Game.getInstance().add(c);
    }

    @Override
    public void end(){
        Game.getInstance().remove(c);
    }

}
