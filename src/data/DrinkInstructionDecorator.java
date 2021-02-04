package data;

import java.util.ArrayList;

public abstract class DrinkInstructionDecorator implements Drink {

    protected Drink wrapee;
    protected String ingredient;
    
    public DrinkInstructionDecorator(Drink wrapee, String ingredient) {
        this.wrapee = wrapee;
        this.ingredient = ingredient;
    }
    
    public abstract String getStepName();
    
    public String getIngredient() {
        return this.ingredient;
    }
    
    @Override
    public ArrayList<ArrayList<String>> getSteps() {
        ArrayList<ArrayList<String>> currentSteps = wrapee.getSteps();
        ArrayList<String> thisStep = new ArrayList<String>();
        
        thisStep.add(this.getStepName());
        thisStep.add(this.getIngredient());
        currentSteps.add(thisStep);
        
        return currentSteps;
    }
    
    @Override
    public ArrayList<Option> getCondiments() {
        return wrapee.getCondiments();
    }
    
    @Override
    public void setName(String drinkName) {
        wrapee.setName(drinkName);
    }
    
    @Override
    public void setCondiments(ArrayList<Option> options) {
        wrapee.setCondiments(options);
    }
    
}
