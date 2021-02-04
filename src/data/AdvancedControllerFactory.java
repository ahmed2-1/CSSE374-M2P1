package data;

public class AdvancedControllerFactory implements ControllerFactory {

    @Override
    public Controller createController() {
        return new AdvancedController();
    }

}
