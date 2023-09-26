import java.awt.*;

public class defaultCube extends GameObject{
    @Override
    public void start() {
        this.addDrawableComponent(new DrawRectComponent(100, 100, Color.red,this));
    }
    @Override
    public void update(double dt) {
        getTransform().getPosition().x +=1;
        getTransform().getPosition().y +=1;
        if (getTransform().getPosition().x > 100){
            this.destroy();
        }
    }

}
