package Gava.DefaultComponent;

import Gava.*;

public class RigidBody extends Component {
    private Vector2D velocity;
    private Vector2D acceleration;
    private double mass;
    private double restitution;  // Bounciness coefficient, between 0 and 1.

    private boolean isStatic = false;
    private double friction;

    public RigidBody(GameObject parent, double mass, double restitution, boolean isStatic) {
        super(parent);
        this.isStatic = isStatic;
        this.velocity = new Vector2D();
        this.acceleration = new Vector2D();
        this.mass = mass;
        this.restitution = restitution;
        this.friction = 0.1;
        CollideBoxComponent cc = new CollideBoxComponent(parent);
        parent.addComponent(cc);
    }

    public void applyForce(Vector2D force) {
        Vector2D forceAccumulated = force.scale(1.0/mass);
        acceleration.m_add(forceAccumulated);
    }

    public void update(double dt) {
        if (isStatic) return;
        acceleration.m_add(velocity.scale(-friction));
        velocity.m_add(acceleration.scale(dt));
        parent.getModificationTransform().getPosition().m_add(velocity.scale(dt));
        acceleration.set(0, 0);  // Reset acceleration
    }

    public boolean isStatic() {
        return isStatic;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    public double getMass() {
        return mass;
    }

    public double getRestitution() {
        return restitution;
    }

    public void setRestitution(double restitution) {
        this.restitution = restitution;
    }



}