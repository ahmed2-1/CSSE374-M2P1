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
        try {
            return  (JsonObject) Jsoner.deserialize(new FileReader(path));
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


        compareCommand(expectedControllerCommand, readControllerCommandOutput());
        compareUserResponse(expectedUserResponse, readUserResponseOutput());

    }

    private void compareCommand(JsonObject expected, JsonObject actual) {

        try {
            JsonObject innerExpected = (JsonObject) expected.get("command");
            JsonObject innerActual = (JsonObject) actual.get("command");

            assertEquals(innerExpected.get("controller_id"), innerActual.get("controller_id"));
            assertEquals(innerExpected.get("coffee_machine_id"), innerActual.get("coffee_machine_id"));
            assertEquals(innerExpected.get("orderID"), innerActual.get("orderID"));
            assertEquals(innerExpected.get("DrinkName"), innerActual.get("DrinkName"));
            assertEquals(innerExpected.get("Requesttype"), innerActual.get("Requesttype"));

        } catch (Exception e) {
            fail("Exception occurred during command comparison");
        }

    }

    private void compareUserResponse(JsonObject expected, JsonObject actual) {

        try {
            JsonObject innerExpected = (JsonObject) expected.get("user-response");
            JsonObject innerActual = (JsonObject) actual.get("user-response");

            assertEquals(innerExpected.get("orderID"), innerActual.get("orderID"), "Incorrect orderID");
            assertEquals(innerExpected.get("coffee_machine_id"), innerActual.get("coffee_machine_id"), "Incorrect coffee_machine_id");
            assertEquals(innerExpected.get("status"), innerActual.get("status"), "Incorrect status");
            assertEquals(innerExpected.get("status-message"), innerActual.get("status-message"), "Incorrect status-message");
            assertEquals(innerExpected.get("error-message"), innerActual.get("error-message"), "Incorrect error-message");

        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception occured during user response comparison");
        }
    }

    @Test
    void testAdvancedSuccess() {
        testWithInputs("src/jsons/order1.json", "src/jsons/command1.json", "src/jsons/user-response1.json", "src/data/test-controllers1.txt");
    }

    @Test
    void testSimpleSuccess() {
        testWithInputs("src/jsons/order2.json", "src/jsons/command2.json", "src/jsons/user-response2.json", "src/data/test-controllers1.txt");
    }

    @Test
    void testAssignmentFailure() {
        //controller command is not sent, does not need to be tested so it is compared to itself
        testWithInputs("src/jsons/order5.json", controllerCommandPath, "src/jsons/user-response5.json", "src/data/test-controllers2.txt");
    }

    @Test 
    void testSimpleMachineJam() {
        testWithInputs("src/jsons/order3.json", "src/jsons/command3.json", "src/jsons/user-response3.json", "src/data/test-controllers1.txt");
    }

    @Test
    void testAdvancedMachineJam() {
        testWithInputs("src/jsons/order4.json", "src/jsons/command4.json", "src/jsons/user-response4.json", "src/data/test-controllers1.txt");
    }
    
    @Test
    void testProgrammableCoffeeMachineSuccess() {
        testWithInputs("src/jsons/order6.json", "src/jsons/command6.json", "src/jsons/user-response6.json", "src/data/test-controllers3.txt");
    }


}
