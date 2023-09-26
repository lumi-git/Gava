import java.awt.*;
import java.util.ArrayList;

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
        this.name = "GameObject";
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
        if(isDestroyed){
            Game.getInstance().getCurrentScene().removeGameObject(this);
        }
        else{
            this.update(dt);
            for(Component c : components){
                if (isDestroyed) return;
                c.Mupdate(dt);

            }
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

    public void removeDrawableComponent(DrawableComponent dc){
        drawableComponentsTOREMOVE.add(dc);
        Game.getInstance().getCurrentScene().removeDrawableComponent(dc);
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
