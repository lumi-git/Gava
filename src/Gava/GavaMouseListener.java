package Gava;

import java.awt.event.*;
import java.util.ArrayList;

class GavaMouseListener implements MouseListener, MouseWheelListener, MouseMotionListener {

    public static final Boolean[] mouseButtonsPressed = new Boolean[4];
    public static final Boolean[] mouseButtonsClicked = new Boolean[4];

    public static boolean isMouseButtonPressed(int ButtonCode){
        return mouseButtonsPressed[ButtonCode];
    }

    public static boolean isMouseButtonClicked(int ButtonCode){return mouseButtonsClicked[ButtonCode];}

    public void frameReset(){
        for (int i = 0; i < mouseButtonsClicked.length; i++) {
            mouseButtonsClicked[i] = false;
        }

    }

    public GavaMouseListener(){
        for (int i = 0; i < mouseButtonsClicked.length; i++) {
            mouseButtonsClicked[i] = false;
        }
        for (int i = 0; i < mouseButtonsPressed.length; i++) {
            mouseButtonsPressed[i] = false;
        }
    }
    public Boolean[] getMouseButtons() {
        return mouseButtonsPressed;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        mouseButtonsClicked[e.getButton()] = true;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseButtonsPressed[e.getButton()] = true;

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseButtonsPressed[e.getButton()] = false;
        mouseButtonsClicked[e.getButton()] = true;
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
