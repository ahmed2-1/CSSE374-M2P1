import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;

public class SimpleController extends Controller {
	
	public SimpleController(int id, String address) {
	    super(id, address);
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
	    
	    JsonArray ar = new JsonArray();
	    for (int i = 0;i < command.options.size();i++) {
	    	JsonObject j = new JsonObject();
	    	j.put("Name", command.options.get(i).name);
	    	j.put("qty", command.options.get(i).qty);
	    	ar.add(j);
	    }
	    json.put("Options", ar);
	    
	    String str = json.toString();
        BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter("DrinkResponseJson"));
			writer.write(str);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	    String str = json.toString();
        BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter("DrinkResponseJson"));
			writer.write(str);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    //F
	    
		return response;
	}

    @Override
    boolean canProcessCondiments(List<Option> condiments) {
        return condiments.isEmpty();
    }
}
