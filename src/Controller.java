import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.github.cliftonlabs.json_simple.JsonObject;

public abstract class Controller {
	
    private final int timeoutSeconds = 30;
    
    public DrinkResponse trySendCommand(Command command) {
        
    	JsonObject result = new JsonObject();
    	DrinkResponse response = null;
    	
    	
        //DrinkResponse result;
        try {
        	
            response = recieveCommand(command);
            result.put("orderID", response.orderID);
            result.put("status", response.status);
            
        } catch (TimeoutException e) {
        	result = new JsonObject();
        	result.put("orderID", (Integer)command.orderID);
			result.put("status", 0);
        }
        
        if (response.errorCode != 0) {
        	result.put("errordesc", response.errorDesc);
        	result.put("errorcode", response.errorCode);
        }
        
        String str = result.toString();
        BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter("DrinkResponseJson"));
			writer.write(str);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
       
        
        return response;
        
    }
    
	abstract DrinkResponse recieveCommand(Command command) throws TimeoutException;
	
	
}
