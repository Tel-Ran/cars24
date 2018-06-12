package telran.cars.controller.items;

import telran.cars.model.IRentCompany;
import telran.view.*;


public class Administrator extends CarsItem {
private Item[]items;
	public Administrator(InputOutput inputOutput, IRentCompany company, Item[] items) {
	super(inputOutput, company);
	this.items = items;
}

	@Override
	public String displayedName() {
		return "Administrator (addCar,addModel,removeCar,clear)";
	}

	@Override
	public void perform() {
		Menu menu=new MenuWithExit(inputOutput, items);
		menu.runMenu();

	}

}
