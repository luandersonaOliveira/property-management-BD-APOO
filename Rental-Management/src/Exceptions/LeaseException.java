package Exceptions;
// Contrato Exception

public class LeaseException extends Exception {
    public LeaseException(String mensagem) {
        super(mensagem);
    }
}
