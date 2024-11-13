package entity;

import Enum.PersonPosition;

public class Person {
	// ATTRIBUTES
	private int id;
	private String name, cpf, telephone, email;
	private PersonPosition position;

	// CONSTRUCTOR

	public Person() {

	}

	public Person(String name, String cpf, String telephone, String email, PersonPosition position) {
		this.setName(name);
		this.cpf = cpf;
		this.setEmail(email);
		this.setTelephone(telephone);
		this.setPosition(position);
	}

	// METODOS ESPECIAS

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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public PersonPosition getPosition() {
		return position;
	}

	public void setPosition(PersonPosition position) {
		this.position = position;
	}

}
