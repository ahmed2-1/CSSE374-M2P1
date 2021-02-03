package domain;

import java.util.List;

import java.util.concurrent.TimeoutException;

import com.github.cliftonlabs.json_simple.JsonObject;

public abstract class Controller {



    private int id;
    private String address;

    public Controller() {

    }

    public String getAddress() {
        return address;
    }

    public int getId() {
        return id;
    }

    public DrinkResponse trySendCommand(Command command) {

        DrinkResponse response = null;

        try {
            response = recieveCommand(command);
        } catch (TimeoutException e) {
            response = new DrinkResponse(command.orderID, 1, 2, "Timed out");
        }


        return response;
    }

    abstract DrinkResponse recieveCommand(Command command) throws TimeoutException;

    public abstract boolean canProcessCondiments(List<Option> condiments);

    public DrinkResponse convertFromJson(JsonObject input) {
        DrinkResponse response;
        JsonObject details = (JsonObject) input.getMap(DrinkResponseKey.DRINKRESPONSE);

        Integer orderID = details.getInteger(DrinkResponseKey.ORDERID);
        Integer status = details.getInteger(DrinkResponseKey.STATUS);

        if (status != 0) {
            String errorDesc =  details.getString(DrinkResponseKey.ERRORDESC);
            Integer errorCode = details.getInteger(DrinkResponseKey.ERRORCODE);

            response = new DrinkResponse(orderID, status, errorCode, errorDesc);
        }
        else {
            response = new DrinkResponse(orderID, status);
        }

        return response;
    }

    public void setID(int newId) {
      this.id = newId;
    }
    
    public void setAddress(String newAddress) {
        this.address = newAddress;
    }


}
