package Interface;
// Interface Repositório de Locação (Contratos)

import java.sql.SQLException;
import java.util.List;

import entity.Landlord;
import entity.Lease;

public interface ILeaseRepository {
	
	public void leaseSave(Lease lease);
	
	public void LeaseUpdateAll(Lease lease);
	
	public void LeaseUpdateStartDate(Lease lease);
	
	public void LeaseUpdateEndDate(Lease lease);

	public void LeaseDeleteByID(int id);
	
	public List<Lease> getLease() throws SQLException;
	
	public Lease getLeaseById(int id) throws SQLException;
	
}
