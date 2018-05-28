package telran.cars.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Model implements Serializable{
private String modelName;
private int gasTank;
private String company;
private String country;
private int priceDay;
public Model(){}
public Model(String modelName, int gasTank, String company, String country, int priceDay) {
	super();
	this.modelName = modelName;
	this.gasTank = gasTank;
	this.company = company;
	this.country = country;
	this.priceDay = priceDay;
}
public int getPriceDay() {
	return priceDay;
}
public void setPriceDay(int priceDay) {
	this.priceDay = priceDay;
}
public String getModelName() {
	return modelName;
}
public int getGasTank() {
	return gasTank;
}
public String getCompany() {
	return company;
}
public String getCountry() {
	return country;
}
@Override
public String toString() {
	return "Model [modelName=" + modelName + ", gasTank=" + gasTank + ", company=" + company + ", country=" + country
			+ ", priceDay=" + priceDay + "]";
}

}
