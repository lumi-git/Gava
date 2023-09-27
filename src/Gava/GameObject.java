package Gava;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class GameObject {

    private boolean isDestroyed = false;
    private final Transform transform = new Transform();
    private final ArrayList<Component> components = new ArrayList<Component>();
    private final ArrayList<Component> componentsTOADD = new ArrayList<Component>();

    private final ArrayList<DrawableComponent> drawableComponents = new ArrayList<DrawableComponent>();

    private final ArrayList<DrawableComponent> drawableComponentsTOADD = new ArrayList<DrawableComponent>();

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


    public void Mstart() {
        this.start();
    }
    public Transform getTransform(){
        return this.transform;
    }

    public abstract void start();

    public void Mupdate(double dt){
        components.addAll(componentsTOADD);
        componentsTOADD.clear();
        drawableComponents.addAll(drawableComponentsTOADD);
        drawableComponentsTOADD.clear();

        this.update(dt);

        Iterator<Component> it = components.iterator();
        while (it.hasNext()) {
            Component c = it.next();
            if (c.isDestroyed())
                it.remove();
            else c.Mupdate(dt);
        }

    }
    public abstract void update(double dt);

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
        }
        for (DrawableComponent dc : drawableComponents){
            dc.destroy();
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
