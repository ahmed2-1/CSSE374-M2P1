package domain;

public class ResponseProcessor {

    public ResponseProcessor() {
    }

    public UserResponse processResponse(DrinkResponse response, int coffeeID) {	
        int drinkStatus = response.getStatus();
        UserResponse output;
        if(drinkStatus == 0) {
            output = new UserResponse(response.getOrderID(), coffeeID, drinkStatus);
        }
        else {
            output = new UserResponse(response.getOrderID(), coffeeID, drinkStatus, response.getErrorDesc());
        }
        return output;
    }

    public UserResponse createNoValidMachineResponse(int orderID) {
        return new UserResponse(orderID, -1, 404, "There is no coffee machine that can service your order.");
    }
}
