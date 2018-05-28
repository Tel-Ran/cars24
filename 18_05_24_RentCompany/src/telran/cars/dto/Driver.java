package telran.cars.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Driver implements Serializable{
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
