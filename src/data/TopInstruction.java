package data;

public class TopInstruction extends DrinkInstructionDecorator {

    public TopInstruction(Drink wrapee, String ingredient) {
        super(wrapee, ingredient);
    }

    @Override
    public String getDrinkName() {
        return wrapee.getDrinkName();
    }

    @Override
    public String getStepName() {
        return "top";
    }

}
