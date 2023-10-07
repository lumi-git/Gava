package Gava.DefaultComponent;

import Gava.GameObject;
import Gava.Input;

import java.awt.event.KeyEvent;

public class PlayerBody extends RigidBody{
    double speed = 2;
    public PlayerBody(GameObject parent, double speed) {
        super(parent);
        this.speed = speed;
    }

    @Override
    public void update(double dt){
        updateBody(dt);
        if(Input.isKeyPressed(KeyEvent.VK_Z)){
            addForce(new Gava.Vector2D(0,-speed));
        }
        if(Input.isKeyPressed(KeyEvent.VK_S)){
            addForce(new Gava.Vector2D(0,speed));
        }
        if(Input.isKeyPressed(KeyEvent.VK_Q)){
            addForce(new Gava.Vector2D(-speed,0));
        }
        if(Input.isKeyPressed(KeyEvent.VK_D)){
            addForce(new Gava.Vector2D(speed,0));
        }
    }
}
