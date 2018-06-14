package telran.cars.controller.items;



import telran.cars.model.IRentCompany;
import telran.view.InputOutput;
import telran.view.Item;

public class Statist extends CarsSubmenuItem {

	public Statist(InputOutput inputOutput, IRentCompany company, Item[] items) {
		super(inputOutput, company, items);
	}

	@Override
	public String displayedName() {
		return "Statistics";
	}

}
