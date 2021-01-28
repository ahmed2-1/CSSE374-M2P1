import java.util.*;

public class ControllerProcessor {

    private ControllerDatabase database;
    
    public ControllerProcessor(ControllerDatabase database) {
        this.database = database;
    }
    
    public Controller getAssignedController(Command command) {
        return database.getControllerById(command.controllerID);
    }
    
}
