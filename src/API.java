
public class API implements PortObserver {

    PortWatcher portWatcher;
    
    public API() {
        
        portWatcher = new PortWatcher(800, this);
        portWatcher.listen();
        
    }
    
    public void recieveMessage(String message) {
        JSONObject parsedJson = new JSONObject();
        
        //TODO: setup object
        
        
    }
    
}
