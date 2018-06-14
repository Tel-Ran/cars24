package telran.cars.controller.items;

import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class DisplayDriverCars extends CarsItem {

	public DisplayDriverCars(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Display driver's cars";
	}

	@Override
	public void perform() {
		long licenseId=inputOutput.getInteger("Enter driver's license Id");
		company.getDriverCars(licenseId).forEach(inputOutput::displayLine);

	}

}
