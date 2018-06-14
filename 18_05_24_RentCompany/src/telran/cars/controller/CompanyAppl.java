package telran.cars.controller;
import telran.view.*;


import telran.cars.controller.items.*;
import telran.cars.model.*;
public class CompanyAppl {
	static InputOutput inputOutput=new ConsoleInputOutput();
	static IRentCompany company=new RentCompanyEmbedded();
	public static void main(String[] args) {
		
		Item[]menuItems= getMenuItems();
		Menu menu=new MenuWithExit(inputOutput, menuItems);
		menu.runMenu();
	}

	private static Item[] getMenuItems() {
		Item[]items= {
			new Administrator(inputOutput, company, getAdminItems()),
			new Clerk(inputOutput, company, getClerkItems()),
			new Statist(inputOutput, company, getStatistItems()),
			new User(inputOutput, company, getUserItems()),
			new Technician(inputOutput, company, getTechnicianItems())
		};
		return items;
	}

	private static Item[] getTechnicianItems() {
		return new Item[] {
				new DisplayAllCars(inputOutput, company),
				new DisplayAllDrivers(inputOutput, company),
				new DisplayAllRecords(inputOutput,company)
		};
	}

	private static Item[] getUserItems() {
		return new Item[] {
				new DisplayModelNames(inputOutput,company),
				new DisplayModel(inputOutput, company),
				new DisplayCarDrivers(inputOutput, company),
				new DisplayDriverCars(inputOutput, company)
		};
	}

	private static Item[] getStatistItems() {
		return new Item[] {
				new DisplayModelProfit(inputOutput, company),
				new DisplayMostPopularModels(inputOutput, company),
				new DisplayMostProfitableModel(inputOutput, company)
		};
	}

	private static Item[] getClerkItems() {
		
		return new Item[] {
				new AddDriver(inputOutput, company),
				new RentCar(inputOutput, company),
				new ReturnCar(inputOutput, company)
		};
	}

	private static Item[] getAdminItems() {
		return new Item[] {
				new AddCarModel(inputOutput, company),
				new AddCar(inputOutput, company),
				new RemoveCar(inputOutput, company),
				new ClearCars(inputOutput, company),
				new DisplayDriver(inputOutput, company)
		};
	}

}
