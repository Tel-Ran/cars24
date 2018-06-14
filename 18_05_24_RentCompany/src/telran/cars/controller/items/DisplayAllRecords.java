package telran.cars.controller.items;

import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class DisplayAllRecords extends CarsItem {

	public DisplayAllRecords(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Display all records";
	}

	@Override
	public void perform() {
		company.getAllRecords().forEach(inputOutput::displayLine);
	}

}
