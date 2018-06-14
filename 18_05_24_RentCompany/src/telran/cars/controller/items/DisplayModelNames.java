package telran.cars.controller.items;

import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class DisplayModelNames extends CarsItem {

	public DisplayModelNames(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Display all model names";
	}

	@Override
	public void perform() {
		company.getAllModelNames().forEach(inputOutput::displayLine);

	}

}
