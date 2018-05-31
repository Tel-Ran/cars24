package telran.cars.dto;

import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("serial")
public class RentRecord implements Serializable{
@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carNumber == null) ? 0 : carNumber.hashCode());
		result = prime * result + Float.floatToIntBits(cost);
		result = prime * result + damages;
		result = prime * result + gasTankPercent;
		result = prime * result + (int) (licenseId ^ (licenseId >>> 32));
		result = prime * result + ((rentDate == null) ? 0 : rentDate.hashCode());
		result = prime * result + rentDays;
		result = prime * result + ((returnDate == null) ? 0 : returnDate.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RentRecord other = (RentRecord) obj;
		if (carNumber == null) {
			if (other.carNumber != null)
				return false;
		} else if (!carNumber.equals(other.carNumber))
			return false;
		if (Float.floatToIntBits(cost) != Float.floatToIntBits(other.cost))
			return false;
		if (damages != other.damages)
			return false;
		if (gasTankPercent != other.gasTankPercent)
			return false;
		if (licenseId != other.licenseId)
			return false;
		if (rentDate == null) {
			if (other.rentDate != null)
				return false;
		} else if (!rentDate.equals(other.rentDate))
			return false;
		if (rentDays != other.rentDays)
			return false;
		if (returnDate == null) {
			if (other.returnDate != null)
				return false;
		} else if (!returnDate.equals(other.returnDate))
			return false;
		return true;
	}
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
