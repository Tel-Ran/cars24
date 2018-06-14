package telran.cars.controller.items;



import telran.cars.model.IRentCompany;
import telran.view.InputOutput;
import telran.view.Item;

public class Clerk extends CarsSubmenuItem {

	public Clerk(InputOutput inputOutput, IRentCompany company, Item[] items) {
		super(inputOutput, company, items);
	}

	@Override
	public String displayedName() {
		return "Clerk (renting car, returning car, adding driver";
	}

}
