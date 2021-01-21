
public interface PortObserver {

    public void recieveOrder(Order order);
    public void recieveCommand(Command command);
    public void recieveUserResponse(UserResponse response);
    public void recieveDrinkResponse(DrinkResponse response);
    
}
