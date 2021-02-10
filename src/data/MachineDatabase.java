package data;
import java.io.*;
import java.util.*;

public class MachineDatabase {
    
    List<Machine> controllers;
    Map<String, MachineFactory> factories;
    
    public MachineDatabase(String connectionString) {
        controllers = new ArrayList<Machine>();
        
        factories = new HashMap<String, MachineFactory>();
        factories.put("SIMPLE", new SimpleMachineFactory());
        factories.put("ADVANCED", new AdvancedMachineFactory());
        factories.put("PROGRAMMABLE", new ProgrammableMachineFactory());
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
                    Machine newController = factories.get(type).createController();
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
        
        for(Machine c : controllers) {
            if(c.getAddress().equals(address + " " + zip)) {
                if(c.canProcessCondiments(drink.getCondiments()) && c.canProcessSteps(drink.getSteps())) {
                    return c.getId();
                }
            }
        }
        
        throw new NoSuchElementException("No compatible controller");
    }
    
    public List<Machine> getAllControllers() {
        return controllers;
    }

    public Machine getControllerById(int controllerID) {
        return controllers.get(controllerID-1);
    }
    
}
