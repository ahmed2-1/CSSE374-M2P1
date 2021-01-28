import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.Scanner;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

public class PortWatcher {

    private int port;
    private Scanner scanner;
    private PortObserver observer;
    private int currentOrder;
    
    public PortWatcher(int port, PortObserver observer) {
        this.port = port;
        this.observer = observer;
        scanner = new Scanner(System.in);
        currentOrder = 0;
    }
    
    public void listen() {
        while(processInput());
    }
    
    private boolean processInput() {
    	
    	JsonObject input = new JsonObject();
    	try {
			FileReader fr = new FileReader("src/jsons/order1.json");
			input = (JsonObject) Jsoner.deserialize(fr);
		} catch (Exception e) {
			System.out.println("Ya done goofed");
			e.printStackTrace();
			return false;
		}
    	
    	System.out.println(input.toString());
    	Order order = convertFromJson(input);
    	observer.recieveOrder(order);
    	System.out.println();
    	System.out.println("Continue? (y/n)");
    	String cont = scanner.nextLine();
    	return cont.equals("y");
    	
    }
    
    private Order convertFromJson(JsonObject obj) {
    	JsonObject details = (JsonObject) obj.getMap(OrderKey.ORDER);
    	Integer orderId = details.getInteger(OrderKey.ORDERID);
    	
    	JsonObject addressObj = (JsonObject) details.getMap(OrderKey.ADDRESS);
    	String address = addressObj.getString(OrderKey.STREET);
    	Integer zipcode = addressObj.getInteger(OrderKey.ZIP);
    	
    	String drink = details.getString(OrderKey.DRINK);
    	
    	
    	return new Order(orderId, address, zipcode, drink);
    }
    
}
