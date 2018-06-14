package telran.cars.controller.items;


import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class DisplayMostPopularModels extends CarsItem {

	
	public DisplayMostPopularModels(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Display  most popular model names";
	}

	@Override
	public void perform() {
		company.getMostPopularModelNames()
		.forEach(inputOutput::displayLine);
	}

}
