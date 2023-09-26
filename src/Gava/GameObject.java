package Gava;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class GameObject {
    private boolean isDestroyed = false;
    private Transform transform = new Transform();
    private ArrayList<Component> components = new ArrayList<Component>();
    private ArrayList<Component> componentsTOREMOVE = new ArrayList<Component>();

    private ArrayList<DrawableComponent> drawableComponents = new ArrayList<DrawableComponent>();
    private ArrayList<DrawableComponent> drawableComponentsTOREMOVE = new ArrayList<DrawableComponent>();

    private String name;

    public GameObject(String name){
        this.name = name;
    }

    public GameObject(){
        this.name = "Gava.GameObject";
    }

    public void Mstart() {
        this.start();
    }
    public Transform getTransform(){
        return this.transform;
    }

    public abstract void start();

    public void Mupdate(double dt){
        components.removeAll(componentsTOREMOVE);
        drawableComponents.removeAll(drawableComponentsTOREMOVE);
        componentsTOREMOVE.clear();
        drawableComponentsTOREMOVE.clear();

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
        this.components.add(component);
        component.Mstart();
    }

    public void removeComponent(Component component){
        this.componentsTOREMOVE.add(component);
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
        drawableComponents.add(dc);
        Game.getInstance().getCurrentScene().addDrawableComponent(dc);
    }


    public ArrayList<DrawableComponent> getDrawableComponents(){
        return this.drawableComponents;

    }

    public DrawableComponent getDrawableComponent(Class<?> componentClass){
        for(DrawableComponent c : drawableComponents){
            if(c.getClass() == componentClass){
                return c;
            }
        }
        return null;
    }

}
