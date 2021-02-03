package domain;
import java.util.List;

import data.Drink;

import java.util.ArrayList;

public class Command {
    int controllerID;
    int coffeeID;
    int orderID;
    Drink drink;
    String requestType;
    List<Option> options;

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
