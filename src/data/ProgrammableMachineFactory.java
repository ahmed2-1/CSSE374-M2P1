package data;

public class ProgrammableMachineFactory implements MachineFactory {

    @Override
    public Machine createMachine() {
        return new ProgrammableMachine();
    }

}
