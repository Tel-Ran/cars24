package telran.cars.controller.items;


import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class DisplayAllModels extends CarsItem {

	public DisplayAllModels(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Display data about all car models";
	}

	@Override
	public void perform() {
		company.getAllModelNames().forEach(inputOutput::displayLine);
	}

}
