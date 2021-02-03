package data;

import java.util.ArrayList;

import domain.Option;

public class Coffee implements Drink {

    private String name;
    ArrayList<Option> condiments;
    
    @Override
    public ArrayList<Option> getCondiments() {
        return condiments;
    }

    @Override
    public String getDrinkName() {
        return name;
    }

    @Override
    public ArrayList<ArrayList<String>> getSteps() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setName(String drinkName) {
        this.name = drinkName;        
    }

    @Override
    public void setCondiments(ArrayList<Option> options) {
        this.condiments = options;
    }

}