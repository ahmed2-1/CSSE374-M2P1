import java.util.concurrent.TimeoutException;

public abstract class Controller {
	
    private final int timeoutSeconds = 30;
    
    private int id;
    private String address;
    
    public Controller(int id, String address) {
        this.id = id;
        this.address = address;
    }
    
    public DrinkResponse trySendCommand(Command command) {
        
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
	
	
}
