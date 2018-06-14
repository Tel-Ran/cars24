package telran.cars.controller.items;

import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class DisplayCarDrivers extends CarsItem {

	public DisplayCarDrivers(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Display car drivers";
	}

	@Override
	public void perform() {
		String carNumber=inputOutput.getString("Enter car number");
		company.getCarDrivers(carNumber).forEach(inputOutput::displayLine);
	}

}
