import com.github.cliftonlabs.json_simple.JsonObject;

public class API implements PortObserver {

    ControllerProcessor controllerProcessor;
    ResponseProcessor responseProcessor;
    OrderProcessor orderProcessor;
    PortWatcher portWatcher;
    
    public API() {
        
        orderProcessor = new OrderProcessor();
        controllerProcessor = new ControllerProcessor();
        responseProcessor =  new ResponseProcessor();
        
        portWatcher = new PortWatcher(800, this);
        portWatcher.listen();
        
    }

    @Override
    public void recieveOrder(Order order) {
        
        UserResponse userResponse;
        Command command = orderProcessor.processOrder(order);
        Controller target = controllerProcessor.getAssignedController(command);
        
        
        DrinkResponse machineResponse = target.trySendCommand(command);
        
        //DrinkResponse machineResponse = target.trySendCommand(command);
        userResponse = responseProcessor.processResponse(machineResponse, command.coffeeID);
        
        sendUserResponse(userResponse);
    }

    private void sendUserResponse(UserResponse userResponse) {
        System.out.println(userResponse);
    }
    
}
