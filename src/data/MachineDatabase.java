package data;
import java.io.*;
import java.util.*;

public class MachineDatabase {
    
    List<Machine> machines;
    Map<String, MachineFactory> factories;
    
    public MachineDatabase(String connectionString) {
        machines = new ArrayList<Machine>();
        
        factories = new HashMap<String, MachineFactory>();
        factories.put("SIMPLE", new SimpleMachineFactory());
        factories.put("ADVANCED", new AdvancedMachineFactory());
        factories.put("PROGRAMMABLE", new ProgrammableMachineFactory());
        populateMachines(connectionString); 
    }
    
    private void populateMachines(String connectionString) {
        
        BufferedReader fr;
        try {
            
            fr = new BufferedReader(new FileReader(connectionString));
            
            int currentId = 1;
            String line = fr.readLine();
            while(line != null) {
                
                String[] details = line.split(";");
                String controllerId = details[0];
                String type = details[1];
                String address = details[2];
                
                try {
                    Machine newMachine = factories.get(type).createMachine();
                    newMachine.setControllerID(Integer.parseInt(controllerId));
                    newMachine.setID(currentId);
                    newMachine.setAddress(address);
                    
                    machines.add(newMachine);
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

    public int findCompatibleMachine(String address, int zip, Drink drink) {
        
        for(Machine c : machines) {
            if(c.getAddress().equals(address + " " + zip)) {
                if(c.canProcessCondiments(drink.getCondiments()) && c.canProcessSteps(drink.getSteps())) {
                    return c.getId();
                }
            }
        }
        
        throw new NoSuchElementException("No compatible controller");
    }
    
    public List<Machine> getAllControllers() {
        return machines;
    }

    public Machine getMachineById(int machineID) {
        return machines.get(machineID-1);
    }

    public int getControllerIDOfMachine(int machineID) {
        return getMachineById(machineID).getControllerId();
    }
    
}
