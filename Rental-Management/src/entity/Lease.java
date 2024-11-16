package entity;

public class Lease {
	// ATTRIBUTES

	private int id;
	private String startDate, endDate, propertyReturnDate;
	private Boolean fines;
	private Property property;
	private Landlord landlord;
	private Tenant tenant;

	// CONSTRUCTOR

	public Lease() {

	}

	public Lease(String startDate, String endDate, Property property, Landlord landlord, Tenant tenant) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.property = property;
		this.landlord = landlord;
		this.tenant = tenant;
	}

	// SPECIAL METHODS

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

	public String getPropertyReturnDate() {
		return propertyReturnDate;
	}

	public void setPropertyReturnDate(String propertyReturnDate) {
		this.propertyReturnDate = propertyReturnDate;
	}

	public Boolean getFines() {
		return fines;
	}

	public void setFines(Boolean fines) {
		this.fines = fines;
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
