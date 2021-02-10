package data;

public class SimpleMachineFactory implements MachineFactory {

    @Override
    public Machine createMachine() {
        return new SimpleMachine();
    }

}
