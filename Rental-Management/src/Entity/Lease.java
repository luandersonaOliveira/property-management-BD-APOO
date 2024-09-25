package Entity;
// Locação (Contrato)

public class Lease {
    private static int nextId = 0;

    // ATTRIBUTES

    private int id;
    private String startDate, endDate;
    private double value;
    private Landlord landlord;
    private Property property;
    private Tenant tenant;

    // CONSTRUCTOR

    public Lease(String startDate, String endDate, Landlord landlord, Property property, Tenant tenant) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.landlord = landlord;
        this.property = property;
        this.tenant = tenant;
        this.setId(nextId++);
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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Landlord getLandlord() {
        return landlord;
    }

    public void setLandlord(Landlord landlord) {
        this.landlord = landlord;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }
}
