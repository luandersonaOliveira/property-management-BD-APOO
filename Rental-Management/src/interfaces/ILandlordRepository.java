package interfaces;
// Interface Repositório de Proprietário

import java.sql.SQLException;
import java.util.List;

import entity.Landlord;

public interface ILandlordRepository {
	public void save(Landlord landlord);

	public void updateAll(Landlord landlord);

	public void updateName(Landlord landlord);

	public void updateEmail(Landlord landlord);

	public void deleteByID(int id);

	public List<Landlord> getLandlords() throws SQLException;
	
	public Landlord getLandlordById(int id) throws SQLException;
}
