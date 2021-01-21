import java.util.concurrent.TimeoutException;

public class SimpleController extends Controller {
	private int counter;
	
	SimpleController() {
		counter = 0;
	}
	
	public DrinkResponse recieveCommand(Command command) throws TimeoutException {
		
	    
		DrinkResponse response;
		if (counter % 3 == 1) {
			response = new DrinkResponse(command.orderID, 1, 20, "Machine Jammed");
		}
		else if (counter % 3 == 2) {
		    throw new TimeoutException();
		}
		else {
			response = new DrinkResponse(command.orderID, 0);
		}
		
		//DrinkResponse response = new DrinkResponse(command.orderID, 0);
		counter++;
		return response;
		
	}
}
