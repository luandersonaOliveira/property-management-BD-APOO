package Enum;
// Enum Pagamento Exception

public enum EnumPaymentException {
    // VALID
    PaymentAddedSuccessfully,
    PaymentRemovedSuccessfully,
    PaymentChangedSuccessfully,

    // INVALID
    PaymentInvalidIndex,
    PaymentInvalidDatePayment,
    PaymentInvalidValue,

    // NO REGISTERED
    PaymentInvalid,
    PaymentNotUpdated,
    PaymentNoRegistered,
    LeaseNotAddedToPayment;
}
