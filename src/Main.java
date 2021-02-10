import data.MachineDatabase;
import domain.API;
import presentation.PortWatcher;

public class Main {

    public static void main(String args[]) {
        MachineDatabase database = new MachineDatabase("src/data/controllers.txt");
        PortWatcher watcher = new PortWatcher(800);
        API api = new API(watcher, database);
        watcher.listen();
    }
    
}
