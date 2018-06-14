package telran.cars.controller.items;

import java.util.HashSet;
import java.util.Set;

import telran.cars.model.IRentCompany;
import telran.view.InputOutput;
import telran.view.Item;

public class DisplayModelProfit extends CarsItem {

	

	public DisplayModelProfit(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Display model's profit";
	}

	@Override
	public void perform() {
		Set<String> modelNames=new HashSet<>(company.getAllModelNames());
		String modelName=inputOutput.getString("Type model name from "
				+  modelNames,modelNames);
		inputOutput.displayLine(company.getModelProfit(modelName));

	}

}
