package telran.cars.controller.items;

import telran.cars.model.IRentCompany;
import telran.view.InputOutput;

public class ExitAndSave extends CarsItem {

	public ExitAndSave(InputOutput inputOutput, IRentCompany company) {
		super(inputOutput, company);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String displayedName() {
		return "Exit and save";
	}

	@Override
	public void perform() {
		company.save();

	}
	@Override
	public boolean isExit() {
		return true;
	}

}
