package services;

import java.sql.SQLException;
import java.text.ParseException;

import Enum.EnumLeaseException;
import Enum.EnumPropertyException;
import containers.TenantLeaseRepository;
import entity.Lease;
import entity.Tenant;
import entity.TenantLease;
import exceptions.LeaseException;

public class TenantLeaseService {
	// ATTRIBUTES
	
	private static final TenantLeaseRepository tenantLeaseDAO = new TenantLeaseRepository();

	// CREATE
	public void addTenantLease(Tenant tenant, Lease lease) throws LeaseException, ParseException {
		if (tenant == null || lease == null) {
			throw new LeaseException("Erro: " + EnumLeaseException.LeaseNoRegistered);
		}

		TenantLease tenantLease = createTenantLease(tenant, lease);
		if (lease != null) {
			tenantLeaseDAO.tenantLeaseSave(tenantLease);
			System.out.println("\nContrato adicionado com sucesso!");
		} else {
			System.out.println("Erro: " + EnumLeaseException.LeaseInvalid);
		}
	}

	public TenantLease createTenantLease(Tenant tenant, Lease lease) throws LeaseException, ParseException {
		if (tenant.getId() <= 0) {
			throw new LeaseException("Erro: " + EnumLeaseException.LeaseInvalid);
		} else if (lease.getId() <= 0) {
			throw new LeaseException("Erro: " + EnumLeaseException.LeaseNoRegistered);
		}

		return new TenantLease(tenant, lease);
	}

	public void removeTenantLease(int id) {
		tenantLeaseDAO.tenantLeaseDeleteByID(id);
	}

	// LIST
	public void listTenantLease() throws SQLException {
		if (tenantLeaseDAO.getTenantLease().isEmpty()) {
			System.out.println(("Erro: " + EnumPropertyException.PropertyNoRegistered));
		} else {
			for (TenantLease ts : tenantLeaseDAO.getTenantLease()) {
				System.out.print("\nID TenantLease: " + ts.getId() + "\n");
				System.out.print("\n | ID Contrato: " + ts.getLease().getId());
				System.out.print("\n | ID Inquilino: " + ts.getTenant().getId());
			}
		}
	}

}
