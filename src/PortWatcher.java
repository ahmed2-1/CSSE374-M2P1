import java.util.Scanner;

public class PortWatcher {

    private int port;
    private Scanner scanner;
    private PortObserver observer;
    
    public PortWatcher(int port, PortObserver observer) {
        this.port = port;
        this.observer = observer;
        scanner = new Scanner(System.in);
    }
    
    public void listen() {
        
        String input = scanner.next();
        while(!input.equals("`")) {
            observer.recieveMessage(input);
            input = scanner.nextLine();
        }
    }
    
}
