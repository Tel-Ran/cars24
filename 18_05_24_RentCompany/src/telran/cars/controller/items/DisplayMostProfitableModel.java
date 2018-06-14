package telran.cars.controller.items;


import java.util.Map;
import java.util.stream.Collectors;

import telran.cars.dto.Car;
import telran.cars.dto.RentRecord;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class DisplayMostProfitableModel extends CarsItem {

	public DisplayMostProfitableModel(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
		
	}

	@Override
	public String displayedName() {
		return "Displaying data about most profitable model";
	}

	@Override
	public void perform() {
		company.getMostProfitModelNames()
		.forEach(inputOutput::displayLine);
	}

}
