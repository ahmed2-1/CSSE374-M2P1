
public class OrderProcessor {
	
	public OrderProcessor() {
	}
	
	public Command processOrder(Order order) {
		return new Command(1, 1, order.getOrderID(), order.getDrink(), "Simple");
	}
}
