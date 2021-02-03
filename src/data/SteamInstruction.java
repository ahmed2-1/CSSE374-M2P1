package data;

public class SteamInstruction extends DrinkInstructionDecorator {

    public SteamInstruction(Drink wrapee, String ingredient) {
        super(wrapee, ingredient);
    }

    @Override
    public String getDrinkName() {
        return wrapee.getDrinkName();
    }

    @Override
    public String getStepName() {
        return "steam";
    }

}
