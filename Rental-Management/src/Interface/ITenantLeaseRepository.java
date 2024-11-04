package Interface;

import java.sql.SQLException;
import java.util.List;

import entity.TenantLease;

public interface ITenantLeaseRepository {
	public void tenantLeaseSave(TenantLease tenantLease);
	
	public void tenantLeaseUpdateAll(TenantLease tenantLease);
	
	public void tenantLeaseUpdateIdTenant(TenantLease tenantLease);
	
	public void tenantLeaseUpdateIdLease(TenantLease tenantLease);
	
	public void tenantLeaseDeleteByID(int id);
	
	public List<TenantLease> getTenantLease() throws SQLException;
}
