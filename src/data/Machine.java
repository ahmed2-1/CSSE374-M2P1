package data;

import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.TimeoutException;

import com.github.cliftonlabs.json_simple.JsonObject;

import domain.DrinkResponseKey;

public abstract class Machine {



    private int id;
    private int controllerId;
    private String address;

    public Machine() {

    }

    public String getAddress() {
        return address;
    }

    public int getId() {
        return id;
    }
    
    public int getControllerId() {
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

    public boolean canProcessCondiments(List<Option> condiments) {
        return condiments.isEmpty();
    }

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
    
    public void setControllerID(int newId) {
        this.controllerId = newId;
      }
    
    public void setAddress(String newAddress) {
        this.address = newAddress;
    }

    public boolean canProcessSteps(ArrayList<ArrayList<String>> steps) {
        return steps.isEmpty();
    }


}
