package entity;
// Proprietário (Espefico de imovel)

import java.util.List;

import Enum.PersonPosition;

public class Landlord extends Person{
	// ATTRIBUTES

	private int id;
	private List<Property> property;

	// CONSTRUCTOR

	public Landlord() {

	}

	public Landlord(String name, String cpf, String telephone, String email, PersonPosition position) {
		super(name, cpf, telephone, email, position);
		this.id = super.getId();
	}
	
	// METODOS ESPECIAS

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
