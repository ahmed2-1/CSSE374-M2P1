package data;

public class SimpleControllerFactory implements ControllerFactory {

    @Override
    public Controller createController() {
        return new SimpleController();
    }

}
