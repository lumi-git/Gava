package Gava.DefaultComponent;

import Gava.*;
import Gava.utility.CollisionResolver;

public abstract class ColliderComponent  extends Component {

    private boolean isColliding = false;
    private boolean lastIsColliding = false;

    private ColliderComponent other = null;


    public ColliderComponent(GameObject parent){
        super(parent);
    }

    public void onCollision(ColliderComponent other, CollisionInformation collisionInformation){

        this.other = other;
        isColliding = true;
        if (other.getParent().getName().hashCode() > parent.getName().hashCode()){
            CollisionResolver.resolve(this, other);
            Debug.log("START FOR NEW Collision for object " + parent.getName() + " with " + other.getParent().getName() + "");
        }
        MonCollisionStay(collisionInformation);
    }

    private boolean stateChanged(){
        return lastIsColliding != isColliding;
    }

    public void CollisionPreUpdate(){
        isColliding = false;
    }

    public void CollisionPostUpdate(){
        //Debug.log(lastIsColliding + " " + isColliding);
        lastIsColliding = isColliding;
    }


    public void onCollisionExit(CollisionInformation collisionInformation){
        parent.onCollisionExit(collisionInformation);
    }

    public void MonCollisionStay(CollisionInformation collisionInformation){
        parent.onCollisionStay(collisionInformation);
        onCollisionStay(collisionInformation);
        if(stateChanged()){
            onCollisionEnter(collisionInformation);
        }
    }

    public void onCollisionStay(CollisionInformation collisionInformation){


    }

    public void onCollisionEnter(CollisionInformation collisionInformation){
        parent.onCollisionEnter(collisionInformation);
    }

    @Override
    public void update(double dt) {
        Game.getInstance().getCollisionSystem().insert(this);
    }


}
