
public class UserResponse {
	int orderID;
	int coffeeID;
	int status;
	String statusMessage;
	String errorMessage;
	
	public UserResponse(int orderID, int coffeeID, int status) {
		this.orderID = orderID;
		this.coffeeID = coffeeID;
		this.status = status;
		this.statusMessage = "Your coffee has been prepared with your desired options.";
		this.errorMessage = "";
	}
	
	public UserResponse(int orderID, int coffeeID, int status, String errorMessage) {
		this.orderID = orderID;
		this.coffeeID = coffeeID;
		this.status = status;
		this.statusMessage = "Your coffee order has been cancelled.";
		this.errorMessage = errorMessage;
	}
	
	public String toString() {
	    return String.format("orderID: %d\nStatus Message: %s\nError: %s" , orderID, status, errorMessage);
	}
}
