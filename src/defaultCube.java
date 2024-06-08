import Gava.*;
import Gava.DefaultComponent.CollideBoxComponent;
import Gava.DrawableComponents.DrawRectComponent;
import Gava.DrawableComponents.DrawSpriteComponent;

import java.awt.*;
import java.awt.event.KeyEvent;

public class defaultCube extends GameObject {
    double speed = 2;

    public defaultCube(Vector2D position){

        this.getModificationTransform().setPosition(position);
    }

    @Override
    public void start() {
        this.addComponent(new CollideBoxComponent(this));
        this.addDrawableComponent(new DrawRectComponent(Color.red,this));
        this.addDrawableComponent(new DrawSpriteComponent("Logo.png",this));
        this.getModificationTransform().setScale(new Vector2D(100,100));

    }
    @Override
    public void update(double dt) {

        smoothMove();
        getDrawableComponent(DrawRectComponent.class).setColor(Color.black);

    }

    private void preciseMove(){
        Vector2D mousePos = Input.getMousePosition();
        getModificationTransform().setPosition(mousePos);
    }

    private void smoothMove(){
        Vector2D mousePos = Input.getMousePosition();
        Vector2D direction = mousePos.subtract(getReadonlyTransform().getCenteredPosition());

        if(direction.y == 0)
            direction.y=0.00000001;

        if (direction.x == 0)
            direction.x=0.00000001;

        getModificationTransform().setRotation( Math.atan2(direction.y,direction.x));


        if  (Input.isMouseClicked(1)){
            destroy();
        }

    }

    @Override
    public void onCollisionEnter(CollisionInformation collisionInformation) {
        System.out.println("collision");
        this.destroy();
    }

    @Override
    public void end() {
        Debug.log("destroyed");
    }
}
