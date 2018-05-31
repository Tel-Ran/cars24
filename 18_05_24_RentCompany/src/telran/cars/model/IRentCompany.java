package telran.cars.model;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

import telran.cars.dto.*;
public interface IRentCompany {
	CarsReturnCode addModel(Model model);//(OK,MODEL_EXISTS)
	CarsReturnCode addCar(Car car);//(OK,CAR_EXISTS,NO_MODEL)
	CarsReturnCode addDriver(Driver driver);//(OK,DRIVER_EXISTS)
	Model getModel(String modelName);
	Car getCar(String carNumber);
	Driver getDriver(long licenseId);
	CarsReturnCode rentCar(String carNumber,long licenseId,
	LocalDate rentDate,int rentDays);//(OK,CAR_IN_USE,NO_CAR,NO_DRIVER)
	    
	CarsReturnCode returnCar(String carNumber,long licenseId,
	LocalDate returnDate,int gasTankPercent,
	      int  damages);//(OK,CAR_NOT_RENTED,
	      // RETURN_DATE_WRONG) In the case of damages up to 10% state is GOOD, 
	       // up to 30% state BAD more than 30% - remove car (flRemoved)
	CarsReturnCode removeCar(String carNumber);//(OK,CAR_IN_USE,CAR_NOT_EXISTS)
	//removing car is setting flRemoved in true
	List<Car> clear(LocalDate currentDate,int days); 
	//all cars for which the returnDate before currentDate - days with flRemoved=true
	//are deleted from an information model along with all related records
	//it returns list of the deleted cars
	List<Driver> getCarDrivers(String carNumber); //returns
	//all drivers that have been renting the car
	List<Car> getDriverCars(long licenseId); //returns list of 
	//all cars that have been rented by the driver
	Stream<RentRecord> getAllRecords();
	Stream<Car> getAllCars();
	Stream<Driver> getAllDrivers();
	
	List<String> getAllModelNames();
	List<String> getMostPopularModelNames(); //returns list of
	// the model names the cars of which have been rented most times
	double getModelProfit(String modelName); //returns value of money received from
	//the renting cars of a given model name
	List<String> getMostProfitModelNames(); //returns list of most
	//proftable model names


}
