package telran.cars.tests;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.cars.model.*;
import telran.cars.dto.*;
class RentCompanyTests {
private static final String REG_NUMBER1 = "123";
private static final String REG_NUMBER2 = "124";
private static final String MODEL1 = "BMW12";
private static final String MODEL2 = "B4";
private static final String REG_NUMBER3 = "125";
private static final long LICENSE1 = 123;
private static final long LICENSE2 = 124;
private static final LocalDate RENT_DATE1 = LocalDate.parse("2018-05-28");
private static final int RENT_DAYS1 = 5;
private static final LocalDate RETURN_DATE = RENT_DATE1.plusDays(RENT_DAYS1);
private static final LocalDate WRONG_RETURN_DATE = RENT_DATE1.minusDays(1);
private static final long DELAY_DAYS = 2;
private static final LocalDate RETURN_DATE_DELAY = RETURN_DATE.plusDays(DELAY_DAYS);
private static final int GAS_PERCENT = 50;
private static final int DAMAGES = 60;
private static final LocalDate CURRENT_DATE = LocalDate.ofYearDay(2019, 10);
private static final int CLEAR_DAYS = 50;
private static final String MODEL_SUPER = "Ferrary";
private static final int SUPER_PRICE = 100000;
private static final String REG_NUMBER_SUPER = "12345";

IRentCompany company;
Car car1=new Car(REG_NUMBER1, "red", MODEL1);
Car car2=new Car(REG_NUMBER2, "green", MODEL2);
Car car3=new Car(REG_NUMBER3,"silver",MODEL1);
Model model1=new Model(MODEL1, 55, "BMW", "Germany", 200);
Model model2=new Model(MODEL2, 50, "Subaru", "Japan", 190);
Driver driver1=new Driver(LICENSE1, "Moshe", 1980, "050-1234567");
Driver driver2=new Driver(LICENSE2,"David",1960,"050-7654321");
RentRecord recordRent=new RentRecord
(LICENSE1, REG_NUMBER1, RENT_DATE1, RENT_DAYS1);
private Car carSuper=new Car(REG_NUMBER_SUPER, "white", MODEL_SUPER);

