package Gava.DefaultComponent;
import Gava.*;

public class RigidBody extends ColliderComponent {

    double mass = 1;

    Vector2D friction = new Vector2D(0.9,0.9);

    Vector2D speed = new Vector2D();
    Vector2D force = new Vector2D();


    public RigidBody(GameObject parent) {
        super(parent);
    }


    @Override
    public void update(double dt) {

        updateBody(dt);

    }

    public void updateBody(double dt){
        Game.getInstance().getCollisionSystem().insert(this);
        speed = speed.add(force.scale(dt));
        speed = speed.multiply(friction);
        parent.getModificationTransform().setPosition(parent.getModificationTransform().getPosition().add(speed));
        force = new Vector2D();
    }

    public void addForce(Vector2D f){
        this.force = this.force.add(f);
    }


    @Override
    public void onCollisionStay(ColliderComponent other) {
        RigidBody otherRigidBody = (RigidBody) other.getParent().getComponent(RigidBody.class);
        Vector2D difference = this.parent.getModificationTransform().getPosition().subtract(other.getParent().getModificationTransform().getPosition());
        if(otherRigidBody != null){
           addForce(difference.scale(0.1));

        }
    }
}
