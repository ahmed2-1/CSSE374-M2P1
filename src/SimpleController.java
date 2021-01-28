import java.util.List;
import java.util.concurrent.TimeoutException;

import com.github.cliftonlabs.json_simple.JsonObject;

public class SimpleController extends Controller {
	private int counter;
	
	public SimpleController(int id, String address) {
	    super(id, address);
		counter = 0;
	}
	
	public DrinkResponse recieveCommand(Command command) throws TimeoutException {
		
	    
		DrinkResponse response;
		response = new DrinkResponse(command.orderID, 0);
			
	
		JsonObject json = new JsonObject();
		
		json.put("controller_id", command.controllerID);
	    json.put("coffee_machine_id", command.coffeeID);
	    json.put("orderID", command.orderID);
	    json.put("DrinkName", command.drinkName);
	    json.put("Requesttype", command.requestType);
		
		counter++;
		return response;
		
	}

    @Override
    boolean canProcessCondiments(List<Option> condiments) {
        return condiments.isEmpty();
    }
}
