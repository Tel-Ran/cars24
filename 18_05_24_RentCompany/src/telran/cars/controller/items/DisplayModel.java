package telran.cars.controller.items;


import java.util.HashSet;
import java.util.Set;

import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class DisplayModel extends CarsItem {

	public DisplayModel(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Display data about model";
	}

	@Override
	public void perform() {
		Set<String> modelNames=new HashSet<>(company.getAllModelNames());
		String modelName=inputOutput.getString("Enter model name",modelNames);
		inputOutput.displayLine(company.getModel(modelName));

	}

}
