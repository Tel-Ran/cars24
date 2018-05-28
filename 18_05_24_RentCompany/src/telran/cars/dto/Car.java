package telran.cars.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Car implements Serializable{
private String regNumber;
private String color;
private State state;
private String modelName;
private boolean inUse;
private boolean flRemoved;

public Car(String regNumber, String color, String modelName) {
	super();
	this.regNumber = regNumber;
	this.color = color;
	this.modelName = modelName;
	state=State.EXCELLENT;
}

@Override
public String toString() {
	return "Car [regNumber=" + regNumber + ", color=" + color + ", state=" + state + ", modelName=" + modelName
			+ ", inUse=" + inUse + ", flRemoved=" + flRemoved + "]";
}

public State getState() {
	return state;
}

public void setState(State state) {
	this.state = state;
}

public boolean isInUse() {
	return inUse;
}

public void setInUse(boolean inUse) {
	this.inUse = inUse;
}

public boolean isFlRemoved() {
	return flRemoved;
}

public void setFlRemoved(boolean flRemoved) {
	this.flRemoved = flRemoved;
}

public String getRegNumber() {
	return regNumber;
}

public String getColor() {
	return color;
}

public String getModelName() {
	return modelName;
}

public Car(){}
}
