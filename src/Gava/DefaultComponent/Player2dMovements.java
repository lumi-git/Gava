package Gava.DefaultComponent;

import Gava.Component;
import Gava.GameObject;
import Gava.Input;

import java.awt.event.KeyEvent;

public class Player2dMovements extends Component {
    double speed = 2;
    public Player2dMovements(GameObject parent, double speed) {
        super(parent);
        this.speed = speed;
    }

    @Override
    public void update(double dt){

        if(Input.isKeyPressed(KeyEvent.VK_Z)){
            parent.getModificationTransform().getPosition().y -=speed;
        }
        if(Input.isKeyPressed(KeyEvent.VK_S)){
            parent.getModificationTransform().getPosition().y +=speed;
        }
        if(Input.isKeyPressed(KeyEvent.VK_Q)){
            parent.getModificationTransform().getPosition().x -=speed;
        }
        if(Input.isKeyPressed(KeyEvent.VK_D)){
            parent.getModificationTransform().getPosition().x +=speed;
        }
    }

}
