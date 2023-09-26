import java.awt.*;

public abstract class DrawableComponent extends Component {

    public DrawableComponent(GameObject parent) {
        super(parent);
    }

    public void Mdraw(Graphics g) {
        draw(g);
    }

    public abstract void  draw(Graphics g);



    @Override
    public void start() {

    }

    @Override
    public void update(double dt) {

    }
}
