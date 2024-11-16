package entity;

import enums.PropertyOccupation;
import enums.PropertyType;
import enums.TheTypeOfBusiness;

public class PropertyCommercial extends Property {
	// ATTRIBUTES

	private int id;

	// CONSTRUCTOR

	public PropertyCommercial() {

	}

	public PropertyCommercial(String address, double rentalValue, PropertyType type, PropertyOccupation occupation,
			int numberOfRooms, TheTypeOfBusiness business) {
		super(address, rentalValue, type, occupation, numberOfRooms, business);
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
