package Gava.utility;

import Gava.*;
import Gava.DefaultComponent.ColliderComponent;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class SpacialhashMap extends CollisionSystem{


    ConcurrentHashMap<Integer, ArrayList<ColliderComponent>> map = new ConcurrentHashMap<>();
    ConcurrentHashMap<String,Boolean> alreadyCollided = new ConcurrentHashMap<>();
    ConcurrentHashMap<ColliderComponent,ArrayList<Integer>> CellMap = new ConcurrentHashMap<>();
    int colls = 0;
    private final ForkJoinPool customThreadPool = new ForkJoinPool(4);


    int cellSize;
    //only with cell size
    public SpacialhashMap(int cellSize){
        this.cellSize = cellSize;
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

    public void insert(ColliderComponent go) {
        ArrayList<Integer> cells = getCells(go);
        for (int cell : cells) {
            map.computeIfAbsent(cell, k -> new ArrayList<>()).add(go);
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

    public ArrayList<Integer> getCells(ColliderComponent go) {
        if (CellMap.containsKey(go)) {
            return CellMap.get(go);
        }
        Transform transform = go.getParent().getReadonlyTransform();
        int x = (int) (transform.getPosition().x / cellSize);
        int y = (int) (transform.getPosition().y / cellSize);
        int x2 = (int) ((transform.getPosition().x + transform.getScale().x) / cellSize);
        int y2 = (int) ((transform.getPosition().y + transform.getScale().y) / cellSize);

        Set<Integer> cellSet = new HashSet<>();
        for (int i = x; i <= x2; i++) {
            for (int j = y; j <= y2; j++) {
                cellSet.add(getCellHash(i, j));
            }
        }
        ArrayList<Integer> cells = new ArrayList<>(cellSet);
        CellMap.put(go, cells);
        return cells;
    }

    public int getCellHash(int x, int y){
        return x*31393 +( y * 6353);
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
        alreadyCollided.put(getKeyForPair(go1,go2),true);
        alreadyCollided.put(go2.getParent().getName() + go1.getParent().getName(),true);
    }

    public boolean checkCollisionHash(ColliderComponent go1, ColliderComponent go2){
        return alreadyCollided.containsKey(getKeyForPair(go1,go2));
    }

    String getKeyForPair(ColliderComponent go1, ColliderComponent go2) {
        return go1.getParent().getName().compareTo(go2.getParent().getName()) < 0 ?
                go1.getParent().getName() + go2.getParent().getName() :
                go2.getParent().getName() + go1.getParent().getName();
    }

    public void update(){
        colls = 0;

        // Convert your map values to a parallel stream and process
        ForkJoinTask<?> t =  customThreadPool.submit(() ->
                map.values().parallelStream().forEach(list ->
                        list.forEach(this::CheckCollisions)
                )
        );
        t.join();


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
