package Gava;

import Gava.DefaultGameObjects.FPSdisplay;
import Gava.DefaultGameObjects.GameObjectsDisplay;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Debug {

    public static void log(String message){
        System.out.println(message);
    }

    private static Map<String,Boolean> debugs = constructMap();

    private static Map<String,Boolean> constructMap(){
        Map<String,Boolean> map = new HashMap<>();
        map.put("fps",false);
        map.put("GameObjects",false);

        return map;
    }

    public static boolean getDebugOpt(String opt){
        return debugs.get(opt);
    }

    public static void setDebugOpt(String opt,boolean value){
        debugs.put(opt,value);
    }


}
