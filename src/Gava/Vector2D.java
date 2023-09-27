package Gava;

public class Vector2D {
    public Vector2D(){
        this.x = 0;
        this.y = 0;
    }
    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }
    public double x;
    public double y;
    public Vector2D add(Vector2D other){
        return new Vector2D(this.x + other.x, this.y + other.y);
    }
    public Vector2D subtract(Vector2D other){
        return new Vector2D(this.x - other.x, this.y - other.y);
    }
    public Vector2D scale(double rate){
        return new Vector2D(this.x * rate, this.y * rate);
    }
    public double getLength(){
        return Math.sqrt(x*x + y*y);
    }
    public Vector2D normalize(){
        double length = getLength();
        return new Vector2D(this.x / length, this.y / length);
    }
    public Vector2D rotate(double angle){
        double rad = Math.toRadians(angle);
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);
        return new Vector2D(this.x * cos - this.y * sin, this.x * sin + this.y * cos);
    }
    public Vector2D set(double x, double y){
        this.x = x;
        this.y = y;
        return this;
    }
    public Vector2D set(Vector2D other){
        return this.set(other.x, other.y);
    }
    public Vector2D clone(){
        return new Vector2D(this.x, this.y);
    }

    public static Vector2D up(){
        return new Vector2D(0,-1);
    }
    public static Vector2D down(){
        return new Vector2D(0,1);
    }

    public static Vector2D left(){
        return new Vector2D(-1,0);
    }

    public static Vector2D right(){
        return new Vector2D(1,0);
    }

    @Override
    public String toString() {
        return "( x: " + x + "," + "y: " + y + ")";
    }
}
