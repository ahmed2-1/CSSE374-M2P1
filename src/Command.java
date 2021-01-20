import java.util.ArrayList;

public class Command {
	int controllerID;
	int coffeeID;
	int orderID;
	String drinkName;
	String requestType;
	ArrayList<Option> options;
	
	public Command(int controllerID, int coffeeID, int orderID, String drinkName, String requestType) {
		this.controllerID = controllerID;
		this.coffeeID = coffeeID;
		this.orderID = orderID;
		this.drinkName = drinkName;
		this.requestType = requestType;
		this.options = new ArrayList<>();
	}
	
	public Command(int controllerID, int coffeeID, int orderID, String drinkName, String requestType, ArrayList<Option> options) {
		this.controllerID = controllerID;
		this.coffeeID = coffeeID;
		this.orderID = orderID;
		this.drinkName = drinkName;
		this.requestType = requestType;
		this.options = options;
	}
}
