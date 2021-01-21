
public class API implements PortObserver {

    PortWatcher portWatcher;
    
    public API() {
        
        portWatcher = new PortWatcher(800, this);
        portWatcher.listen();
        
    }

    @Override
    public void recieveOrder(Order order) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void recieveCommand(Command command) {
        // TODO Auto-generated method stub
        
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
