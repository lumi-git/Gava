package Gava;

import java.awt.*;

public class Input {
    private static Input instance = null;
    private static final GavaKeyListener keyListener = new GavaKeyListener();
    private static final GavaMouseListener mouseListener = new GavaMouseListener();

    private static boolean mousePositionAlreadyCalculated = false;

    private static Vector2D mousePosition;

    public GavaMouseListener getMouseListener() {
        return mouseListener;
    }

    public GavaKeyListener getKeyListener() {
        return keyListener;
    }

    private Input(){

    }

    public static Input getInstance(){
        if (instance == null){
            instance = new Input();
        }
        return instance;
    }

    public void frameReset(){
        mouseListener.frameReset();
        mousePositionAlreadyCalculated = false;
    }

    public static Vector2D getMousePosition(){

        if(!mousePositionAlreadyCalculated){
            Point p = MouseInfo.getPointerInfo().getLocation();
            mousePosition = new Vector2D(p.getX() - Game.getInstance().getLocationOnScreen().getX(), p.getY()-Game.getInstance().getLocationOnScreen().getY());
            mousePositionAlreadyCalculated = true;
            return mousePosition;
        }
        return mousePosition;

    }

    public static boolean isKeyPressed(int keyCode){
        return GavaKeyListener.isKeyPressed(keyCode);
    }

    public static boolean isMousePressed(int buttonCode){
        return GavaMouseListener.isMouseButtonPressed(buttonCode);
    }

    public static boolean isMouseClicked(int buttonCode){
        return GavaMouseListener.isMouseButtonClicked(buttonCode);
    }

}
