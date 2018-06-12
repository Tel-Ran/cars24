package telran.cars.controller.items;

import telran.cars.dto.CarsReturnCode;
import telran.cars.dto.Model;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class AddCarModel extends CarsItem {

	public AddCarModel(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Adding car model";
	}

	@Override
	public void perform() {
		String modelName=inputOutput.getString("Enter model name");
		int gasTank=inputOutput.getInteger("Enter gas tank capacity", 40, 80);
		String companyName=inputOutput.getString("Enter company name");
		String country=inputOutput.getString("Enter country");
		int priceDay=inputOutput.getInteger("Enter price");
		Model model=new Model
				(modelName, gasTank, companyName,
						country, priceDay);
		CarsReturnCode code=company.addModel(model);
		inputOutput.displayLine
		(code==CarsReturnCode.OK?modelName+" model was added":
			modelName+" already exists");

	}

}
