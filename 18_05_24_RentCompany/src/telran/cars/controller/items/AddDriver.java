package telran.cars.controller.items;


import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import telran.cars.dto.CarsReturnCode;
import telran.cars.dto.Driver;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class AddDriver extends CarsItem {

	public AddDriver(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Adding new driver";
	}

	@Override
	public void perform() {
		long licenseId=inputOutput.getInteger("Enter license ID");
		String name=inputOutput.getString("Enter driver's name");
		int birthYear=inputOutput.getInteger("Enter driver's birth year",1940,2000);
		String phone=inputOutput.getString("Enter Israel Mobile phone",getPhonePredicate());
		Driver driver=new Driver(licenseId,
				name, birthYear, phone);
		CarsReturnCode code=company.addDriver(driver);
		inputOutput.displayLine(code);

	}

	private Predicate<String> getPhonePredicate() {
	Pattern pattern=Pattern.compile("(\\+972|0)-?5[02-8](-?\\d){7}");
		return pattern.asPredicate();
	}

	

	

}
