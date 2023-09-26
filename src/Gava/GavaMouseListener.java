package Gava;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class GavaMouseListener implements MouseListener {

    private Boolean[] mouseButtons = new Boolean[4];

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

}
