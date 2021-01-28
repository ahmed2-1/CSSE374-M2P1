import java.util.NoSuchElementException;

public class OrderProcessor {
	
    private ControllerDatabase database;
    
	public OrderProcessor(ControllerDatabase database) {
	    
	    this.database = database;
	    
	}
	
	public Command processOrder(Order order) {
	    int controllerID = database.findCompatibleController(order.getStreetAddress(),  order.getZipcode(), order.getCondiments());
	    if (controllerID == -1) {
	    	throw new NoSuchElementException();
	    }
	    if (order.getCondiments().isEmpty())
	    	return new Command(controllerID, 1, order.getOrderID(), order.getDrink(), "Simple");
	    else
	    	return new Command(controllerID, 1, order.getOrderID(), order.getDrink(), "Automated", order.getCondiments());
	}
}
