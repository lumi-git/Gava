package Gava;

public class Transform {
    private Vector2D position;
    private Vector2D scale;
    private double rotation;

    public Transform(){
        this.position = new Vector2D();
        this.scale = new Vector2D(0, 0);
        this.rotation = 0;
    }
    public Transform(Vector2D position, Vector2D scale, double rotation){
        this.position = position;
        this.scale = scale;
        this.rotation = rotation;
    }
    public Vector2D getPosition(){
        return this.position;
    }

    public Vector2D getCenteredPosition(){
        return new Vector2D(this.position.x + (this.scale.x / 2), this.position.y + (this.scale.y / 2));
    }

    public Vector2D getScale(){
        return this.scale;
    }
    public double getRotation(){
        return this.rotation;
    }
    public void setPosition(Vector2D position){
        this.position = position.round(4);
    }
    public void setScale(Vector2D scale){
        this.scale = scale.round(4);
    }
    public void setRotation(double rotation){
        this.rotation = rotation;
    }
    public Transform clone(){
        return new Transform(this.position.clone(), this.scale.clone(), this.rotation);
    }

    public void Forward(double speed){
        this.position.x += Math.cos(this.rotation) * speed;
        this.position.y += Math.sin(this.rotation) * speed;
    }

    public void Move(Vector2D direction, double speed){
        this.position.x += direction.x * speed;
        this.position.y += direction.y * speed;
    }

    public Transform Combine(Transform transform){
        Transform newTransform = new Transform();
        newTransform.setPosition(this.position.add(transform.getPosition()));
        newTransform.setScale(transform.getScale());
        return newTransform;
    }


    public Vector2D getBottomLeft(){
        return new Vector2D(this.position.x, this.position.y + this.scale.y);
    }
    public Vector2D getBottomRight(){
        return new Vector2D(this.position.x + this.scale.x, this.position.y + this.scale.y);
    }
    public Vector2D getTopLeft(){
        return new Vector2D(this.position.x, this.position.y);
    }
    public Vector2D getTopRight(){
        return new Vector2D(this.position.x + this.scale.x, this.position.y);
    }
    public Vector2D getCenter(){
        return new Vector2D(this.position.x + this.scale.x / 2, this.position.y + this.scale.y / 2);
    }

    public boolean isInside(Vector2D point){
        return point.x >= this.position.x && point.x <= this.position.x + this.scale.x && point.y >= this.position.y && point.y <= this.position.y + this.scale.y;
    }

    public boolean Intersect(Transform transform){
        return this.isInside(transform.getBottomLeft()) || this.isInside(transform.getBottomRight()) || this.isInside(transform.getTopLeft()) || this.isInside(transform.getTopRight()) || transform.isInside(this.getBottomLeft()) || transform.isInside(this.getBottomRight()) || transform.isInside(this.getTopLeft()) || transform.isInside(this.getTopRight());

    }

    public Vector2D getFaceBasedOnDirection(Vector2D dir){
        if(dir.x >0){
            return this.getTopRight();
        }
        if(dir.x < 0){
            return this.getTopLeft();
        }
        if(dir.y > 0){
            return this.getBottomLeft();
        }
        if(dir.y < 0){
            return this.getTopLeft();
        }
        return this.getCenter();
    }

    public Vector2D OffsetCloseToEdge(Transform transform){

        Vector2D closestPoint = this.getFaceBasedOnDirection(transform.getPosition().subtract(this.getPosition()));
        return closestPoint.subtract(transform.getPosition());
    }


    @Override
    public String toString() {
        return "Transform{" +
                "position=" + position +
                ", scale=" + scale +
                ", rotation=" + rotation +
                '}';
    }
}
