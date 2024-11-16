package entity;

import enums.PropertyOccupation;
import enums.PropertyType;

public class PropertyResidential extends Property {
	// ATTRIBUTES

	private int id;

	// CONSTRUCTOR

	public PropertyResidential() {

	}

	public PropertyResidential(String address, double rentalValue, PropertyType type, PropertyOccupation occupation,
			int numberOfRooms, boolean theLeisureArea) {
		super(address, rentalValue, type, occupation, numberOfRooms, theLeisureArea);
		this.id = super.getId();
	}

	// SPECIAL METHODS

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
