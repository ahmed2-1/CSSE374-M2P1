import java.util.NoSuchElementException;

public class API implements PortObserver {

    ControllerProcessor controllerProcessor;
    ResponseProcessor responseProcessor;
    OrderProcessor orderProcessor;
    PortWatcher portWatcher;
    
    public API() {
        
        ControllerDatabase controllerDatabase = new ControllerDatabase("src/db/controllers.txt");
        
        orderProcessor = new OrderProcessor(controllerDatabase);
        controllerProcessor = new ControllerProcessor(controllerDatabase);
        responseProcessor =  new ResponseProcessor();
        
        portWatcher = new PortWatcher(800, this);
        portWatcher.listen();
        
    }

    @Override
    public void recieveOrder(Order order) {
        
        UserResponse userResponse;
        Command command = null;
        try {
        	command = orderProcessor.processOrder(order);
        	Controller target = controllerProcessor.getAssignedController(command);
            
            DrinkResponse machineResponse = target.trySendCommand(command);
            userResponse = responseProcessor.processResponse(machineResponse, command.coffeeID);
        } catch (NoSuchElementException e) {
        	userResponse = responseProcessor.createNoValidMachineResponse(order.orderID);
        }
        
        sendUserResponse(userResponse);
        
    }

    private void sendUserResponse(UserResponse userResponse) {
        System.out.println(userResponse);
    }
    
}
