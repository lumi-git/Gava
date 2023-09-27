package Gava;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class GavaKeyListener implements KeyListener {

    public static boolean[] keys = new boolean[1000];

    public static boolean isKeyPressed(int keyCode){
        return keys[keyCode];
    }

    @Override
    public void keyTyped(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;

    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;

    }
}
