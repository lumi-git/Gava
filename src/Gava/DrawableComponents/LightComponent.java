package Gava.DrawableComponents;

import Gava.*;
import Gava.utility.LightMap;

import java.awt.*;

public class LightComponent extends DrawableComponent {

    private int intensity;
    private int rayon;
    private Color color;

    public LightComponent(GameObject parent,int intensity,int rayon,Color color) {
        super(parent);

        this.intensity = intensity;
        this.rayon = rayon;
        this.color = color;
        this.color = new Color(color.getRed(),color.getGreen(),color.getBlue(), (int) this.intensity);
    }
    float[] dist = {0.0f, 1.0f};

    @Override
    public void draw(Graphics g) {
        Color[] colors = {color, new Color(0,0,0,0) };
        Camera camera = Game.getInstance().getCamera();
        Graphics2D gcv = getGraphics2D(camera, colors);
        gcv.fillOval((int) (parent.getReadonlyTransform().getCenteredPosition().x - (camera.getPosition().x) - rayon/2 ),
                (int) (parent.getReadonlyTransform().getCenteredPosition().y - ( camera.getPosition().y)  - rayon/2),(int)rayon,(int)rayon);
    }

    private Graphics2D getGraphics2D(Camera camera, Color[] colors) {
        Graphics2D gcv = (Graphics2D) Game.getInstance().getLightmap().getGraphics();
        gcv.setComposite(AlphaComposite.DstOut);
        gcv.setColor(color);

        RadialGradientPaint p =
                new RadialGradientPaint(new Point((int)( parent.getReadonlyTransform().getCenteredPosition().x - camera.getPosition().x  ),
                        (int) (parent.getReadonlyTransform().getCenteredPosition().y - camera.getPosition().y )), (int)rayon/2, dist, colors, MultipleGradientPaint.CycleMethod.NO_CYCLE);
        gcv.setPaint(p);
        return gcv;
    }
}
