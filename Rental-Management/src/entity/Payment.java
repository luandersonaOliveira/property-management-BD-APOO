package entity;
// Pagamento

public class Payment {
	// ATTRIBUTES

	private int id;
	private double value;
	private String datePayment;
	private Lease lease;

	// CONSTRUCTOR

	public Payment(double value, String datePayment, Lease lease) {
		this.value = value;
		this.datePayment = datePayment;
		this.lease = lease;
	}

	// METODOS ESPECIAS

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getDatePayment() {
		return datePayment;
	}

	public void setDatePayment(String datePayment) {
		this.datePayment = datePayment;
	}

	public Lease getLease() {
		return lease;
	}

	public void setLease(Lease lease) {
		this.lease = lease;
	}
}
