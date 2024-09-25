package Exceptions;
// Proprietário Exception

public class LandlordException extends Exception {
    public LandlordException(String mensagem) {
        super(mensagem);
    }
}
