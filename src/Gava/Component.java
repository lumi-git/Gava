package Gava;

public abstract class Component {

    boolean isDestroyed = false;
    GameObject parent;

    public Component(GameObject parent){
        this.parent = parent;
    }

    public boolean isDestroyed(){
        return this.isDestroyed;
    }

    public void destroy(){
        this.isDestroyed = true;
    }

    public abstract void start();
    public void Mstart(){
        this.start();
    }
    public abstract void update(double dt);
    public void Mupdate(double dt){
        this.update(dt);
    }
}
