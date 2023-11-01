package Gava.DefaultComponent;

import Gava.Component;
import Gava.GameObject;
import Gava.Input;

import java.awt.event.KeyEvent;

public class PlayerBody extends Component {
    double speed = 2;
    RigidBody rb;
    public PlayerBody(GameObject parent, double speed) {
        super(parent);
        rb = new RigidBody(parent,10,0.5,false);
        parent.addComponent(rb);
        this.speed = speed;
    }

    @Override
    public void update(double dt){

        if(Input.isKeyPressed(KeyEvent.VK_Z)){
            rb.applyForce(new Gava.Vector2D(0,-speed));
        }
        if(Input.isKeyPressed(KeyEvent.VK_S)){
            rb.applyForce(new Gava.Vector2D(0,speed));
        }
        if(Input.isKeyPressed(KeyEvent.VK_Q)){
            rb.applyForce(new Gava.Vector2D(-speed,0));
        }
        if(Input.isKeyPressed(KeyEvent.VK_D)){
            rb.applyForce(new Gava.Vector2D(speed,0));
        }
    }
}
