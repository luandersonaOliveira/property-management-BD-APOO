package interfaces;
// Interface Repositório de Inquilinos

import java.sql.SQLException;
import java.util.List;

import entity.Tenant;

public interface ITenantRepository {
	public void save(Tenant tenant);

	public void updateAll(Tenant tenant);

	public void updateName(Tenant tenant);

	public void updateEmail(Tenant tenant);

	public void updateWallet(Tenant tenant);

	public void deleteByID(int id);

	public List<Tenant> getTenants() throws SQLException;

	public Tenant getTenantById(int id) throws SQLException;
}