	@BeforeEach
	public void setUp() throws Exception {
		company=new RentCompanyEmbedded();
		company.addModel(model1);
		company.addDriver(driver1);
		company.addCar(car1);
		company.rentCar(REG_NUMBER1, LICENSE1, RENT_DATE1,
				RENT_DAYS1);
	}
	@Test
	public void rentCar() {
		assertEquals(CarsReturnCode.CAR_IN_USE,
				company.rentCar(REG_NUMBER1, LICENSE1, RENT_DATE1, RENT_DAYS1));
		assertEquals(CarsReturnCode.NO_CAR,
				company.rentCar(REG_NUMBER2, LICENSE1, RENT_DATE1, RENT_DAYS1));
		company.addModel(model2);
		company.addCar(car2);
		assertEquals(CarsReturnCode.NO_DRIVER,company.rentCar
				(REG_NUMBER2, LICENSE2, RENT_DATE1, RENT_DAYS1));
		assertEquals(CarsReturnCode.OK,company.rentCar
				(REG_NUMBER2, LICENSE1, RENT_DATE1, RENT_DAYS1));
		RentRecord record1=getRecord(REG_NUMBER1);
		assertEquals(LICENSE1,record1.getLicenseId());
		assertEquals(recordRent,record1);
		assertTrue(car1.isInUse());
		
		
	}
	private RentRecord getRecord(String regNumber1) {
		return company.getAllRecords().filter(x->x.getCarNumber()
				.equals(regNumber1)).findFirst().orElse(null);
	}
	@Test
	public void addCar() {
		assertEquals(CarsReturnCode.CAR_EXISTS,company.addCar(car1));
		assertEquals(CarsReturnCode.NO_MODEL,company.addCar(car2));
		assertEquals(CarsReturnCode.OK,company.addCar(car3));
	}
	@Test
	public void addModel() {
		assertEquals(CarsReturnCode.MODEL_EXISTS,company.addModel(model1));
		assertEquals(CarsReturnCode.OK,company.addModel(model2));
	}
	@Test
	public void addDriver() {
		assertEquals(CarsReturnCode.DRIVER_EXISTS,company.addDriver(driver1));
		assertEquals(CarsReturnCode.OK,company.addDriver(driver2));
	}
	@Test
	public void getCar() {
		assertEquals(null,company.getCar(REG_NUMBER2));
		assertEquals(car1,company.getCar(REG_NUMBER1));
	}
	@Test
	public void getModel() {
		assertEquals(null,company.getModel(MODEL2));
		assertEquals(model1,company.getModel(MODEL1));
	}
	@Test
	public void getDriver() {
		assertEquals(null,company.getDriver(LICENSE2));
		assertEquals(driver1,company.getDriver(LICENSE1));
	}
	@Test
	public void getAllRecords() {
		company.getAllRecords().forEach(r->assertEquals(recordRent, r));
	}
	@Test
	public void getCarDrivers() {
		company.getCarDrivers(REG_NUMBER1)
		.forEach(d->assertEquals(driver1,d));
	}
	@Test
	public void getDriverCars() {
		company.getDriverCars(LICENSE1)
		.forEach(c->assertEquals(car1,c));
	}
	@Test
	public void returnCarCodes() {
		assertEquals(CarsReturnCode.CAR_NOT_RENTED,
		company.returnCar(REG_NUMBER2, LICENSE1, RETURN_DATE, 100, 0));
		assertEquals(CarsReturnCode.CAR_NOT_RENTED,
				company.returnCar(REG_NUMBER1, LICENSE2, RETURN_DATE, 100, 0));
		assertEquals(CarsReturnCode.RETURN_DATE_WRONG,
		company.returnCar(REG_NUMBER1, LICENSE1, WRONG_RETURN_DATE, 100, 0));
		assertEquals(CarsReturnCode.OK,
				company.returnCar(REG_NUMBER1, LICENSE1, RETURN_DATE, 100, 0));
	}
	@Test
	public void returCarNoDamagesNoAdditionalCost () {
		company.returnCar(REG_NUMBER1, LICENSE1, RETURN_DATE, 100, 0);
		assertFalse(car1.isInUse());
		assertEquals(car1.getState(),State.EXCELLENT);
		assertFalse(car1.isFlRemoved());
		recordRent.setGasTankPercent(100);
		recordRent.setReturnDate(RETURN_DATE);
		recordRent.setCost(RENT_DAYS1*model1.getPriceDay());
		RentRecord record=getRecord(REG_NUMBER1);
		assertEquals(recordRent,record);
	}
	@Test
	public void returnCarWithDamagesAdditionalCost() {
		company.returnCar(REG_NUMBER1,LICENSE1,RETURN_DATE_DELAY,GAS_PERCENT,DAMAGES);
		assertFalse(car1.isInUse());
		assertTrue(car1.isFlRemoved());
		recordRent.setGasTankPercent(GAS_PERCENT);
		recordRent.setDamages(DAMAGES);
		recordRent.setReturnDate(RETURN_DATE_DELAY);
		recordRent.setCost(RENT_DAYS1*model1.getPriceDay()+getAdditionaCost());
	}
	private float getAdditionaCost() {
		
		int gasPrice=((AbstractRentCompany)company).getGasPrice();
		int finePerDay=((AbstractRentCompany)company).getFinePercent();
		int gasTank=model1.getGasTank();
		int priceDay=model1.getPriceDay();
		return (gasTank-GAS_PERCENT*gasTank/100)*gasPrice+
				DELAY_DAYS*(priceDay+finePerDay*priceDay/100);
	}
	@Test
	public void removeCar() {
		assertEquals(CarsReturnCode.CAR_IN_USE,
				company.removeCar(REG_NUMBER1));
		assertEquals(CarsReturnCode.NO_CAR,
				company.removeCar(REG_NUMBER2));
		company.returnCar
		(REG_NUMBER1, LICENSE1, RETURN_DATE,
				100, 0);
		assertEquals(CarsReturnCode.OK,
				company.removeCar(REG_NUMBER1));
		assertTrue(car1.isFlRemoved());
	}
	@Test
	public void clear() {
		setUpClear();
		//assumed car1 and car2 are deleted ; car3 is not deleted
		List<Car> carsActual=company.clear(CURRENT_DATE, CLEAR_DAYS);
		Car[]carsExpected= {car1,car2};
		carsActual.sort((x,y)->x.getRegNumber().compareTo(y.getRegNumber()));
		assertArrayEquals(carsExpected, carsActual.toArray());
		assertNull(company.getCar(REG_NUMBER1));
		assertNull(company.getCar(REG_NUMBER2));
		assertNull(getRecord(REG_NUMBER1));
		assertNull(getRecord(REG_NUMBER2));
		assertNotNull(company.getCar(REG_NUMBER3));
		assertNotNull(getRecord(REG_NUMBER3));
		
		
		
	}
	private void setUpClear() {
		company.returnCar
		(REG_NUMBER1, LICENSE1, RETURN_DATE, 0, 90);
		company.addModel(model2);
		company.addCar(car2);
		company.addCar(car3);
		company.rentCar(REG_NUMBER2, LICENSE1, RENT_DATE1, RENT_DAYS1);
		company.rentCar(REG_NUMBER3, LICENSE1, RENT_DATE1, RENT_DAYS1);
		company.returnCar
		(REG_NUMBER2, LICENSE1, RETURN_DATE, 100, 0);
		company.removeCar(REG_NUMBER2);
		company.returnCar
		(REG_NUMBER3, LICENSE1, RETURN_DATE, 100, 0);
		
		
	}
	@Test
	public void getAllCars() {
		Car[]expected= {car1,car3};
		company.addCar(car3);
		assertArrayEquals(expected,company.getAllCars()
				.sorted((c1,c2)->
				c1.getRegNumber().compareTo(c2.getRegNumber())).toArray());
	}
	@Test
	public void getAllDrivers() {
		Driver[]expected= {driver1,driver2};
		company.addDriver(driver2);
		assertArrayEquals(expected,company.getAllDrivers()
				.sorted((d1,d2)->
				Long.compare(d1.getLicenseId(), d2.getLicenseId())).toArray());
	}
	@Test
	public void getModelNames() {
		String[]expected= {MODEL2,MODEL1};
		company.addModel(model2);
		List<String>actual=company.getAllModelNames();
		actual.sort(String::compareTo);
		assertArrayEquals(expected,actual.toArray());
	}
	@Test
	public void mostPopularModels() {
		setUpStatics();
		//assumed model2 and model1 most popular
		List<String> actualModels=company.getMostPopularModelNames();
		String expectedModels[]= {MODEL2,MODEL1};//B4 less than BMW12
		actualModels.sort(String::compareTo);
		assertArrayEquals(expectedModels, actualModels.toArray());
	}
	@Test
	public void getProfit() {
		setUpStatics();
		assertEquals(200*5*3,company.getModelProfit(MODEL1));
		assertEquals(190*5*3,company.getModelProfit(MODEL2));
		assertEquals(SUPER_PRICE*5*1,company.getModelProfit(MODEL_SUPER));
	}
	@Test
	public void mostProfitModel() {
		setUpStatics();
		String []expected= {MODEL_SUPER};
		List<String> actual=company.getMostProfitModelNames();
		assertArrayEquals(expected, actual.toArray());
	}
	private void setUpStatics() {
		company.returnCar
		(REG_NUMBER1, LICENSE1, RETURN_DATE, 100, 0);
		rentReturn(REG_NUMBER1,2);
		company.addModel(model2);
		company.addCar(car2);
		rentReturn(REG_NUMBER2,3);
		
		company.addModel(new Model(MODEL_SUPER, 100, "FEAT", "ITALY", SUPER_PRICE));
		company.addCar(carSuper);
		rentReturn(carSuper.getRegNumber(),1);
		
	}
	private void rentReturn(String regNumber, int n) {
		for (int i=0;i<n;i++) {
			company.rentCar(regNumber, LICENSE1, RENT_DATE1, RENT_DAYS1);
			company.returnCar(regNumber, LICENSE1, RETURN_DATE, 100, 0);
		}
		
	}
	

}
