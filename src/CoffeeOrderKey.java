import com.github.cliftonlabs.json_simple.JsonKey;

public enum CoffeeOrderKey implements JsonKey{
    ORDERID ("orderID"),
    ADDRESS ("address"),
    DRINK ("drink"),
    CONDIMENTS("condiments");
    
    private final String key;
    
    CoffeeOrderKey(String key) {
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
