package data;

import java.util.ArrayList;

public interface Drink {
	ArrayList<Option> getCondiments();
	String getDrinkName();
	ArrayList<ArrayList<String>> getSteps();
    void setName(String drinkName);
    void setCondiments(ArrayList<Option> options);
}
