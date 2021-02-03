package domain;

public class AdvancedControllerFactory implements ControllerFactory {

    @Override
    public Controller createController() {
        return new AdvancedController();
    }

}
