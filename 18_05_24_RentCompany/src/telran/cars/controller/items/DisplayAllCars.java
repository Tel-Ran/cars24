package telran.cars.controller.items;


import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class DisplayAllCars extends CarsItem {

	public DisplayAllCars(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Display data about all cars";
	}

	@Override
	public void perform() {
		company.getAllCars().forEach(inputOutput::displayLine);

	}

}
