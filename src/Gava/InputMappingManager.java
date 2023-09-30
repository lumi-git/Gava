package Gava;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InputMappingManager {

    static String[] floatInputArray = {"horizontal","vertical","mouseX","mouseY"};
    private ArrayList<EventEntry> impulsiveEntry = new ArrayList<EventEntry>();
    public HashMap<String,Boolean> buildBooleanInput(){
        // take the values of inputMapping and build a hashmap with all the values set to false
        HashMap<String,Boolean> map = new HashMap<String,Boolean>();
        for(Map.Entry<Integer,String> entry : inputMapping){
            Debug.log(entry.getValue());
            Debug.log(entry.getKey()+"");
            map.put(entry.getValue(),false);
        }
        return map;
    }

    public ArrayList<EventEntry> getImpulsiveEntry(){
        return impulsiveEntry;
    }

    public static HashMap<String,Float> buildFloatInput(){
        HashMap<String,Float> map = new HashMap<String,Float>();
        for(String s : floatInputArray){
            map.put( s,0f);
        }
        return map;
    }
    public ArrayList<EventEntry> inputMapping ;

    private Map<Integer,String> mappingMap;

    public InputMappingManager(){
        Init();
    }

    public void Init(){
        inputMapping = new ArrayList<EventEntry>();
        inputMapping.add(new EventEntry(KeyEvent.VK_Z,"up"));
        inputMapping.add(new EventEntry(KeyEvent.VK_S,"down"));
        inputMapping.add(new EventEntry(KeyEvent.VK_Q,"left"));
        inputMapping.add(new EventEntry(KeyEvent.VK_D,"right"));
        inputMapping.add(impulse(new EventEntry(KeyEvent.VK_SPACE,"jump")));
        inputMapping.add(new EventEntry(KeyEvent.VK_E,"interact"));
        inputMapping.add(new EventEntry(KeyEvent.VK_ESCAPE,"pause"));
        inputMapping.add(new EventEntry(KeyEvent.VK_ENTER,"enter"));
        inputMapping.add(new MouseEventEntry(MouseEvent.BUTTON1,"leftFire"));
        inputMapping.add(new MouseEventEntry(MouseEvent.BUTTON3,"rightFire"));

        mappingMap = buildMappingMap();
    }


    private Map<Integer,String> buildMappingMap(){

        HashMap<Integer,String> map = new HashMap<Integer,String>();
        for(Map.Entry<Integer,String> entry : inputMapping){
            map.put(entry.getKey(),entry.getValue());
        }
        return map;
    }


    public String match(int keyCode){
        String output = mappingMap.get(keyCode);
        if (output == null){
            return "None";
        }
        else{
            return output;
        }
    }

    public EventEntry impulse(EventEntry eventEntry){
        EventEntry e = new EventEntry(eventEntry.getKey()+ Input.ValueMappingOffsetImpulse,eventEntry.getValue());
        impulsiveEntry.add(e);
        return e;
    }


}
