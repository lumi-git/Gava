package Gava;

import java.awt.event.*;
import java.util.ArrayList;

class GavaMouseListener implements MouseListener, MouseWheelListener, MouseMotionListener {

    public static final Boolean[] mouseButtons = new Boolean[4];

    public static boolean isMouseButtonPressed(int ButtonCode){
        return mouseButtons[ButtonCode];
    }



    public GavaMouseListener(){
        for (int i = 0; i < mouseButtons.length; i++) {
            mouseButtons[i] = false;
        }
    }
    public Boolean[] getMouseButtons() {
        return mouseButtons;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseButtons[e.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseButtons[e.getButton()] = false;
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
