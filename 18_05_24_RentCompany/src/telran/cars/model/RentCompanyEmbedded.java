package telran.cars.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import telran.cars.dto.Car;
import telran.cars.dto.CarsReturnCode;
import telran.cars.dto.Driver;
import telran.cars.dto.Model;
import telran.cars.dto.RentRecord;
import telran.cars.dto.State;

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
		CarsReturnCode code=checkRentCar(carNumber,licenseId);
		if(code==CarsReturnCode.OK) {
			RentRecord record=new RentRecord(licenseId, carNumber,
					rentDate, rentDays);
			addCarRecords(record);
			addDriverRecords(record);
			setInUse(record);
		}
		return code;
	}

	private void setInUse(RentRecord record) {
		Car car=cars.get(record.getCarNumber());
		car.setInUse(true);
		
	}

	private void addDriverRecords(RentRecord record) {
		long licenseId=record.getLicenseId();
		List<RentRecord>records=driverRecords.get(licenseId);
		if(records==null) {
			records=new LinkedList<>();
			driverRecords.put(licenseId, records);
		}
		records.add(record);
		
	}

	private void addCarRecords(RentRecord record) {
		String regNumber=record.getCarNumber();
		List<RentRecord> records=carRecords.get(regNumber);
		if(records==null) {
			records=new LinkedList<>();
			carRecords.put(regNumber, records);
		}
		records.add(record);
	}

	private CarsReturnCode checkRentCar(String carNumber, long licenseId) {
		Car car=getCar(carNumber);
		if(car==null || car.isFlRemoved())
			return CarsReturnCode.NO_CAR;
		if(car.isInUse())
			return CarsReturnCode.CAR_IN_USE;
		if(getDriver(licenseId)==null)
			return CarsReturnCode.NO_DRIVER;
		return CarsReturnCode.OK;
	}

	@Override
	public CarsReturnCode returnCar(String carNumber, long licenseId,
			LocalDate returnDate, int gasTankPercent,int damages) {
			RentRecord record=getRentRecord(carNumber,licenseId);
			if(record==null)
				return CarsReturnCode.CAR_NOT_RENTED;
			if(returnDate.isBefore(record.getRentDate()))
				return CarsReturnCode.RETURN_DATE_WRONG;
			updateRecord(returnDate, gasTankPercent, damages, record);
			Car car=cars.get(carNumber);
			if(car==null || car.isFlRemoved() || !car.isInUse())
				throw new RuntimeException
				("Information model inconsistency (the record exists but car doesn't)");
			setCost(record,car);
			updateCarData(damages, car);
			addReturnedRecords(record);
			return CarsReturnCode.OK;
		}

	private RentRecord getRentRecord(String carNumber, long licenseId) {
		List<RentRecord> records=driverRecords.get(licenseId);
		return records==null?null:records.stream()
				.filter(r->r.getCarNumber().equals(carNumber)&&r.getReturnDate()==null)
				.findFirst().orElse(null);
	}

	private void updateRecord(LocalDate returnDate, int gasTankPercent, int damages, RentRecord record) {
		record.setDamages(damages);
		record.setGasTankPercent(gasTankPercent);
		record.setReturnDate(returnDate);
	}

		private void addReturnedRecords(RentRecord record) {
			LocalDate returnDate=record.getReturnDate();
			List<RentRecord>records=returnedRecords.get(returnDate);
			if(records==null){
				records=new ArrayList<>();
				returnedRecords.put(returnDate, records);
			}
			records.add(record);
			
		}

		private void updateCarData(int damages, Car car) {
			if(damages>0 && damages<10)
				car.setState(State.GOOD);
			else if(damages>=10&&damages<30)
				car.setState(State.BAD);
			else if(damages>=30)
				car.setFlRemoved(true);
			car.setInUse(false);
		}

		private void setCost(RentRecord record, Car car) {
			long period=ChronoUnit.DAYS.between
					(record.getRentDate(), record.getReturnDate());
			float costPeriod=0;
			Model model=getModel(car.getModelName());
			if(model==null)
				throw new RuntimeException("Information model inconsistency (the car exists but model doesn't)");
			float costGas=0;
			costPeriod = getCostPeriod(record, period, model);
			costGas = getCostGas(record, model);
			record.setCost(costPeriod+costGas);
			
		}
		private float getCostGas(RentRecord record, Model model) {
			float costGas;
			int gasTank=model.getGasTank();
			float litersCost=(float)(100-record.getGasTankPercent())*gasTank/100;
			costGas=litersCost*getGasPrice();
			return costGas;
		}
		private float getCostPeriod(RentRecord record, long period, Model model) {
			float costPeriod;
			long delta=period-record.getRentDays();
			float additionalPeriodCost=0;
			int pricePerDay=model.getPriceDay();
			int rentDays=record.getRentDays();
			if(delta>0){
				additionalPeriodCost=getAdditionalPeriodCost
						(pricePerDay,delta);
			}
			costPeriod=rentDays*pricePerDay+additionalPeriodCost;
			return costPeriod;
		}

		private float getAdditionalPeriodCost(int pricePerDay, long delta) {
			float fineCostPerDay=pricePerDay*getFinePercent()/100;
			return (pricePerDay+fineCostPerDay)*delta;
		}


	@Override
	public CarsReturnCode removeCar(String carNumber) {
		Car car=cars.get(carNumber);
		if(car==null)
			return CarsReturnCode.NO_CAR;
		if(car.isInUse())
			return CarsReturnCode.CAR_IN_USE;
		car.setFlRemoved(true);
		return CarsReturnCode.OK;
	}

	@Override
	public List<Car> clear(LocalDate currentDate, int days) {
		Set<RentRecord> recordsForRemove=
				getRecordsForRemove(currentDate.minusDays(days));
		removeDriverRecords(recordsForRemove);
		List<Car>res=getCarsForRemove(recordsForRemove);
		return res;
	}
	private List<Car> getCarsForRemove(Set<RentRecord> recordsForRemove) {
		List<Car> res=new ArrayList<>();
		 recordsForRemove.stream()
				.map(r->cars.get(r.getCarNumber()))
				.distinct()
				.forEach(c->{
					res.add(c);
					cars.remove(c.getRegNumber());
				});
		 return res;
	}

	private void removeDriverRecords(Set<RentRecord> recordsForRemove) {
		recordsForRemove.stream().mapToLong(RentRecord::getLicenseId)
		.distinct().forEach(id->driverRecords.get(id)
				.removeIf(recordsForRemove::contains));
		
	}

	private Set<RentRecord> getRecordsForRemove(LocalDate beforeDate) {
		Set<RentRecord> res=new HashSet<>();
		returnedRecords.headMap(beforeDate).values()
		.forEach(r->{
			Iterator<RentRecord> it=r.iterator();
			while(it.hasNext()) {
				RentRecord record=it.next();
				Car car=cars.get(record.getCarNumber());
				if(car.isFlRemoved()) {
					res.add(record);
					carRecords.remove(car.getRegNumber());
					it.remove();
				}
			}
		});
		return res;
	}

	@Override
	public List<Driver> getCarDrivers(String carNumber) {
		List<RentRecord> records=carRecords.get(carNumber);
		return records==null?new ArrayList<>()
				:records.stream()
				.map(x->getDriver(x.getLicenseId()))
				.distinct().collect(Collectors.toList());
	}

	@Override
	public List<Car> getDriverCars(long licenseId) {
		List<RentRecord> records=driverRecords.get(licenseId);
		return records==null?new ArrayList<>()
				:records.stream()
				.map(x->getCar(x.getCarNumber()))
				.distinct().collect(Collectors.toList());
	}

	@Override
	public Stream<Car> getAllCars() {
		return cars.values().stream();
	}

	@Override
	public Stream<Driver> getAllDrivers() {
		return drivers.values().stream();
	}

	@Override
	public Stream<RentRecord> getAllRecords() {
		
		return carRecords.values().stream()
				.flatMap(Collection::stream);
	}

	@Override
	public List<String> getAllModelNames() {
		
		return models.values().stream().map(Model::getModelName)
				.collect(Collectors.toList());
	}

	@Override
	public List<String> getMostPopularModelNames() {
		Map<String,Long> modelOccurrences=
		carRecords.values().stream()
		.flatMap(List::stream)
		.collect(Collectors.groupingBy(this::getModelNameFromRecord,
				Collectors.counting()));
		long maxOccurrences=modelOccurrences.values().stream()
				.max(Long::compare).orElse(0l);
		
		return modelOccurrences.entrySet().stream()
				.filter(x->x.getValue()==maxOccurrences)
				.map(x->x.getKey()).collect(Collectors.toList());
	}
private String getModelNameFromRecord(RentRecord record) {
	return cars.get(record.getCarNumber()).getModelName();
}
	@Override
	public double getModelProfit(String modelName) {
		
		return carRecords.values().stream().flatMap(List::stream)
				.filter(r->getModelNameFromRecord(r).equals(modelName))
				.collect(Collectors
						.summingDouble(RentRecord::getCost));
	}

	@Override
	public List<String> getMostProfitModelNames() {
		Map<String,Double> modelOccurrences=
		carRecords.values().stream().flatMap(List::stream)
		.collect(Collectors.groupingBy(this::getModelNameFromRecord,
			Collectors.summingDouble(RentRecord::getCost)));
		double maxCost=modelOccurrences.values().stream()
				.max(Double::compare).orElse(0.0);
		return modelOccurrences.entrySet().stream()
				.filter(x->Double.compare(x.getValue(), maxCost)==0)
				.map(x->x.getKey()).collect(Collectors.toList());
	}

}
