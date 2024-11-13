package entity;
// Inquilino

import Enum.PersonPosition;

public class Tenant extends Person{
	// ATTRIBUTES

	private int id;
	private double balance;
	private Property property;

	// CONSTRUCTOR

	public Tenant() {

	}

	public Tenant(String name, String cpf, String telephone, String email, double balance, PersonPosition position) {
		super(name, cpf, telephone, email, position);
		this.id = super.getId();
		this.balance = balance;
	}

	// METODOS ESPECIAS

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}
}
