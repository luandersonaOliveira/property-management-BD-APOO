package entity;

import Enum.PaymentMethod;
import Enum.PaymentStatus;

// Pagamento

public class Payment {
	// ATTRIBUTES

	private int id;
	private String paymentDate;
	private double amountToPay;
	private PaymentMethod method;
	private PaymentStatus status;
	private Lease lease;

	// CONSTRUCTOR

	public Payment() {

	}

	public Payment(String paymentDate, double amountToPay, PaymentMethod method, PaymentStatus status, Lease lease) {
		this.paymentDate = paymentDate;
		this.amountToPay = amountToPay;
		this.method = method;
		this.status = status;
		this.lease = lease;
	}

	// METODOS ESPECIAS

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getAmountToPay() {
		return amountToPay;
	}

	public void setAmountToPay(double amountToPay) {
		this.amountToPay = amountToPay;
	}

	public PaymentMethod getMethod() {
		return method;
	}

	public void setMethod(PaymentMethod method) {
		this.method = method;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	public Lease getLease() {
		return lease;
	}

	public void setLease(Lease lease) {
		this.lease = lease;
	}

}
