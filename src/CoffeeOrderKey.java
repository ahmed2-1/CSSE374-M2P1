import com.github.cliftonlabs.json_simple.JsonKey;

public enum CoffeeOrderKey implements JsonKey{
    ORDER ("order"),
    ORDERID ("orderID"),
    ADDRESS ("address"),
    STREET ("street"),
    ZIP ("ZIP"),
    DRINK ("drink"),
    CONDIMENTS("condiments"),
    NAME ("name"),
    QUANTITY ("qty");
    
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