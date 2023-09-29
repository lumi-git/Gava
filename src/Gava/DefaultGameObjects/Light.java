package Gava.DefaultGameObjects;

import Gava.DrawableComponents.LightComponent;
import Gava.Game;
import Gava.GameObject;

import java.awt.*;

public class Light extends GameObject {
    private int intensity = 255 ;
    private Color color = Color.white;
    private int rayon = 100;
    public Light(int LightIntensity,int Lightrayon,Color LightColor) {
        rayon = Lightrayon;
        intensity = LightIntensity;
        color = LightColor;
    }
    public Light(){
    }
    @Override
    public void start(){
        if (parent != null){
            getModificationTransform().setScale(parent.getReadonlyTransform().getScale());
        }
        addDrawableComponent(new LightComponent(this,intensity,rayon,color));
    }
}
