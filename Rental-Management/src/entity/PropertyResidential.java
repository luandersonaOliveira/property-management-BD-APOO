package entity;

import Enum.PropertyOccupation;
import Enum.PropertyType;

public class PropertyResidential extends Property {
	// ATTRIBUTES

	private int id, numberOfRooms;
	private boolean theLeisureArea;

	// CONSTRUCTOR
	public PropertyResidential() {

	}

	public PropertyResidential(String address, double rentalValue, PropertyType type, PropertyOccupation occupation,
			int numberOfRooms, boolean theLeisureArea) {
		super(address, rentalValue, type, occupation);
		this.id = super.getId();
		this.numberOfRooms = numberOfRooms;
		this.theLeisureArea = theLeisureArea;
	}

	// METODOS ESPECIAS

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	public boolean isTheLeisureArea() {
		return theLeisureArea;
	}

	public void setTheLeisureArea(boolean theLeisureArea) {
		this.theLeisureArea = theLeisureArea;
	}

}
