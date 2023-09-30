package Gava;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class GavaKeyListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
        Input.getInstance().registerBooleanEvent(-(e.getKeyCode()+Input.ValueMappingOffsetImpulse),true);
    }

    @Override
    public void keyPressed(KeyEvent e) {

        Input.getInstance().registerBooleanEvent(e.getKeyCode(),true);

    }

    @Override
    public void keyReleased(KeyEvent e) {

        Input.getInstance().registerBooleanEvent(e.getKeyCode(),false);
    }
}
