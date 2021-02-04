package data;
import java.io.*;
import java.util.List;
import java.util.concurrent.TimeoutException;

import com.github.cliftonlabs.json_simple.*;

public class SimpleController extends Controller {

    public SimpleController() {
        super();
    }

    public DrinkResponse recieveCommand(Command command) throws TimeoutException {
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
}
