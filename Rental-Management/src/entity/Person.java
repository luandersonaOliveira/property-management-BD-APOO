package entity;

import enums.PersonsPosition;

public class Person {
	// ATTRIBUTES

	private int id;
	private String name, cpf, telephone, email;
	private double wallet;
	private PersonsPosition positions;

	// CONSTRUCTOR

	public Person() {

	}

	public Person(String name, String cpf, String telephone, String email, double wallet,PersonsPosition positions) {
		this.name = name;
		this.cpf = cpf;
		this.telephone = telephone;
		this.email = email;
		this.wallet = (wallet);
		this.positions = positions;
	}

	// SPECIAL METHODS

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getWallet() {
		return wallet;
	}

	public void setWallet(double wallet) {
		this.wallet = wallet;
	}

	public PersonsPosition getPositions() {
		return positions;
	}

	public void setPositions(PersonsPosition positions) {
		this.positions = positions;
	}

}
