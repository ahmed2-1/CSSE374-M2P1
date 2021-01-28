package test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import org.junit.jupiter.api.Test;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

import data.ControllerDatabase;
import domain.API;
import presentation.PortWatcher;

class APIOutput_Tests {

    private final String controllerCommandPath = "src/jsons/controller-command-output.json";
    private final String userResponsePath = "src/jsons/api-user-response-output.json";
    
    public JsonObject readControllerCommandOutput() {
        return readFile(controllerCommandPath);
    }
    
    public JsonObject readUserResponseOutput() {
        return readFile(userResponsePath);
    }

    private JsonObject readFile(String path) {
        BufferedReader input = null;
        try {
            input = new BufferedReader(new FileReader(path));
            return  (JsonObject) Jsoner.deserialize(input);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failed to process file " + path);
            return null;
        }
        
    }
    

    //Controller response is located at src/jsons/controller-response%d, where %d is the orderid of the input order
    void testWithInputs(String orderInPath, String expectedControllerCommandPath, String expectedUserResponsePath, String databasePath) {
        
        ControllerDatabase database = new ControllerDatabase(databasePath);
        PortWatcher watcher = new PortWatcher(800);
        API api = new API(watcher, database);
        
        watcher.processInput(orderInPath);
        
        JsonObject expectedControllerCommand = readFile(expectedControllerCommandPath);
        JsonObject expectedUserResponse = readFile(expectedUserResponsePath);
        
        
        assertEquals(expectedControllerCommand, readControllerCommandOutput(), "Expected command sent to controller differs from actual");
        assertEquals(expectedUserResponse, readUserResponseOutput(), "Expected response sent to user differs from actual");
        
    }
    
    @Test
    void testAdvancedSuccess() {
        //TODO: make not refer to itself
        testWithInputs("src/jsons/order1.json", controllerCommandPath, userResponsePath, "src/data/test-controllers1.txt");
    }
    
    @Test
    void testSimpleSuccess() {
        //TODO: make not refer to itself
        testWithInputs("src/jsons/order2.json", controllerCommandPath, userResponsePath, "src/data/test-controllers1.txt");
    }
    
    @Test
    void testAssignmentFailure() {
        //controller command is not sent, does not need to be tested so it is compared to itself
        testWithInputs("src/jsons/order1.json", controllerCommandPath, "src/jsons/user-response3.json", "src/data/test-controllers2.txt");
    }

}
