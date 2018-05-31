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

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((color == null) ? 0 : color.hashCode());
	result = prime * result + (flRemoved ? 1231 : 1237);
	result = prime * result + (inUse ? 1231 : 1237);
	result = prime * result + ((modelName == null) ? 0 : modelName.hashCode());
	result = prime * result + ((regNumber == null) ? 0 : regNumber.hashCode());
	result = prime * result + ((state == null) ? 0 : state.hashCode());
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
	Car other = (Car) obj;
	if (color == null) {
		if (other.color != null)
			return false;
	} else if (!color.equals(other.color))
		return false;
	if (flRemoved != other.flRemoved)
		return false;
	if (inUse != other.inUse)
		return false;
	if (modelName == null) {
		if (other.modelName != null)
			return false;
	} else if (!modelName.equals(other.modelName))
		return false;
	if (regNumber == null) {
		if (other.regNumber != null)
			return false;
	} else if (!regNumber.equals(other.regNumber))
		return false;
	if (state != other.state)
		return false;
	return true;
}
}
