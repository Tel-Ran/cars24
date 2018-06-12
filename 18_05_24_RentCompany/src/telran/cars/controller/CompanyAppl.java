package telran.cars.controller;
import telran.view.*;
import telran.cars.controller.items.*;
import telran.cars.model.*;
public class CompanyAppl {

	public static void main(String[] args) {
		InputOutput inputOutput=new ConsoleInputOutput();
		IRentCompany company=new RentCompanyEmbedded();
		Item[]adminItems=getAdminItems(inputOutput,company);
		Item[]subMenu= {
				new Administrator(inputOutput, company, adminItems)
	};
		Menu menu=new MenuWithExit(inputOutput, subMenu);
		menu.runMenu();
	}

	private static Item[] getAdminItems(InputOutput inputOutput, IRentCompany company) {
		return new Item[] {
				new AddCarModel(inputOutput, company),
				new AddCar(inputOutput, company)
		};
	}

}
