package Gava;

import java.util.Map;

public class EventEntry implements Map.Entry<Integer, String> {
    private Integer key;
    private String value;

    EventEntry(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public Integer getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String setValue(String value) {
        this.value = value;
        return value;
    }
}
