package Gava;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Scene {
    private final ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
    private final ArrayList<GameObject> gameObjectsTOADD = new ArrayList<GameObject>();

    private final ArrayList<DrawableComponent> drawableComponents = new ArrayList<DrawableComponent>();

    private final ArrayList<DrawableComponent> drawableComponentsTOADD = new ArrayList<DrawableComponent>();

    public void addDrawableComponent(DrawableComponent dc){
        drawableComponentsTOADD.add(dc);
    }

    public ArrayList<DrawableComponent> getDrawableComponents(){
        return this.drawableComponents;
    }



    public void addGameObject(GameObject go){
        gameObjectsTOADD.add(go);
        go.Mstart();
    }


    public void Mupdate(double dt){
        gameObjects.addAll(gameObjectsTOADD);
        gameObjectsTOADD.clear();
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
        drawableComponents.addAll(drawableComponentsTOADD);
        drawableComponentsTOADD.clear();
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
