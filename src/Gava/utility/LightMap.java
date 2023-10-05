package Gava.utility;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class LightMap extends BufferedImage {
    //thread this class
    Graphics2D gfx;

    private int globalLightening= 0;

    Kernel kernel = new Kernel(5, 5,
            new float[] {
                    1f/25f, 1f/25f, 1f/25f, 1f/25f, 1f/25f,
                    1f/25f, 1f/25f, 1f/25f, 1f/25f, 1f/25f,
                    1f/25f, 1f/25f, 1f/25f, 1f/25f, 1f/25f,
                    1f/25f, 1f/25f, 1f/25f, 1f/25f, 1f/25f,
                    1f/25f, 1f/25f, 1f/25f, 1f/25f, 1f/25f,});
    BufferedImageOp op = new ConvolveOp(kernel);

    Color Background = new Color(globalLightening, globalLightening, globalLightening, 0 );

    public Color getBackgroundColor(){
        return Background;
    }

    public LightMap(int width, int height) {
        super(width, height, BufferedImage.TYPE_INT_ARGB);
        gfx = (Graphics2D) getGraphics();

    }

    public void clear(){
        gfx.setColor(Background);
        gfx.fillRect(0,0,getWidth(),getHeight());
    }

    public void setGlobalLightening(int lightening){
        globalLightening = lightening;
        Background = new Color(globalLightening, globalLightening, globalLightening, 100 );
    }


    public void blur(){
        gfx.drawImage(op.filter(this, null),0,0,null);
    }

}
