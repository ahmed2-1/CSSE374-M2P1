package data;

public class ProgrammableControllerFactory implements ControllerFactory {

    @Override
    public Controller createController() {
        return new ProgrammableController();
    }

}
