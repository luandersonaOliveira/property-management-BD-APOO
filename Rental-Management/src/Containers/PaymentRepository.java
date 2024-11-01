package containers;
// Repositório do Pagamento

import java.util.ArrayList;

import Interface.IPaymentRepository;
import entity.Payment;

public class PaymentRepository implements IPaymentRepository {
	public ArrayList<Payment> payments = new ArrayList<>();

	@Override
	public void addPayment(Payment payment) {
		payments.add(payment);
	}

	@Override
	public void removePayment(int id) {
		payments.remove(id);
	}

	@Override
	public void changePayment(int id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'changePayment'");
	}

	@Override
	public ArrayList<Payment> listPayment() {
		return payments;
	}

	@Override
	public Payment searchPayment(int id) {
		if (id >= 0 && id < payments.size()) {
			return payments.get(id);
		}
		return null;
	}

}
