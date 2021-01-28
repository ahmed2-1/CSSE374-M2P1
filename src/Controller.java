
import java.io.*;
import java.util.List;

import java.util.concurrent.TimeoutException;

public abstract class Controller {
	
    private final int timeoutSeconds = 30;
    
    private int id;
    private String address;
    
    public Controller(int id, String address) {
        this.id = id;
        this.address = address;
    }
    
    public String getAddress() {
        return address;
    }
    
    public int getId() {
        return id;
    }
    
    public DrinkResponse trySendCommand(Command command) {
        
    	DrinkResponse response = null;

        try {
            response = recieveCommand(command);

            
        } catch (TimeoutException e) {
            //make timeout error and put in response
        }
        

        return response;
    }
    
	abstract DrinkResponse recieveCommand(Command command) throws TimeoutException;
	
	abstract boolean canProcessCondiments(List<Option> condiments);
	
	
}
