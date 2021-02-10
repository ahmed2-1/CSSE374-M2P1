package domain;
import java.util.*;

import data.Command;
import data.Machine;
import data.MachineDatabase;

public class MachineProcessor {

    private MachineDatabase database;
    
    public MachineProcessor(MachineDatabase database) {
        this.database = database;
    }
    
    public Machine getAssignedController(Command command) {
        return database.getControllerById(command.controllerID);
    }
    
}
