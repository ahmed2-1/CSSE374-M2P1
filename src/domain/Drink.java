package domain;

import java.util.ArrayList;

public interface Drink {
	
	
	ArrayList<Option> getCondiments();
	String getDrinkName();
	ArrayList<ArrayList<String>> getSteps();
}
