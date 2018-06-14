package telran.cars.controller.items;


import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class DisplayAllDrivers extends CarsItem {

	public DisplayAllDrivers(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Display data about all drivers";
	}

	@Override
	public void perform() {
		company.getAllDrivers().forEach(inputOutput::displayLine);

	}

}
