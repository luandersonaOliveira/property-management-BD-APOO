package entity;

import Enum.PropertyOccupation;
import Enum.PropertyType;
import Enum.TheTypeOfBusiness;

public class PropertyCommercial extends Property {
	// ATTRIBUTES

	private int id, numberOfRooms;
	private TheTypeOfBusiness business;

	// CONSTRUCTOR
	public PropertyCommercial() {

	}

	public PropertyCommercial(String address, double rentalValue, PropertyType type, PropertyOccupation occupation,
			int numberOfRooms, TheTypeOfBusiness business) {
		super(address, rentalValue, type, occupation);
		this.id = super.getId();
		this.numberOfRooms = numberOfRooms;
		this.business = business;
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

	public TheTypeOfBusiness getBusiness() {
		return business;
	}

	public void setBusiness(TheTypeOfBusiness business) {
		this.business = business;
	}

}
