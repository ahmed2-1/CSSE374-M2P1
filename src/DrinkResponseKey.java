import com.github.cliftonlabs.json_simple.JsonKey;

public enum DrinkResponseKey implements JsonKey {

    DRINKRESPONSE ("drinkresponse"),
    ORDERID ("orderID"),
    STATUS ("status"),
    ERRORDESC ("errordesc"),
    ERRORCODE ("errorcode");
    
    private final String key;
    
    DrinkResponseKey(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public Object getValue() {
        return key;
    }
    
}
