package telran.cars.controller.items;

import telran.cars.model.IRentCompany;
import telran.view.InputOutput;
import telran.view.Item;

public abstract class CarsItem extends Item {
protected IRentCompany company;
	public CarsItem(InputOutput inputOutput, IRentCompany company) {
	super(inputOutput);
	this.company = company;
}

	
}
