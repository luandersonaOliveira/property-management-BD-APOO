package Interface;
// Interface Repositório de Pagamento

import java.util.ArrayList;

import Entity.Payment;

public interface IPaymentRepository {
    public void addPayment(Payment payment);

    public void removePayment(int id);

    public void changePayment(int id);

    public ArrayList<Payment> listPayment();

    public Payment searchPayment(int id);
}
