package Gava;

public class MouseEventEntry extends EventEntry{
    public MouseEventEntry(Integer key, String value) {
        super(-(key+Input.ValueMappingOffset), value);
    }
}
