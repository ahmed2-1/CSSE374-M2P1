import java.util.HashMap;

public class JSONObject {

    private String command;
    private HashMap<String, String> keys;
    
    public JSONObject() {
        this.keys = new HashMap<String, String>();
    }
    
    public void setCommand(String command) {
        this.command = command;
    }
    
    public String getCommand() {
        return command;
    }
    
    public void addValue(String key, String value) {
        keys.put(key, value);
    }
    
    public void getValue(String key) {
        keys.get(key);
    }
    
    
}
