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
        Point p = new Point(0,0);
        if(!mousePositionAlreadyCalculated){
            try{
                p = MouseInfo.getPointerInfo().getLocation();
                mousePosition = new Vector2D(p.getX()  - Game.getInstance().getLocationOnScreen().getX() +  Game.getInstance().getCamera().getPosition().x , p.getY()-Game.getInstance().getLocationOnScreen().getY() + Game.getInstance().getCamera().getPosition().y);
            }
            catch (Exception e){
                return new Vector2D(0,0);
            }

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
