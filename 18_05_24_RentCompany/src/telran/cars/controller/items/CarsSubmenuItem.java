package telran.cars.controller.items;


import java.util.ArrayList;

import telran.cars.model.IRentCompany;
import telran.view.*;

public abstract class CarsSubmenuItem extends CarsItem {
Item[] items;
	

	public CarsSubmenuItem(InputOutput inputOutput, IRentCompany company, Item[] items) {
	super(inputOutput, company);
	this.items = items;
}

	

	@Override
	public void perform() {
		Menu menu=new MenuWithExit(inputOutput,items );
		menu.runMenu();

	}

}
