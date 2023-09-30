package Gava.DefaultComponent;

import Gava.GameObject;
import Gava.Input;

import java.awt.event.KeyEvent;

public class Player2dMovements extends Gava.Component{
    double speed ;
    public Player2dMovements(GameObject parent, double playerSpeed) {
        super(parent);
        speed = playerSpeed;
    }
    @Override
    public void update(double dt) {
        if(Input.get("up")){
            parent.getModificationTransform().getPosition().y -=speed;
        }
        if(Input.get("down")){
            parent.getModificationTransform().getPosition().y +=speed;
        }
        if(Input.get("left")){
            parent.getModificationTransform().getPosition().x -=speed;
        }
        if(Input.get("right")){
            parent.getModificationTransform().getPosition().x +=speed;
        }
    }
}
