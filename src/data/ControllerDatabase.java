package data;
import java.io.*;
import java.util.*;

import domain.AdvancedController;
import domain.Controller;
import domain.Option;
import domain.SimpleController;

public class ControllerDatabase {
    
    List<Controller> controllers;
    
    public ControllerDatabase(String connectionString) {
        controllers = new ArrayList<Controller>();
        populateControllers(connectionString);
    }
    
    private void populateControllers(String connectionString) {
        
        BufferedReader fr;
        try {
            
            fr = new BufferedReader(new FileReader(connectionString));
            
            int currentId = 1;
            String line = fr.readLine();
            while(line != null) {
                
                String[] details = line.split(";");
                String type = details[0];
                String address = details[1];
                
                if(type.equals("SIMPLE")) {
                    controllers.add(new SimpleController(currentId, address));
                    currentId++;
                } else if(type.equals("ADVANCED")) {
                    controllers.add(new AdvancedController(currentId, address));
                    currentId++;
                }
                
                line = fr.readLine();
                
            }
            
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
    }

    public int findCompatibleController(String address, int zip, List<Option> condiments) {
        
        for(Controller c : controllers) {
            if(c.getAddress().equals(address + " " + zip)) {
                if(c.canProcessCondiments(condiments)) {
                    return c.getId();
                }
            }
        }
        
        throw new NoSuchElementException("No compatible controller");
    }
    
    public List<Controller> getAllControllers() {
        return controllers;
    }

    public Controller getControllerById(int controllerID) {
        return controllers.get(controllerID-1);
    }
    
}
