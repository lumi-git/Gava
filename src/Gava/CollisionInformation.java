package Gava;

public class CollisionInformation {
    private GameObject other;
    private Vector2D normal;
    private float depth;

    public CollisionInformation(Vector2D normal, float depth) {
        this.other = null;
        this.normal = normal;
        this.depth = depth;
    }

    public float getDepth() {
        return depth;
    }

    public Vector2D getNormal() {
        return normal;
    }

    public void setOther(GameObject other){
        this.other = other;
    }

    public GameObject getOther(){
        return other;
    }

}
