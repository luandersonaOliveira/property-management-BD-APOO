package entity;

import java.util.List;

import enums.PropertyOccupation;
import enums.PropertyType;
import enums.TheTypeOfBusiness;

public class Property {
	// ATTRIBUTES

	private int id, numberOfRooms;
	private String address;
	private double rentalValue;
	private PropertyType type;
	private PropertyOccupation occupation;
	private TheTypeOfBusiness business;
	private boolean theLeisureArea;
	private Landlord landlord;
	private List<Tenant> tenant;

	// CONSTRUCTOR

	public Property() {

	}

	public Property(String address, double rentalValue, PropertyType type, PropertyOccupation occupation) {
		this.address = address;
		this.rentalValue = rentalValue;
		this.type = type;
		this.occupation = occupation;
	}
	
	public Property(String address, double rentalValue, PropertyType type, PropertyOccupation occupation,
			int numberOfRooms, TheTypeOfBusiness business) {
		this.address = address;
		this.rentalValue = rentalValue;
		this.type = type;
		this.occupation = occupation;
		this.numberOfRooms = numberOfRooms;
		this.business = business;
	}
	
	public Property(String address, double rentalValue, PropertyType type, PropertyOccupation occupation,
			int numberOfRooms, boolean theLeisureArea) {
		this.address = address;
		this.rentalValue = rentalValue;
		this.type = type;
		this.occupation = occupation;
		this.numberOfRooms = numberOfRooms;
		this.theLeisureArea = theLeisureArea;
	}


	// SPECIAL METHODS

	public int getId() {
		return id;
	}
	
	public int getNumberOfRooms() {
		return numberOfRooms;
	}
	
	public String getAddress() {
		return address;
	}
	
	public double getRentalValue() {
		return rentalValue;
	}
	
	public PropertyType getType() {
		return type;
	}
	
	public PropertyOccupation getOccupation() {
		return occupation;
	}
	
	public TheTypeOfBusiness getBusiness() {
		return business;
	}
	
	public boolean isTheLeisureArea() {
		return theLeisureArea;
	}
	
	public Landlord getLandlord() {
		return landlord;
	}
	
	public List<Tenant> getTenant() {
		return tenant;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setRentalValue(double rentalValue) {
		this.rentalValue = rentalValue;
	}
	
	public void setType(PropertyType type) {
		this.type = type;
	}
	
	public void setOccupation(PropertyOccupation occupation) {
		this.occupation = occupation;
	}
	
	public void setBusiness(TheTypeOfBusiness business) {
		this.business = business;
	}
	
	public void setTheLeisureArea(boolean theLeisureArea) {
		this.theLeisureArea = theLeisureArea;
	}
	
	public void setLandlord(Landlord landlord) {
		this.landlord = landlord;
	}
	
	public void setTenant(List<Tenant> tenant) {
		this.tenant = tenant;
	}

}
