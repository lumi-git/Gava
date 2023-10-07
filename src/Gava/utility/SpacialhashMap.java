package Gava.utility;

import Gava.*;
import Gava.DefaultComponent.ColliderComponent;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class SpacialhashMap extends CollisionSystem{

    HashMap<Integer, ArrayList<ColliderComponent>> map;
    HashMap<String,Boolean> alreadyCollided;

    HashMap<ColliderComponent,ArrayList<Integer>> CellMap;
    int colls = 0;

    int cellSize;
    //only with cell size
    public SpacialhashMap(int cellSize){
        this.cellSize = cellSize;
        map = new HashMap<>();
        alreadyCollided = new HashMap<>();
        CellMap = new HashMap<>();
    }

    public int getCollsCount(){
        return colls;
    }

    public int getTheoricalCollsCount(){
        int count = 0;
        for (ArrayList<ColliderComponent> list: map.values()) {
            count += list.size();
        }
        return count;
    }

    public void insert(ColliderComponent go){

        ArrayList<Integer> cells = getCells(go);
        for ( int cell :cells){
            if(map.containsKey(cell)){
                if(map.get(cell).contains(go)){
                    continue;
                }
                map.get(cell).add(go);
            }else{
                ArrayList<ColliderComponent> list = new ArrayList<ColliderComponent>();
                list.add(go);
                map.put(cell,list);
            }
        }

    }

    public void remove(ColliderComponent go){
        ArrayList<Integer> cells = getCells(go);
        for(int cell : cells){
            if(map.containsKey(cell)){
                map.get(cell).remove(go);
            }
        }

    }

    public void clear(){
         map.clear();
    }

    public ArrayList<Integer> getCells(ColliderComponent go){
        if( CellMap.containsKey(go)){
            return CellMap.get(go);
        }
        Transform transform = go.getParent().getReadonlyTransform();
        int x = (int) (transform.getPosition().x/cellSize);
        int y = (int) (transform.getPosition().y/cellSize);
        int x2 = (int) ((transform.getPosition().x + transform.getScale().x)/cellSize);
        int y2 = (int) ((transform.getPosition().y + transform.getScale().y)/cellSize);
        ArrayList<Integer> cells = new ArrayList<Integer>();
        for (int i = x; i <= x2; i++) {
            for (int j = y; j <= y2; j++) {
                if (cells.contains(getCellHash(i,j))){
                    continue;
                }
                cells.add(getCellHash(i,j));
            }
        }
        CellMap.put(go,cells);
        return cells;

    }

    public int getCellHash(int x, int y){
        return x +( y * Game.getInstance().getScreenWidth());
    }

    public Set<ColliderComponent> query(ColliderComponent go){
        Set<ColliderComponent> set = new java.util.HashSet<>();
        ArrayList<Integer> cells = getCells(go);
        for(int cell : cells){
            if(map.containsKey(cell)){
                set.addAll(map.get(cell));
            }
        }
        return set;
    }

    public void CheckCollisions(ColliderComponent go){
        Set<ColliderComponent> list = query(go);
        // get a set out of the list
        for (ColliderComponent g: list) {
            colls +=1;
            if (g == go) {
                continue;
            }
            if(checkCollisionHash(go,g)){
                continue;
            }

            addCollisionHash(go,g);

            if(checkAABBCollision(go,g)){

                go.CollisionPreUpdate();
                go.onCollision(g);
                go.CollisionPostUpdate();

                g.CollisionPreUpdate();
                g.onCollision(go);
                g.CollisionPostUpdate();
            }
        }
    }

    public void addCollisionHash(ColliderComponent go1, ColliderComponent go2){
        alreadyCollided.put(go1.getParent().getName() + go2.getParent().getName(),true);
        alreadyCollided.put(go2.getParent().getName() + go1.getParent().getName(),true);
    }

    public boolean checkCollisionHash(ColliderComponent go1, ColliderComponent go2){
        return alreadyCollided.containsKey(go1.getParent().getName() + go2.getParent().getName()) || alreadyCollided.containsKey(go2.getParent().getName() + go1.getParent().getName());
    }

    public void update(){
        colls = 0;
        for (ArrayList<ColliderComponent> list: map.values()) {
            for (ColliderComponent go: list) {
                CheckCollisions(go);
            }
        }
        CellMap.clear();
        alreadyCollided.clear();

    }

    public void draw(Graphics g){
        Camera camera = Game.getInstance().getCamera();
        g.setColor(Color.red);

        for (int i = Game.getInstance().getScreenHeight()/cellSize ;i>=0 ;i--){
            int y = (int)((i*cellSize - camera.getPosition().y)%Game.getInstance().getScreenHeight());
            g.drawLine(0,y,Game.getInstance().getScreenWidth(),y);
        }

        for (int i = Game.getInstance().getScreenWidth()/cellSize ;i>=0 ;i--){
            int x = (int)((i*cellSize - camera.getPosition().x)%Game.getInstance().getScreenWidth());
            g.drawLine(x,0,x,Game.getInstance().getScreenHeight());
        }
    }

    public boolean checkAABBCollision(ColliderComponent a, ColliderComponent b) {
        // Assuming ColliderComponent has methods to get the top, bottom, left, and right edges.
        return a.getParent().getReadonlyTransform().Intersect(b.getParent().getReadonlyTransform());
    }


}
