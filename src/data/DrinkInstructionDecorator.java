package data;

public abstract class DrinkInstructionDecorator implements Drink {

    private Drink wrapee;
    private String ingredient;
    
    public DrinkInstructionDecorator(Drink wrapee, String ingredient) {
        this.wrapee = wrapee;
        this.ingredient = ingredient;
    }
    
    public abstract String getStepName();
    
    public String getIngredient() {
        return this.ingredient;
    }
    
    
}
