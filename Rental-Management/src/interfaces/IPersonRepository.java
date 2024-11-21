package interfaces;

import java.sql.SQLException;
import java.util.List;

import entity.Landlord;
import entity.Tenant;

public interface IPersonRepository {

	public void saveLandlord(Landlord landlord);
	
	public void saveTenant(Tenant tenant);
	
	public void updateAllLandlord(Landlord landlord);
	
	public void updateAllTenant(Tenant tenant);
	
	public void updateLandlordName(Landlord landlord);
	
	public void updateTenantName(Tenant tenant);
	
	public void updateLandlordTelephone(Landlord landlord);
	
	public void updateTenantTelephone(Tenant tenant);
	
	public void updateLandlordEmail(Landlord landlord);
	
	public void updateTenantEmail(Tenant tenant);
	
	public void updateTenantWallet(Tenant tenant);
	
	public void deleteLandlordByID(int id);
	
	public void deleteTenantByID(int id);
	
	public List<Landlord> getLandlords() throws SQLException;
	
	public List<Tenant> getTenants() throws SQLException;
	
	public Landlord getLandlordById(int id) throws SQLException;
	
	public Tenant getTenantById(int id) throws SQLException;

}
