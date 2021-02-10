package domain;
import data.Command;
import data.MachineDatabase;
import data.Order;

public class OrderProcessor {

    private MachineDatabase database;

    public OrderProcessor(MachineDatabase database) {

        this.database = database;

    }

    public Command processOrder(Order order) {
        int controllerID = database.findCompatibleController(order.getStreetAddress(),  order.getZipcode(), order.getDrink());
        if (order.getCondiments().isEmpty())
            return new Command(controllerID, 1, order.getOrderID(), order.getDrink(), "Simple");
        if (order.getDrink().getSteps().isEmpty())
            return new Command(controllerID, 1, order.getOrderID(), order.getDrink(), "Automated", order.getCondiments());
        else
            return new Command(controllerID, 1, order.getOrderID(), order.getDrink(), "Programmable", order.getCondiments());
    }
}
