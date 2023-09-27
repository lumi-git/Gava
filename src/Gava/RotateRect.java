package Gava;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class RotateRect {
    public static Shape rotate(Rectangle rect, double angle) {
        Rectangle2D myRect = new Rectangle2D.Double(rect.x, rect.y, rect.width, rect.height);
        return AffineTransform.getRotateInstance(angle - 90, rect.x + rect.width / 2, rect.y + rect.height / 2).createTransformedShape(myRect);
    }
}
