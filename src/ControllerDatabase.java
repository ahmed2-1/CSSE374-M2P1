import java.io.*;
import java.util.*;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

public class ControllerDatabase {
    
    List<Controller> controllers;
    
    public ControllerDatabase(String connectionString) {
        
        populateControllers(connectionString);

    }
    
    private void populateControllers(String connectionString) {
        // TODO Auto-generated method stub
        
        BufferedReader fr;
        try {
            
            fr = new BufferedReader(new FileReader(connectionString));
            
            int currentId = 1;
            String line = fr.readLine();
            while(line != null) {
                
                String[] details = line.split(";");
                String type = details[0];
                String address = details[0];
                
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        
    }

    public int findCompatibleController(String address, int zip, List<Option> condiments) {
        //TODO
        return 1;
    }
    
    public List<Controller> getAllControllers() {
        return controllers;
    }
    
}
