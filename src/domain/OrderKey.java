package domain;
import com.github.cliftonlabs.json_simple.JsonKey;

public enum OrderKey implements JsonKey {
    
    ORDER ("order"),
    ORDERID ("orderID"),
    ADDRESS ("address"),
    STREET ("street"),
    ZIP ("ZIP"),
    DRINK ("drink"),
    CONDIMENTS("condiments"),
    NAME ("name"),
    QUANTITY ("qty"),
    RECIPE ("Recipe"),
    COMMANDSTEP ("commandstep"),
    OBJECT ("object");
    
    private final String key;
    
    OrderKey(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public Object getValue() {
        return "";
    }

}
