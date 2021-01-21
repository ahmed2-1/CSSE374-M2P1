
public class SimpleController implements Controller {
	private int counter;
	
	SimpleController() {
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
		counter++;
		return response;
		
	}
}
