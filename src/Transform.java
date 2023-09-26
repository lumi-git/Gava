public class Transform {
    private Vector2D position;
    private Vector2D scale;
    private double rotation;

    public Transform(){
        this.position = new Vector2D();
        this.scale = new Vector2D(1, 1);
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
    public Vector2D getScale(){
        return this.scale;
    }
    public double getRotation(){
        return this.rotation;
    }
    public void setPosition(Vector2D position){
        this.position = position;
    }
    public void setScale(Vector2D scale){
        this.scale = scale;
    }
    public void setRotation(double rotation){
        this.rotation = rotation;
    }
    public Transform clone(){
        return new Transform(this.position.clone(), this.scale.clone(), this.rotation);
    }

}
