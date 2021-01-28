package test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import org.junit.jupiter.api.Test;

import data.ControllerDatabase;
import domain.API;
import presentation.PortWatcher;

class APIOutput_Tests {

    private final String controllerCommandPath = "src/jsons/controller-command-output.json";
    private final String userResponsePath = "src/jsons/api-user-response-output.json";
    
    public String readControllerCommandOutput() {
        return readFile(controllerCommandPath);
    }
    
    public String readUserResponseOutput() {
        return readFile(userResponsePath);
    }

    private String readFile(String path) {
        BufferedReader input = null;
        try {
            input = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail("Failed to open file " + path);
            return "";
        }
        
        String result = "";
        String in;
        try {
            in = input.readLine();
            
            while(in != null) {
                result += in;
                in = input.readLine();
            }
            
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
            fail("Failed to read file " + path);
        }

        return result;
    }
    

    void testWithInputs(String orderInPath, String expectedControllerCommandPath, String expectedUserResponsePath, String databasePath) {
     
        String orderIn = readFile(orderInPath);
        String controllerResponse = readFile(databasePath); //specified by path, unused
        String expectedControllerCommand = readFile(expectedControllerCommandPath);
        String expectedUserResponse = readFile(expectedUserResponsePath);
        
        ControllerDatabase database = new ControllerDatabase(databasePath);
        PortWatcher watcher = new PortWatcher(800);
        API api = new API(watcher, database);
        
        watcher.processInput(orderInPath);
        
        assertEquals(expectedControllerCommand, readControllerCommandOutput(), "Expected command sent to controller differs from actual");
        assertEquals(expectedUserResponse, readUserResponseOutput(), "Expected response sent to user differs from actual");
        
    }
    
    @Test
    void testAdvancedSuccess() {
        //TODO: make not refer to itself
        testWithInputs("src/jsons/order1.json", controllerCommandPath, userResponsePath, "src/data/controllers.txt");
    }
    
    @Test
    void testSimpleSuccess() {
        //TODO: make not refer to itself
        testWithInputs("src/jsons/order2.json", controllerCommandPath, userResponsePath, "src/data/controllers.txt");
    }
    
    @Test 
    void testSimpleMachineJam() {
    	testWithInputs("src/jsons/order3.json", controllerCommandPath, userResponsePath, "src/data/controllers.txt");
    }
    
    @Test
    void testAdvancedMachineJam() {
    	testWithInputs("src/jsons/order4.json", controllerCommandPath, userResponsePath, "src/data/controllers.txt");
    }


}
