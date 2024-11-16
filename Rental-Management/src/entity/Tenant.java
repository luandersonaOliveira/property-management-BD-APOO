package entity;

import enums.PersonsPosition;

public class Tenant extends Person {
	// ATTRIBUTES

	private int id;
	private Property property;

	// CONSTRUCTOR

	public Tenant() {

	}

	public Tenant(String name, String cpf, String telephone, String email, double wallet, PersonsPosition positions) {
		super(name, cpf, telephone, email, wallet, positions);
		this.id = super.getId();
	}

	// SPECIAL METHODS

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

}
