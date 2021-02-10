package data;

public class AdvancedMachineFactory implements MachineFactory {

    @Override
    public Machine createController() {
        return new AdvancedMachine();
    }

}
