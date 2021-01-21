
public class AdvancedController extends Controller {
	private int counter;
	
	AdvancedController() {
		counter = 0;
	}
	
	public DrinkResponse recieveCommand(Command command) {
		/*
		boolean fail = false;
		if (counter % 5 == 0) {
			fail = true;
		}
		
		
		DrinkResponse response;
		if (fail) {
			response = new DrinkResponse(command.orderID, 0, 20, "Machine Jammed");
		} else {
			response = new DrinkResponse(command.orderID, 0);
		}
		*/
		DrinkResponse response = new DrinkResponse(command.orderID, 0);
		
		return response;
		
	}
}
