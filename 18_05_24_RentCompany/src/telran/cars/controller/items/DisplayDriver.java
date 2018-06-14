package telran.cars.controller.items;


import telran.cars.dto.Driver;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class DisplayDriver extends CarsItem {

	public DisplayDriver(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Display driver's data";
	}

	@Override
	public void perform() {
		long licenseId=inputOutput.getInteger("Enter driver's license number");
		Driver driver=company.getDriver(licenseId);
		if(driver==null){
			inputOutput.displayLine("No driver with license: "+licenseId);
		}
		else{
			inputOutput.displayLine(driver);
		}

	}

}
