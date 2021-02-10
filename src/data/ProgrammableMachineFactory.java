package data;

public class ProgrammableMachineFactory implements MachineFactory {

    @Override
    public Machine createController() {
        return new ProgrammableMachine();
    }

}
