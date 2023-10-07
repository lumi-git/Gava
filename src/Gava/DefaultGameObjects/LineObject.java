package Gava.DefaultGameObjects;
import Gava.Game;
import Gava.GameObject;
import Gava.Vector2D;
import java.awt.*;
import java.util.ArrayList;

public class LineObject extends GameObject{

    Color color = Color.red;

    ArrayList<GameObject> objects = new ArrayList<>();

    public GameObject getObject(int index){
        return objects.get(index);
    }

    public void addObject(GameObject go){
        objects.add(go);

    }

    public LineObject(GameObject go1, GameObject go2){
        this.addObject(go1);
        this.addObject(go2);
        this.addDrawableComponent(new Gava.DrawableComponents.DrawLineComponent(this));
    }

    public LineObject(){
        this.addObject(Game.Instantiate(new Gava.DefaultGameObjects.VoidGameObject()));
        this.addObject(Game.Instantiate(new Gava.DefaultGameObjects.VoidGameObject()));
        this.addDrawableComponent(new Gava.DrawableComponents.DrawLineComponent(this));
    }

    public LineObject(Vector2D pos1, Vector2D pos2){
        GameObject go1 = Game.Instantiate(new Gava.DefaultGameObjects.VoidGameObject());
        GameObject go2 = Game.Instantiate(new Gava.DefaultGameObjects.VoidGameObject());
        go1.getModificationTransform().setPosition(pos1);
        go2.getModificationTransform().setPosition(pos2);
        this.addObject(go1);
        this.addObject(go2);
        this.addDrawableComponent(new Gava.DrawableComponents.DrawLineComponent(this));
    }

    public void setA(GameObject go){
        this.objects.set(0,go);
    }

    public void setB(GameObject go){
        this.objects.set(1,go);
    }

    public void setPosA(Gava.Vector2D pos){
        this.objects.get(0).getModificationTransform().setPosition(pos);
    }

    public void setPosB(Gava.Vector2D pos){
        this.objects.get(1).getModificationTransform().setPosition(pos);
    }

    public void setColor(Color color){
        this.color = color;
    }

    public Color getColor(){
        return color;
    }

}
