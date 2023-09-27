package Gava;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Scene {
    private final ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
    private final ArrayList<GameObject> gameObjectsTOADD = new ArrayList<GameObject>();

    private final ArrayList<DrawableComponent> drawableComponentsTOADD = new ArrayList<DrawableComponent>();

    public void addDrawableComponent(DrawableComponent dc){
        drawableComponentsTOADD.add(dc);
    }

    private final ArrayList<ArrayList<DrawableComponent>> drawLayers = new ArrayList<ArrayList<DrawableComponent>>();

    public Scene(){
        for(int i = Game.getInstance().getDrawLayerCount() ; i >0 ;i--)
            drawLayers.add(new ArrayList<DrawableComponent>());
    }

    public void addGameObject(GameObject go){
        gameObjectsTOADD.add(go);
        go.Mstart();
    }


    public void Mupdate(double dt){
        if (gameObjectsTOADD.size() > 0){
            gameObjects.addAll(gameObjectsTOADD);
            gameObjectsTOADD.clear();
        }

        this.update(dt);
        Iterator<GameObject> it = gameObjects.iterator();
        while (it.hasNext()) {
            GameObject go = it.next();
            if (go.isDestroyed()){
                go.Mend();
                it.remove();
            }
            else go.Mupdate(dt);
        }
    }

    public void Mdraw(Graphics g){

        if (!drawableComponentsTOADD.isEmpty()){
            Iterator<DrawableComponent> it = drawableComponentsTOADD.iterator();
            while (it.hasNext()){
                DrawableComponent dc = it.next();
                if (dc.getDrawLayer() >= drawLayers.size()){
                    drawLayers.add(new ArrayList<DrawableComponent>());
                }
                drawLayers.get(dc.getDrawLayer()).add(dc);
            }
            drawableComponentsTOADD.clear();
        }

        Iterator<ArrayList<DrawableComponent>> it2 = drawLayers.iterator();
        while (it2.hasNext()){
            ArrayList<DrawableComponent> layer = it2.next();
            Iterator<DrawableComponent> it = layer.iterator();
            while (it.hasNext()) {
                DrawableComponent dc = it.next();
                if (dc.isDestroyed()){
                    dc.Mend();
                    it.remove();
                }
                else dc.Mdraw(g);
            }
        }

    }

    public ArrayList<GameObject> getGameObjects(){
        return this.gameObjects;
    }

    public abstract void update(double dt);
    public void Mstart(){

        this.start();

    }
    public void start(){
    }

    public void Mend(){
        this.end();
    }

    public void end() {

    }


}
