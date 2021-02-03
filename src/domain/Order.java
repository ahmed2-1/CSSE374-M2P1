package domain;

import java.util.List;

import data.Drink;

public class Order {
    int orderID;
    String streetAddress;
    int zipcode;
    Drink drink;

    public Order(int orderID, String streetAddress, int zipcode, Drink drink) {
        this.orderID = orderID;
        this.streetAddress = streetAddress;
        this.zipcode = zipcode;
        this.drink = drink;
    }

    public int getOrderID() { 
        return this.orderID;
    }

    public String getStreetAddress() {
        return this.streetAddress;
    }

    public int getZipcode() {
        return this.zipcode;
    }

    public Drink getDrink() {
        return this.drink;
    }

    public List<Option> getCondiments(){
        return this.drink.getCondiments();
    }

}
