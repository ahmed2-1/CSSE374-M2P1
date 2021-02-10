# CSSE374-M2P2

## Testing
All system tests are in **APIOutput_Tests.java**
The test code will test sample input from the app in the files **order#.json**. These are as follows:

- testAdvancedSuccess tests success on advanced machine
- testSimpleSuccess tests success on the simple machine
- testAssignmentFailure tests failure on assignment for an advanced and a programmable controller
- testSimpleMachineJam tests failure on simple machine functionality
- testProgrammableCoffeeMachineSuccess tests success on programmable machines for multiple instruction sets

Each of these is paired with a corresponding **controller-response#.json** which models a sample response a controller might send.

The two outputs of the system are **controller-command-output.json** which models the json file that would be sent to the coffee controllers to make a coffee and **api-user-response-output.json** which is the json file sent to the mobile application for user feedback.

### How to change the test cases
You can change the inputs by modifying or creating new **order#.json** files and creating an associated test case that calls 'testWithInputs' in APIOutput_Tests.java. The `"orderID"` property specifies which number of **controller-response#.json** the system reads. So long as these numbers are consistent, you can test any combination of orders and controller responses. By making expected result files, you can compare the output with what is expected by calling `testWithInputs(...)` from the **APIOutput_Tests.java** file.