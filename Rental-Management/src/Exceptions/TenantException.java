package Exceptions;
// Inquilino Exception

public class TenantException extends Exception {
    public TenantException(String mensagem) {
        super(mensagem);
    }
}
