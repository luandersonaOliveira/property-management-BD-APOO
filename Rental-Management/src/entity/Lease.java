package entity;
// Locação (Contrato)

public class Lease {
	// ATTRIBUTES

	private int id;
	private String startDate, endDate;
	private Property property;
	private Landlord landlord;
	private Tenant tenant;

	// CONSTRUCTOR

	public Lease(String startDate, String endDate, Landlord landlord, Property property, Tenant tenant) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.landlord = landlord;
		this.property = property;
		this.tenant = tenant;
	}

	// METODOS ESPECIAS

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public Landlord getLandlord() {
		return landlord;
	}

	public void setLandlord(Landlord landlord) {
		this.landlord = landlord;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

}
