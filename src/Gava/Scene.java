package Gava;

import Gava.DrawableComponents.LightComponent;
import Gava.utility.LightMap;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class Scene {
    private final ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
    private final ArrayList<GameObject> gameObjectsTOADD = new ArrayList<GameObject>();

    private final ArrayList<GameObject> gameObjectsTOSTART = new ArrayList<GameObject>();
    private final ArrayList<GameObject> gameObjectsToStartTOADD = new ArrayList<GameObject>();

    private final ArrayList<DrawableComponent> drawableComponentsTOADD = new ArrayList<DrawableComponent>();

    public void addDrawableComponent(DrawableComponent dc){
        drawableComponentsTOADD.add(dc);
    }

    private final ArrayList<ArrayList<DrawableComponent>> drawLayers = new ArrayList<ArrayList<DrawableComponent>>();

    private boolean isMainScene = false;

    public Scene(){
        for(int i = Game.getInstance().getDrawLayerCount() ; i >0 ;i--)
            drawLayers.add(new ArrayList<DrawableComponent>());
    }


    public void setMainScene(){
        isMainScene = true;
    }
    public boolean isMainScene(){
        return isMainScene;
    }

    public void addGameObject(GameObject go){
        gameObjectsTOADD.add(go);
        gameObjectsToStartTOADD.add(go);
    }


    public void Mupdate(double dt){
        if (!gameObjectsTOADD.isEmpty()){
            gameObjects.addAll(gameObjectsTOADD);
            gameObjectsTOADD.clear();
        }
        if (!gameObjectsToStartTOADD.isEmpty()){
            gameObjectsTOSTART.addAll(gameObjectsToStartTOADD);
            gameObjectsToStartTOADD.clear();
        }
        if(!gameObjectsTOSTART.isEmpty()){
            for(GameObject go : gameObjectsTOSTART){
                go.Mstart();
            }
            gameObjectsTOSTART.clear();
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
        //if (Game.getInstance().getLightmap() != null){

        //    Game.getInstance().getLightmap().clear();
            // multiply the light canva of the game instance with g to get the final image
        //    Graphics2D g2d = (Graphics2D) g;
        //    g2d.drawImage(Game.getInstance().getLightmap(), 0,0,null);
        //}


    }

    public ArrayList<GameObject> getGameObjects(){
        return this.gameObjects;
    }

    public void update(double dt){

    }
    public void Mstart(){

        this.start();
        if (!gameObjectsToStartTOADD.isEmpty()){
            gameObjectsTOSTART.addAll(gameObjectsToStartTOADD);
            gameObjectsToStartTOADD.clear();
        }
        if(!gameObjectsTOSTART.isEmpty()){
            for(GameObject go : gameObjectsTOSTART){
                go.Mstart();
            }
            gameObjectsTOSTART.clear();
        }

    }
    public void start(){
    }

    public void Mend(){
        for(GameObject go : getGameObjects()){
            go.Mend();
        }
        this.end();
    }

    public void end() {

    }


}
