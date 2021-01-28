import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import com.github.cliftonlabs.json_simple.*;

public class PortWatcher {

    private int port;
    private Scanner scanner;
    private PortObserver observer;
    private int currentOrder;
    
    public PortWatcher(int port) {
        this.port = port;
        scanner = new Scanner(System.in);
        currentOrder = 0;
    }
    
    public void listen() {
        while(processInput());
        scanner.close();
    }
    
    private boolean processInput() {
    	currentOrder++;
    	JsonObject input = new JsonObject();
    	try {
			FileReader fr = new FileReader(String.format("src/jsons/order%d.json", currentOrder));
			input = (JsonObject) Jsoner.deserialize(fr);
			fr.close();
		} catch (Exception e) {
			System.out.println("File read error, make sure that the order exists and is formatted as a JSON object.");
			e.printStackTrace();
			return false;
		}
    	
    	Order order = convertFromJson(input);
    	observer.recieveOrder(order);
    	System.out.println("Order Recieved");
    	System.out.println("Continue? (y/n)");
    	String cont = scanner.nextLine();
    	System.out.println();
    	return cont.equals("y");
    	
    }
    
    private Order convertFromJson(JsonObject obj) {
    	JsonObject details = (JsonObject) obj.getMap(OrderKey.ORDER);
    	Integer orderId = details.getInteger(OrderKey.ORDERID);
    	
    	JsonObject addressObj = (JsonObject) details.getMap(OrderKey.ADDRESS);
    	String address = addressObj.getString(OrderKey.STREET);
    	Integer zipcode = addressObj.getInteger(OrderKey.ZIP);
    	
    	String drink = details.getString(OrderKey.DRINK);
    	
    	JsonArray condiments = (JsonArray) details.getCollection(OrderKey.CONDIMENTS);
    	ArrayList<Option> options = new ArrayList<>();
    	
    	if (condiments != null) { //Need this here to catch is there are no condiments
	    	for (Object o : condiments) {
	    		JsonObject cond = (JsonObject) o;
	    		String name = cond.getString(OrderKey.NAME);
	    		int qty = cond.getInteger(OrderKey.QUANTITY);
	    		options.add(new Option(name, qty));
	    	}
    	}
    	
    	
    	return new Order(orderId, address, zipcode, drink, options);
    }
    
    public void subscribe(PortObserver observer) {
        this.observer = observer;
    }
    
}
