package telran.cars.controller.items;

import telran.cars.model.IRentCompany;
import telran.view.InputOutput;
import telran.view.Item;

public class User extends CarsSubmenuItem{

	public User(InputOutput inputOutput, IRentCompany company, Item[] items) {
		super(inputOutput, company, items);
	}

	@Override
	public String displayedName() {
		return "User (model names,displaying model,driver cars,car drivers";
	}

}
