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
    	
    	
        
		System.out.println(String.format("Processing order %d", currentOrder));
    	System.out.println("Please enter a drink name:");
    	String drink = scanner.nextLine();
    	System.out.println("Please enter an address:");
    	String address = scanner.nextLine();
    	System.out.println("Please enter the zipcode:");
    	int zip = scanner.nextInt();
    	scanner.nextLine();
    	
    	Order o = new Order(currentOrder++, address, zip, drink);
    	
    	observer.recieveOrder(o);
    	System.out.println();
    	    	
        System.out.println("Continue? (y/n)");
        String cont = scanner.nextLine();
        return cont.equals("y");
    	
    }
    
}
