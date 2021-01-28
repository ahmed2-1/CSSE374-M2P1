import java.util.List;
import java.util.ArrayList;

public class ControllerDatabase {
    
    public int findCompatibleController(String address, int zip, List<Option> condiments) {
        //TODO
        return 1;
    }
    
    public List<Controller> getAllControllers() {
        
        List<Controller> allControllers = new ArrayList<Controller>();
        
        //TODO: link to file IO
        allControllers.add(new SimpleController());
        allControllers.add(new AdvancedController());
        
        return allControllers;
    }
    
}
