package telran.cars.dto;

import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("serial")
public class RentRecord implements Serializable{
private long licenseId;
private String carNumber;
private LocalDate rentDate;
private LocalDate returnDate;
private int gasTankPercent;
private int rentDays;
private float cost;
private int damages;
public RentRecord(){}
public RentRecord(long licenseId, String carNumber, LocalDate rentDate, int rentDays) {
	super();
	this.licenseId = licenseId;
	this.carNumber = carNumber;
	this.rentDate = rentDate;
	this.rentDays = rentDays;
}
public LocalDate getReturnDate() {
	return returnDate;
}
public void setReturnDate(LocalDate returnDate) {
	this.returnDate = returnDate;
}
public int getGasTankPercent() {
	return gasTankPercent;
}
public void setGasTankPercent(int gasTankPercent) {
	this.gasTankPercent = gasTankPercent;
}
public float getCost() {
	return cost;
}
public void setCost(float cost) {
	this.cost = cost;
}
public int getDamages() {
	return damages;
}
public void setDamages(int damages) {
	this.damages = damages;
}
public long getLicenseId() {
	return licenseId;
}
public String getCarNumber() {
	return carNumber;
}
public LocalDate getRentDate() {
	return rentDate;
}
public int getRentDays() {
	return rentDays;
}
@Override
public String toString() {
	return "RentRecord [licenseId=" + licenseId + ", carNumber=" + carNumber + ", rentDate=" + rentDate
			+ ", returnDate=" + returnDate + ", gasTankPercent=" + gasTankPercent + ", rentDays=" + rentDays + ", cost="
			+ cost + ", damages=" + damages + "]";
}

}
