package data;

public class MixInstruction extends DrinkInstructionDecorator {

    public MixInstruction(Drink wrapee, String ingredient) {
        super(wrapee, ingredient);
    }

    @Override
    public String getDrinkName() {
        return wrapee.getDrinkName();
    }

    @Override
    public String getStepName() {
        return "mix";
    }

}
