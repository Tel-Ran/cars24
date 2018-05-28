package telran.cars.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Stream;

import telran.cars.dto.Car;
import telran.cars.dto.CarsReturnCode;
import telran.cars.dto.Driver;
import telran.cars.dto.Model;
import telran.cars.dto.RentRecord;

@SuppressWarnings("serial")
public class RentCompanyEmbedded extends AbstractRentCompany {
	private HashMap<String,Car> cars=new HashMap<>();
	private HashMap<Long,Driver> drivers=new HashMap<>();
	private HashMap<String,List<RentRecord>> carRecords=new HashMap<>();
	private HashMap<Long,List<RentRecord>> driverRecords=new HashMap<>();
	private HashMap<String,Model> models=new HashMap<>();
	private TreeMap<LocalDate,List<RentRecord>> returnedRecords=new TreeMap<>();
	@Override
	public CarsReturnCode addModel(Model model) {
		return models.putIfAbsent(model.getModelName(), model)==null?
				CarsReturnCode.OK:CarsReturnCode.MODEL_EXISTS;
	}

	@Override
	public CarsReturnCode addCar(Car car) {
		Model model=models.get(car.getModelName());
		if(model==null)
			return CarsReturnCode.NO_MODEL;
		return cars.putIfAbsent(car.getRegNumber(),
				car)==null?CarsReturnCode.OK:CarsReturnCode.CAR_EXISTS;
	}

	@Override
	public CarsReturnCode addDriver(Driver driver) {
		return drivers.putIfAbsent(driver.getLicenseId(),
				driver)==null?CarsReturnCode.OK:
					CarsReturnCode.DRIVER_EXISTS;
	}

	@Override
	public Model getModel(String modelName) {
		return models.get(modelName);
	}

	@Override
	public Car getCar(String carNumber) {
		return cars.get(carNumber);
	}

	@Override
	public Driver getDriver(long licenseId) {
		return drivers.get(licenseId);
	}

	@Override
	public CarsReturnCode rentCar(String carNumber, long licenseId, LocalDate rentDate, int rentDays) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CarsReturnCode returnCar(String carNumber, long licenseId, LocalDate returnDate, int gasTankPercent,
			int damages) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CarsReturnCode removeCar(String carNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Car> clear(LocalDate currentDate, int days) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Driver> getCarDrivers(String carNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Car> getDriverCars(long licenseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<Car> getAllCars() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<Driver> getAllDrivers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<RentRecord> getAllRecords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAllModelNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getMostPopularModelNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getModelProfit(String modelName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> getMostProfitModelNames() {
		// TODO Auto-generated method stub
		return null;
	}

}