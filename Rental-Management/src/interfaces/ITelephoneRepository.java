package interfaces;

import java.sql.SQLException;
import java.util.List;

import entity.Telephone;

public interface ITelephoneRepository {

	public void save(Telephone telephone);

	public void updateAll(Telephone telephone);

	public void updateFirstTelephone(Telephone telephone);

	public void updateSecondTelephone(Telephone telephone);

	public void deleteByID(int id);

	public List<Telephone> getTelephone() throws SQLException;

	public Telephone getTelephoneById(int id) throws SQLException;

}
