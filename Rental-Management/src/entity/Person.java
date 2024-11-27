package entity;

import java.util.ArrayList;

import enums.PersonsPosition;

public class Person {
	// ATTRIBUTES

	private int id;
	private String name, cpf, email;
	private double wallet;
	private ArrayList<String> telephone;
	private PersonsPosition positions;

	// CONSTRUCTOR

	public Person() {

	}

	public Person(String name, String cpf,  String email, double wallet, PersonsPosition positions) {
		this.name = name;
		this.cpf = cpf;
		this.email = email;
		this.wallet = (wallet);
		this.positions = positions;
		 this.telephone = new ArrayList<>();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<String> getTelephone() {
		return telephone;
	}

	public void addTelephone(String telephone) {
        this.telephone.add(telephone);
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
