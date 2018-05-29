package telran.cars.tests;

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
private static final String REG_NUMBER3 = "124";
private static final long LICENSE1 = 123;
private static final long LICENSE2 = 124;
private static final LocalDate RENT_DATE1 = LocalDate.parse("2018-05-28");
private static final int RENT_DAYS1 = 5;
IRentCompany company;
Car car1=new Car(REG_NUMBER1, "red", MODEL1);
Car car2=new Car(REG_NUMBER2, "green", MODEL2);
Car car3=new Car(REG_NUMBER3,"silver",MODEL1);
Model model1=new Model(MODEL1, 55, "BMW", "Germany", 200);
Model model2=new Model(MODEL2, 50, "Subaru", "Japan", 190);
Driver driver1=new Driver(LICENSE1, "Moshe", 1980, "050-1234567");
Driver driver2=new Driver(LICENSE2,"David",1960,"050-7654321");


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
		assertEquals(REG_NUMBER1,record1.getCarNumber());
		assertEquals(RENT_DATE1,record1.getRentDate());
		assertEquals(null,record1.getReturnDate());
		assertEquals(RENT_DAYS1,record1.getRentDays());
		assertTrue(car1.isInUse());
		
	}
	private RentRecord getRecord(String regNumber1) {
		return company.getAllRecords().filter(x->x.getCarNumber()
				.equals(regNumber1)).findFirst().get();
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
		//TODO
	}
	@Test
	public void getCarDrivers() {
		//TODO
	}
	@Test
	public void getDriverCars() {
		//TODO
	}

}
