package entity;

import java.util.List;

import enums.PersonsPosition;

public class Landlord extends Person {
	// ATTRIBUTES

	private int id;
	private List<Property> property;

	// CONSTRUCTOR

	public Landlord() {

	}

	public Landlord(String name, String cpf, String email, double wallet, PersonsPosition positions) {
		super(name, cpf, email, wallet, positions);
		this.id = super.getId();
	}

	// SPECIAL METHODS

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Property> getProperty() {
		return property;
	}

	public void setProperty(List<Property> property) {
		this.property = property;
	}

}
