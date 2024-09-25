package Enum;
// Enum Contrato Exception

public enum EnumLeaseException {
    // VALID
    LeaseAddedSuccessfully,
    LeaseRemovedSuccessfully,
    LeaseChangedSuccessfully,

    // INVALID
    LeaseInvalidIndex,
    LeaseInvalidStartOrEndDate,
    LeaseInvalidValue,
    
    // NO REGISTERED
    LeaseInvalid,
    LeaseNotUpdated,
    LeaseNoRegistered,
    TenantNotAddedToProperty,
    LandlordNotAddedToProperty, 
    LandlordAndTenantHaveTheSameCPF;
}
