import java.util.concurrent.TimeoutException;

public abstract class Controller {
	
    private final int timeoutSeconds = 30;
    
    public DrinkResponse trySendCommand(Command command) {
        
        DrinkResponse result;
        try {
            result = recieveCommand(command);
        } catch (TimeoutException e) {
            result = new DrinkResponse(command.orderID, 0, 1, "Timed out");
        }
        
        return result;
        
    }
    
	abstract DrinkResponse recieveCommand(Command command) throws TimeoutException;
	
	
}
