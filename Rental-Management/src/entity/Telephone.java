package entity;

public class Telephone {
	// ATTRIBUTES

	private int id;
	private String firstTelephone, secondTelephone;
	private Person person;

	// CONSTRUCTOR
	
	public Telephone() {
		
	}

	public Telephone(String firstTelephone, String secondTelephone, Person person) {
		this.firstTelephone = firstTelephone;
		this.secondTelephone = secondTelephone;
		this.person = person;
		this.id = person.getId();
	}

	// SPECIAL METHODS

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstTelephone() {
		return firstTelephone;
	}

	public void setFirstTelephone(String firstTelephone) {
		this.firstTelephone = firstTelephone;
	}

	public String getSecondTelephone() {
		return secondTelephone;
	}

	public void setSecondTelephone(String secondTelephone) {
		this.secondTelephone = secondTelephone;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
