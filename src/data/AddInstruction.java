package data;

public class AddInstruction extends DrinkInstructionDecorator {

    public AddInstruction(Drink wrapee, String ingredient) {
        super(wrapee, ingredient);
    }

    @Override
    public String getDrinkName() {
        return wrapee.getDrinkName();
    }

    @Override
    public String getStepName() {
        return "add";
    }

}
