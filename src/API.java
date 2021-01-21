
public class API implements PortObserver {

    ControllerProcessor controllerProcessor;
    OrderProcessor orderProcessor;
    PortWatcher portWatcher;
    
    public API() {
        
        orderProcessor = new OrderProcessor();
        controllerProcessor = new ControllerProcessor();
        
        portWatcher = new PortWatcher(800, this);
        portWatcher.listen();
        
    }

    @Override
    public void recieveOrder(Order order) {
        
        Command command = orderProcessor.processOrder(order);
        Controller target =  controllerProcessor.getAssignedController(command);
        //TODO: apply command to target
    }

    @Override
    public void recieveUserResponse(UserResponse response) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void recieveDrinkResponse(DrinkResponse response) {
        // TODO Auto-generated method stub
        
    }
    
}
