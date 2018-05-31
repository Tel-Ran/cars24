package telran.cars.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Driver implements Serializable{
@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + birthYear;
		result = prime * result + (int) (licenseId ^ (licenseId >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
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
		Driver other = (Driver) obj;
		if (birthYear != other.birthYear)
			return false;
		if (licenseId != other.licenseId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}
private long licenseId;
private String name;
private int birthYear;
private String phone;
public Driver(long licenseId, String name, int birthYear, String phone) {
	super();
	this.licenseId = licenseId;
	this.name = name;
	this.birthYear = birthYear;
	this.phone = phone;
}
public Driver(){}
public long getLicenseId() {
	return licenseId;
}
@Override
public String toString() {
	return "Driver [licenseId=" + licenseId + ", name=" + name + ", birthYear=" + birthYear + ", phone=" + phone + "]";
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getName() {
	return name;
}
public int getBirthYear() {
	return birthYear;
}
public String getPhone() {
	return phone;
}
}
