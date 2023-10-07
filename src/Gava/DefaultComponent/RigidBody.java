package Gava.DefaultComponent;
import Gava.Debug;
import Gava.GameObject;
import Gava.Transform;
import Gava.Vector2D;

public class RigidBody extends ColliderComponent {

    double mass = 1;

    Vector2D friction = new Vector2D(0.2,0.2);

    Vector2D speed = new Vector2D();
    Vector2D force = new Vector2D();


    public RigidBody(GameObject parent) {
        super(parent);
    }


    @Override
    public void update(double dt) {
        super.update(dt);

        updateBody(dt);
    }

    public void updateBody(double dt){
        speed = speed.add(force.scale(dt));
        parent.getModificationTransform().setPosition(parent.getModificationTransform().getPosition().add(speed.scale(dt)));
        speed = speed.subtract(speed.multiply(friction.scale(dt)));
        force = new Vector2D();
    }

    public void addForce(Vector2D f){
        this.force = this.force.add(f);
    }


    @Override
    public void onCollisionStay(ColliderComponent other) {
        RigidBody otherRigidBody = (RigidBody) other.getParent().getComponent(RigidBody.class);
        if( otherRigidBody != null){


        }
    }
}
