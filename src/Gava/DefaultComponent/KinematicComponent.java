package Gava.DefaultComponent;

import Gava.*;

public class KinematicComponent extends Component {

    boolean useGravity = false;

    Vector2D velocity = new Vector2D();

    public void UseGravity(boolean state){
        useGravity = state;
    }

    public void applyForce(Vector2D forceVector){
        velocity = velocity.add(forceVector);
    }

    public KinematicComponent(GameObject parent) {
        super(parent);
    }

    @Override
    public void update(double dt){
        if(useGravity){
            applyForce(Vector2D.down().scale(Game.getInstance().getPhysicGlobalRules().getGravity()));
        }
        parent.getTransform().setPosition(parent.getTransform().getPosition().add(velocity));
    }

}
