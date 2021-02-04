package data;
import java.util.List;
import java.util.ArrayList;

public class Command {
    public int controllerID;
    public int coffeeID;
    public int orderID;
    public Drink drink;
    public String requestType;
    public List<Option> options;

    public Command(int controllerID, int coffeeID, int orderID, Drink drink, String requestType) {
        this(controllerID, coffeeID, orderID, drink, requestType, new ArrayList<Option>());
    }

    public Command(int controllerID, int coffeeID, int orderID, Drink drink, String requestType, List<Option> options) {
        this.controllerID = controllerID;
        this.coffeeID = coffeeID;
        this.orderID = orderID;
        this.drink = drink;
        this.requestType = requestType;
        this.options = options;
    }
}
