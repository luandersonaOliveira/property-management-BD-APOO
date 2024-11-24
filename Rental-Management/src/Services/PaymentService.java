package services;
// Serviço Pagamento

import java.util.Scanner;

import entity.Lease;
import entity.Payment;
import enums.PaymentMethod;
import enums.PaymentStatus;
import exceptions.EnumPaymentException;
import exceptions.LeaseException;

public class PaymentService {
	// ATTRIBUTES
	
	private static final Scanner scanner = new Scanner(System.in);
	
	// ADD
	public void add(String paymentDate, double amountToPay, PaymentMethod method, PaymentStatus status, Lease lease) throws LeaseException {
		if (lease == null) {
			throw new LeaseException("Erro: " + EnumPaymentException.LeaseNotAddedToPayment);
		} else if (method == null || status == null) {
			throw new LeaseException("Erro: " + EnumPaymentException.PaymentInvalidValue);
		} else if (amountToPay < 0) {
			throw new LeaseException("Erro: " + EnumPaymentException.PaymentInvalidValue);
		}
		
		Payment payment = create();
		
	}
	
	public Payment create(String paymentDate, double amountToPay, PaymentMethod method, PaymentStatus status, Lease lease) {
		return new Payment(paymentDate, amountToPay, method, status, lease);
		
	}
	
}
