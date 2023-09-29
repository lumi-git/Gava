package Gava;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class GameObject {

    private boolean isDestroyed = false;
    private final Transform transform = new Transform();
    private Transform globalTransform = new Transform();
    protected final ArrayList<Component> components = new ArrayList<Component>();
    private final ArrayList<Component> componentsTOADD = new ArrayList<Component>();

    protected final ArrayList<DrawableComponent> drawableComponents = new ArrayList<DrawableComponent>();

    private final ArrayList<DrawableComponent> drawableComponentsTOADD = new ArrayList<DrawableComponent>();

    protected GameObject parent = null;
    protected ArrayList<GameObject> children = new ArrayList<GameObject>();

    private ArrayList<GameObject> childrenTOADD = new ArrayList<GameObject>();

    private final String name;

    public GameObject(String name){
        this.name = name;
    }

    public GameObject(){
        this.name = "Gava.GameObject";
    }

    public String getName(){
        return this.name;
    }

    public void addChild(GameObject child){
        child.parent = this;
        childrenTOADD.add(child);
    }


    public void Mstart() {
        this.start();
    }
    public Transform getModificationTransform(){
        return this.transform;
    }

    public Transform getReadonlyTransform(){
        if (parent != null){

            return parent.getReadonlyTransform().Combine(transform);
        }
        else{

            return transform;
        }
    }


    public void start(){

    }

    public void Mupdate(double dt){

        if (!childrenTOADD.isEmpty()){
            children.addAll(childrenTOADD);
            childrenTOADD.clear();
        }

        if(!componentsTOADD.isEmpty()){
            components.addAll(componentsTOADD);
            componentsTOADD.clear();
        }

        if(!drawableComponentsTOADD.isEmpty()){
            drawableComponents.addAll(drawableComponentsTOADD);
            drawableComponentsTOADD.clear();
        }


        this.update(dt);

        Iterator<Component> it = components.iterator();
        while (it.hasNext()) {
            Component c = it.next();
            if (c.isDestroyed())
                it.remove();
            else c.Mupdate(dt);
        }

    }
    public void update(double dt){

    }

    public boolean isDestroyed(){
        return this.isDestroyed;
    }
    public void addComponent(Component component){
        this.componentsTOADD.add(component);
        component.Mstart();
    }

    public void removeComponent(Component component){
        component.destroy();
    }
    public void removeDrawableComponent(DrawableComponent component){
        component.destroy();
    }
    public ArrayList<Component> getComponents(){
        return this.components;
    }

    public Component getComponent(Class<?> componentClass){
        for(Component c : components){
            if(c.getClass() == componentClass){
                return c;
            }
        }
        return null;
    }

    public void destroy(){
        this.isDestroyed = true;
        for (Component c : components){
            c.destroy();
            c.Mend();
        }
        for (DrawableComponent dc : drawableComponents){
            dc.destroy();
            dc.Mend();
        }
        for (GameObject go : children){
            go.destroy();
            go.Mend();
        }
    }
    public void addDrawableComponent(DrawableComponent dc){
        drawableComponentsTOADD.add(dc);
        Game.getInstance().getCurrentScene().addDrawableComponent(dc);
    }

    public ArrayList<DrawableComponent> getDrawableComponents(){
        return this.drawableComponents;

    }

    public <T> T getDrawableComponent(Class<T> componentClass){
        //TODO: replace with hash table
        for(DrawableComponent c : drawableComponents){
            if(c.getClass() == componentClass){
                return (T) c;
            }
        }
        return null;
    }

    public void Mend(){
        end();
    }

    public void end(){

    }
}
