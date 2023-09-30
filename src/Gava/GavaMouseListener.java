package Gava;

import java.awt.event.*;
import java.util.ArrayList;

class GavaMouseListener implements MouseListener, MouseWheelListener, MouseMotionListener {



    public GavaMouseListener(){

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Input.getInstance().registerBooleanEvent(-(Input.ValueMappingOffset + e.getButton()),true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Input.getInstance().registerBooleanEvent(-(Input.ValueMappingOffset + e.getButton()),false);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

    }
}
