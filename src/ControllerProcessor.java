import java.util.*;

public class ControllerProcessor {

    private ArrayList<Controller> controllers;
    
    public ControllerProcessor() {
        controllers = new ArrayList<Controller>();
    }
    
    public Controller getAssignedController(Command command) {
        return controllers.get(command.controllerID);
    }
    
}
