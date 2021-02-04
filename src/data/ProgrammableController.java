package data;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

public class ProgrammableController extends Controller {
	
	private List<Option> condiments;
	private List<String> ingredients; //TODO: Change in M3P2 to use the decorator pattern

	public ProgrammableController() {
		//TODO: Change the constructor to work with the factory
		super();
		condiments = new ArrayList<>();
		ingredients = new ArrayList<>();
	}

	@Override
	DrinkResponse recieveCommand(Command command) throws TimeoutException {
	    JsonObject json = new JsonObject();

        json.put("controller_id", command.controllerID);
        json.put("coffee_machine_id", command.coffeeID);
        json.put("orderID", command.orderID);
        json.put("DrinkName", command.drink.getDrinkName());
        json.put("Requesttype", command.requestType);

        JsonArray ar = new JsonArray();
        for (int i = 0;i < command.options.size();i++) {
            JsonObject j = new JsonObject();
            j.put("Name", command.options.get(i).name);
            j.put("qty", command.options.get(i).qty);
            ar.add(j);
        }
        json.put("Options", ar);

        JsonObject cj = new JsonObject();
        cj.put("command", json);

        String str = Jsoner.serialize(cj);
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter("src/jsons/controller-command-output.json"));
            writer.write(str);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonObject input = new JsonObject();
        try {
            FileReader fr = new FileReader(String.format("src/jsons/controller-response%d.json", command.orderID));
            input = (JsonObject) Jsoner.deserialize(fr);
            fr.close();
        } catch (Exception e) {
            System.out.println("File read error, make sure that the Command exists and is formatted as a JSON object.");
            e.printStackTrace();
            return new DrinkResponse(command.orderID ,1, 5, "File Read Error");
        }

        DrinkResponse response = convertFromJson(input);
        return response;    
	}

	@Override
	public boolean canProcessCondiments(List<Option> condiments) {
		return true;
	}
	
	@Override
    public boolean canProcessSteps(ArrayList<ArrayList<String>> steps) {
	    return true;
	}

}
