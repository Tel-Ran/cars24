package telran.cars.controller.items;


import java.time.LocalDate;

import telran.cars.dto.CarsReturnCode;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class RentCar extends CarsItem {

	public RentCar(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Renting car";
	}

	@Override
	public void perform() {
		String carNumber=
		inputOutput.getString("Enter car's registration number");
		long licenseId=inputOutput.getInteger("Enter driver's license id");
		LocalDate rentDate=inputOutput.getDate("Enter rent date");
		int rentDays=inputOutput.getInteger("Enter rent days",1,30);
		CarsReturnCode code=company.rentCar
		(carNumber, licenseId, rentDate, rentDays);
		inputOutput.displayLine(code);

	}

}
