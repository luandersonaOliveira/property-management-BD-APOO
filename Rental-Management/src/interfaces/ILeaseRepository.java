package interfaces;
// Interface Repositório de Locação (Contratos)

import java.sql.SQLException;
import java.util.List;

import entity.Lease;

public interface ILeaseRepository {
	
	public void save(Lease lease);
	
	public void updateAll(Lease lease);
	
	public void updateStartDate(Lease lease);
	
	public void updateEndDate(Lease lease);

	public void deleteByID(int id);
	
	public List<Lease> getLease() throws SQLException;
	
	public Lease getLeaseById(int id) throws SQLException;
	
}
