package domain;
import java.io.*;
import java.util.NoSuchElementException;

import com.github.cliftonlabs.json_simple.*;

import data.Command;
import data.Machine;
import data.MachineDatabase;
import data.DrinkResponse;
import data.Order;
import data.UserResponse;
import presentation.PortWatcher;

public class API implements PortObserver {

    ControllerProcessor controllerProcessor;
    ResponseProcessor responseProcessor;
    OrderProcessor orderProcessor;
    
    public API(PortWatcher portWatcher, MachineDatabase database) {
        
        MachineDatabase controllerDatabase = database;
        
        orderProcessor = new OrderProcessor(controllerDatabase);
        controllerProcessor = new ControllerProcessor(controllerDatabase);
        responseProcessor =  new ResponseProcessor();
        portWatcher.subscribe(this);
        
    }

    @Override
    public void recieveOrder(Order order) {
        
        UserResponse userResponse;
        Command command = null;
        try {
        	command = orderProcessor.processOrder(order);
        	Machine target = controllerProcessor.getAssignedController(command);
            
            DrinkResponse machineResponse = target.trySendCommand(command);
            userResponse = responseProcessor.processResponse(machineResponse, command.controllerID);
        } catch (NoSuchElementException e) {
        	userResponse = responseProcessor.createNoValidMachineResponse(order.orderID);
        }
        
        sendUserResponse(userResponse);
        
    }

    private void sendUserResponse(UserResponse userResponse) {
        
        JsonObject json = new JsonObject();
        JsonObject inner = new JsonObject();
        
        json.put("user-response", inner);
        inner.put("orderID", userResponse.orderID);
        inner.put("coffee_machine_id", userResponse.coffeeID);
        inner.put("status", userResponse.status);
        inner.put("status-message", userResponse.statusMessage);
        if(userResponse.status != 0) {
            inner.put("error-message", userResponse.errorMessage);
        }
        
        BufferedWriter writer;
        try {
        	String str = Jsoner.serialize(json);
            writer = new BufferedWriter(new FileWriter("src/jsons/api-user-response-output.json"));
            writer.write(str);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
