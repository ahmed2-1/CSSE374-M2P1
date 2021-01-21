import java.util.*;

public class ControllerProcessor {

    private ArrayList<Controller> controllers;
    
    public ControllerProcessor() {
        controllers = new ArrayList<Controller>();
        populateControllers();
    }
    
    public Controller getAssignedController(Command command) {
        return controllers.get(command.controllerID - 1);
    }
    
    private void populateControllers() {
        
        controllers.add(new SimpleController());
        
    }
    
}
