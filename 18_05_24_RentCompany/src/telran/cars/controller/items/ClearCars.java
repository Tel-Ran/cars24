package telran.cars.controller.items;


import java.time.LocalDate;

import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class ClearCars extends CarsItem {

	public ClearCars(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Clear cars";
	}

	@Override
	public void perform() {
		LocalDate currentDate=inputOutput.getDate("Enter current date");
		int days=inputOutput.getInteger("Enter number of days since the last renting");
		inputOutput.displayLine("List of cars that has been removed:");
		company.clear(currentDate, days).forEach(inputOutput::displayLine);
		

	}

}
