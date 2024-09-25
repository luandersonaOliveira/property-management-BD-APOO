package Exceptions;
// Pagamento Exception

public class PaymentException extends Exception{
    public PaymentException(String mensagem) {
        super(mensagem);
    }
}
