package Gava;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class Input {



    public static int ValueMappingOffset = 200;
    public static int ValueMappingOffsetImpulse = 500;
    private static Input instance = null;

    private InputMappingManager inputMappingManager = new InputMappingManager();


    private final GavaKeyListener keyListener = new GavaKeyListener();
    private final GavaMouseListener mouseListener = new GavaMouseListener();

    private static boolean mousePositionAlreadyCalculated = false;

    private static Vector2D mousePosition;

    public GavaMouseListener getMouseListener() {
        return mouseListener;
    }

    public GavaKeyListener getKeyListener() {
        return keyListener;
    }

    private HashMap<String,Boolean> booleanInput;

    private Input(){
        booleanInput = inputMappingManager.buildBooleanInput();
    }


    public static boolean get(String inputName){
        return getInstance().booleanInput.get(inputName);
    }

    public void registerBooleanEvent(int keyCode,boolean value){
        getInstance().booleanInput.put(inputMappingManager.match(keyCode),value);
    }

    public static Input getInstance(){
        if (instance == null){
            instance = new Input();
        }
        return instance;
    }

    public void frameReset(){
        mousePositionAlreadyCalculated = false;
        for(EventEntry e : inputMappingManager.getImpulsiveEntry()){
            registerBooleanEvent(e.getKey(),false);
        }
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

}
