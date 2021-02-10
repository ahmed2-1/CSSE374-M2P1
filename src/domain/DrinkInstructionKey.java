package domain;
import com.github.cliftonlabs.json_simple.JsonKey;

public enum DrinkInstructionKey implements JsonKey {
    
    MIX ("mix"),
    STEAM ("steam"),
    TOP ("top"),
    ADD ("add");
    
    private final String key;
    
    DrinkInstructionKey(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
    
    public String toString() {
        return getKey();
    }

    @Override
    public Object getValue() {
        return null;
    }

}
