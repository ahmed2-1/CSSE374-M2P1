package domain;
import java.util.List;
import java.util.ArrayList;

public class Command {
	int controllerID;
	int coffeeID;
	int orderID;
	String drinkName;
	String requestType;
	List<Option> options;
	
	public Command(int controllerID, int coffeeID, int orderID, String drinkName, String requestType) {
		this.controllerID = controllerID;
		this.coffeeID = coffeeID;
		this.orderID = orderID;
		this.drinkName = drinkName;
		this.requestType = requestType;
		this.options = new ArrayList<Option>();
	}
	
	public Command(int controllerID, int coffeeID, int orderID, String drinkName, String requestType, List<Option> options) {
		this.controllerID = controllerID;
		this.coffeeID = coffeeID;
		this.orderID = orderID;
		this.drinkName = drinkName;
		this.requestType = requestType;
		this.options = options;
	}
}
