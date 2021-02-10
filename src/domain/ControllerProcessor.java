package domain;
import java.util.*;

import data.Command;
import data.Machine;
import data.MachineDatabase;

public class ControllerProcessor {

    private MachineDatabase database;
    
    public ControllerProcessor(MachineDatabase database) {
        this.database = database;
    }
    
    public Machine getAssignedController(Command command) {
        return database.getControllerById(command.controllerID);
    }
    
}
