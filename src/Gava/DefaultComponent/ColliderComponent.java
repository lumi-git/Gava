package Gava.DefaultComponent;

import Gava.Component;
import Gava.Debug;
import Gava.Game;
import Gava.GameObject;

public abstract class ColliderComponent  extends Component {

    private boolean isColliding = false;
    private boolean lastIsColliding = false;

    private ColliderComponent other = null;


    public ColliderComponent(GameObject parent){
        super(parent);
    }

    public void onCollision(ColliderComponent other){
        //Debug.log("START FOR NEW Collision for object " + parent.getName() + " with " + other.getParent().getName() + "");
        this.other = other;
        isColliding = true;
        MonCollisionStay(other);
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


    public void onCollisionExit(ColliderComponent other){
        parent.onCollisionExit(other.getParent());
    }

    public void MonCollisionStay(ColliderComponent other){
        parent.onCollisionStay(other.getParent());
        onCollisionStay(other);
        if(stateChanged()){
            onCollisionEnter(other);
        }
    }

    public void onCollisionStay(ColliderComponent other){

    }

    public void onCollisionEnter(ColliderComponent other){
        parent.onCollisionEnter(other.getParent());
    }

    @Override
    public void update(double dt) {
        Game.getInstance().getCollisionSystem().insert(this);
    }


}
