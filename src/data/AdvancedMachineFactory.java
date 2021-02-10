package data;

public class AdvancedMachineFactory implements MachineFactory {

    @Override
    public Machine createMachine() {
        return new AdvancedMachine();
    }

}
