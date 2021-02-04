package domain;
import java.util.*;

import data.Command;
import data.Controller;
import data.ControllerDatabase;

public class ControllerProcessor {

    private ControllerDatabase database;
    
    public ControllerProcessor(ControllerDatabase database) {
        this.database = database;
    }
    
    public Controller getAssignedController(Command command) {
        return database.getControllerById(command.controllerID);
    }
    
}
