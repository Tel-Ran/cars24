package telran.cars.controller.items;

import java.util.*;
import telran.cars.dto.Car;
import telran.cars.dto.CarsReturnCode;
import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class AddCar extends CarsItem {

	public AddCar(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
	}

	@Override
	public String displayedName() {
		return "Adding new car";
	}

	@Override
	public void perform() {
		String carNumber=inputOutput.getString("Enter reg number");
		Set<String> options=getPossibleCollor();
		String color=inputOutput.getString("Enter color "+options, options);
		Set<String> modelNames=new HashSet<>(company.getAllModelNames());
		String modelName=inputOutput.getString("Type model name from "
				+  modelNames,modelNames);
		Car car=new Car(carNumber, color, modelName);
		CarsReturnCode code=company.addCar(car);
		inputOutput.displayLine(carNumber+
				(code==CarsReturnCode.OK?" was added":code));

	}

	private Set<String> getPossibleCollor() {
		
		return new HashSet<>(Arrays.asList("red","green","gray",
				"white","black","yellow","silver"));
	}

}
