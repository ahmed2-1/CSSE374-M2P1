import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;

import com.github.cliftonlabs.json_simple.JsonObject;

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
        
        JsonObject json = new JsonObject();
        
        json.put("orderID", userResponse.orderID);
        json.put("coffee_machine_id", userResponse.coffeeID);
        json.put("status", userResponse.status);
        json.put("status-message", userResponse.statusMessage);
        if(userResponse.status != 0) {
            json.put("error-message", userResponse.errorMessage);
        }
        
        String str = json.toString();
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter("src/jsons/api-user-response-output.json"));
            writer.write(str);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
