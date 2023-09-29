package Gava;

public class Camera {
    private Transform transform = new Transform();

    private Vector2D offset = new Vector2D(0,0);

    public void setOffset(Vector2D offset) {
        this.offset = offset;
    }

    public Vector2D getOffset() {
        return offset;
    }

    public Camera(Vector2D position, Vector2D scale, double rotation) {
        transform.setPosition(position);

        transform.setScale(scale);
        transform.setRotation(rotation);

    }

    public Camera(Vector2D position, Vector2D scale) {
        transform.setPosition(position);

        transform.setScale(scale);
        transform.setRotation(0);

    }

    public Camera(Vector2D position) {
        transform.setPosition(position);

        transform.setScale(new Vector2D(1,1));
        transform.setRotation(0);

    }

    public Camera() {
        transform.setPosition(new Vector2D(0,0));

        transform.setScale(new Vector2D(1,1));
        transform.setRotation(0);

    }

    public Transform getTransform() {
        return transform;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public void setPosition(Vector2D position) {
        this.transform.setPosition(position);
    }

    public void setScale(Vector2D scale) {
        this.transform.setScale(scale);
    }

    public void setRotation(double rotation) {
        this.transform.setRotation(rotation);
    }

    public Vector2D getPosition() {
        return this.transform.getPosition();
    }

    public Vector2D getScale() {
        return this.transform.getScale();
    }

    public double getRotation() {
        return this.transform.getRotation();
    }

    public void translate(Vector2D translation) {
        this.transform.setPosition(this.transform.getPosition().add(translation));
    }





}
