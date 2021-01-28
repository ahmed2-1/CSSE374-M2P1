package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

import org.junit.jupiter.api.Test;

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
    

    void testWithInputs(String orderInPath, String expectedControllerCommandPath, String controllerResponsePath, String expectedUserResponsePath) {
     
        String orderIn = readFile(orderInPath);
        String controllerResponse = readFile(controllerResponsePath);
        String expectedControllerCommand = readFile(expectedControllerCommandPath);
        String expectedUserResponse = readFile(expectedUserResponsePath);
        
        //TODO: run actual api stuff
        
        assertEquals(expectedControllerCommand, readControllerCommandOutput());
        assertEquals(expectedUserResponse, readUserResponseOutput());
        
    }
    
    @Test
    void testExample() {
        //always passes, compares output to itself
        testWithInputs("src/jsons/order1.json", controllerCommandPath, "src/jsons/controller-response1.json", userResponsePath);
    }

}
