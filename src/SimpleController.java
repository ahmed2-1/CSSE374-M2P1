import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

public class SimpleController extends Controller {
	
	public SimpleController(int id, String address) {
	    super(id, address);
	}
	
	public DrinkResponse recieveCommand(Command command) throws TimeoutException {
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
		
    	JsonObject input = new JsonObject();
    	try {
			FileReader fr = new FileReader("src/jsons/controller-response1.json");
			input = (JsonObject) Jsoner.deserialize(fr);
			fr.close();
		} catch (Exception e) {
			System.out.println("File read error, make sure that the order exists and is formatted as a JSON object.");
			e.printStackTrace();
		}
    	
    	DrinkResponse response = convertFromJson(input);
		return response;
	}

	@Override
    boolean canProcessCondiments(List<Option> condiments) {
        return condiments.isEmpty();
    }
}
