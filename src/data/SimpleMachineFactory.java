package data;

public class SimpleMachineFactory implements MachineFactory {

    @Override
    public Machine createController() {
        return new SimpleMachine();
    }

}
