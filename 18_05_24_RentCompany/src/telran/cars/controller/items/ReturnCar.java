package telran.cars.controller.items;


import java.time.LocalDate;

import telran.cars.dto.CarsReturnCode;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class ReturnCar extends CarsItem {

	public ReturnCar(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Returning car";
	}

	@Override
	public void perform() {
		String carNumber=inputOutput.getString("Enter car's registration number");
		long licenseId=inputOutput.getInteger("Enter driver's license number");
		LocalDate returnDate=inputOutput.getDate("Enter return date");
		int gasTankPercent=inputOutput.getInteger("Enter gasoline tank percent",0,100);
		int damages=inputOutput.getInteger("Enter damages assesment ",0,100);
		CarsReturnCode code=company.returnCar
		(carNumber, licenseId, returnDate, gasTankPercent, damages);
		inputOutput.displayLine(code);

	}

}
