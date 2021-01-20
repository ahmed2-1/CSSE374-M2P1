import java.util.ArrayList;

public class Order {
	int orderID;
	String streetAddress;
	int zipcode;
	String drink;
	ArrayList<Option> condiments;
	
	public Order(int orderID, String streetAddress, int zipcode, String drink) {
		this.orderID = orderID;
		this.streetAddress = streetAddress;
		this.zipcode = zipcode;
		this.drink = drink;
		this.condiments = new ArrayList<>();
	}
	
	public Order(int orderID, String streetAddress, int zipcode, String drink, ArrayList<Option> condiments) {
		this.orderID = orderID;
		this.streetAddress = streetAddress;
		this.zipcode = zipcode;
		this.drink = drink;
		this.condiments = condiments;
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
	
	public String getDrink() {
		return this.drink;
	}
	
	public ArrayList<Option> getCondiments(){
		return this.condiments;
	}
 
}
