package domain;

import data.Order;

public interface PortObserver {

    public void recieveOrder(Order order);
    
}
