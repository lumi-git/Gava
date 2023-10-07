package Gava;

public abstract class Component {
    private int Drawlayer = 0;
    public int getDrawLayer(){
        return Drawlayer;
    }
    public void setDrawLayer(int layer){
        this.Drawlayer = layer;
    }
    boolean added = false;
    boolean isDestroyed = false;
    protected GameObject parent;

    public Component(GameObject parent){
        this.parent = parent;
    }

    public boolean isDestroyed(){
        return this.isDestroyed;
    }

    public void destroy(){
        this.isDestroyed = true;
    }

    public void start(){

    }
    public void Mstart(){
        this.start();
    }
    public void update(double dt){

    }
    public void Mupdate(double dt){
        this.update(dt);
    }

    public void Mend(){
        end();
    }
    public void end(){

    }

    public GameObject getParent(){
        return this.parent;
    }

}
