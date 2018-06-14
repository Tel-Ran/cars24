package telran.cars.controller.items;


import telran.cars.dto.*;
import telran.cars.model.*;
import telran.view.*;

public class RemoveCar extends CarsItem {

	public RemoveCar(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Removing car";
	}

	@Override
	public void perform() {
		String carNumber=inputOutput.getString("Enter car registration number");
		CarsReturnCode code=company.removeCar(carNumber);
		inputOutput.displayLine(code);

	}

}
