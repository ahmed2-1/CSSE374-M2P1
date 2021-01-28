import java.io.*;
import java.util.List;
import java.util.concurrent.TimeoutException;

import com.github.cliftonlabs.json_simple.JsonObject;

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
        
    	JsonObject commandJson = new JsonObject();
    	DrinkResponse response = null;
    	
    	
        //DrinkResponse result;
        try {
        	
            response = recieveCommand(command);
            //result.put("orderID", response.orderID);
            //result.put("status", response.status);
            
        } catch (TimeoutException e) {
        	//result = new JsonObject();
        	//result.put("orderID", (Integer)command.orderID);
			//result.put("status", 0);
        }
        
        if (response.errorCode != 0) {
        	//result.put("errordesc", response.errorDesc);
        	//result.put("errorcode", response.errorCode);
        }
        
       
        
        
       
        
        return response;
        
    }
    
	abstract DrinkResponse recieveCommand(Command command) throws TimeoutException;
	
	abstract boolean canProcessCondiments(List<Option> condiments);
	
	
}
