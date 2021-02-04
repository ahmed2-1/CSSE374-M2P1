package data;
import java.io.*;
import java.util.*;

public class ControllerDatabase {
    
    List<Controller> controllers;
    Map<String, ControllerFactory> factories;
    
    public ControllerDatabase(String connectionString) {
        controllers = new ArrayList<Controller>();
        
        factories = new HashMap<String, ControllerFactory>();
        factories.put("SIMPLE", new SimpleControllerFactory());
        factories.put("ADVANCED", new AdvancedControllerFactory());
        factories.put("PROGRAMMABLE", new ProgrammableControllerFactory());
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
                
                try {
                    Controller newController = factories.get(type).createController();
                    newController.setID(currentId);
                    newController.setAddress(address);
                    
                    controllers.add(newController);
                    currentId++;
                }
                catch(NullPointerException e) {

                }
                
                line = fr.readLine();
                
            }
            
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public int findCompatibleController(String address, int zip, Drink drink) {
        
        for(Controller c : controllers) {
            if(c.getAddress().equals(address + " " + zip)) {
                if(c.canProcessCondiments(drink.getCondiments()) && c.canProcessSteps(drink.getSteps())) {
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
