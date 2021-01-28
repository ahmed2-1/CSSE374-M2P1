import java.util.*;

public class ControllerProcessor {

    private List<Controller> controllers;
    private ControllerDatabase database;
    
    public ControllerProcessor(ControllerDatabase database) {
        this.database = database;
        populateControllers();
    }
    
    public Controller getAssignedController(Command command) {
        return controllers.get(command.controllerID - 1);
    }
    
    private void populateControllers() {
        
        this.controllers = database.getAllControllers();
        
    }
    
}
