package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class ProgrammableController extends Controller {
	
	private List<Option> condiments;
	private List<String> ingredients; //TODO: Change in M3P2 to use the decorator pattern

	public ProgrammableController(int id, String address) {
		//TODO: Change the constructor to work with the factory
		super(id, address);
		condiments = new ArrayList<>();
		ingredients = new ArrayList<>();
	}

	@Override
	DrinkResponse recieveCommand(Command command) throws TimeoutException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canProcessCondiments(List<Option> condiments) {
		// TODO Auto-generated method stub
		return false;
	}

}
