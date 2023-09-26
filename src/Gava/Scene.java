package Gava;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Scene {
    private final ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

    private final ArrayList<DrawableComponent> drawableComponents = new ArrayList<DrawableComponent>();

    public void addDrawableComponent(DrawableComponent dc){
        drawableComponents.add(dc);
    }

    public ArrayList<DrawableComponent> getDrawableComponents(){
        return this.drawableComponents;
    }

    public void addGameObject(GameObject go){
        gameObjects.add(go);
        go.Mstart();
    }


    public void Mupdate(double dt){

        this.update(dt);
        Iterator<GameObject> it = gameObjects.iterator();
        while (it.hasNext()) {
            GameObject go = it.next();
            if (go.isDestroyed())
                it.remove();
            else go.Mupdate(dt);
        }
    }

    public void Mdraw(Graphics g){
        Iterator<DrawableComponent> it = drawableComponents.iterator();
        while (it.hasNext()) {
            DrawableComponent dc = it.next();
            if (dc.isDestroyed())
                it.remove();
            else dc.Mdraw(g);
        }
    }

    public ArrayList<GameObject> getGameObjects(){
        return this.gameObjects;
    }

    public abstract void update(double dt);
    public void Mstart(){
        this.start();

    }
    public abstract void start();



}
