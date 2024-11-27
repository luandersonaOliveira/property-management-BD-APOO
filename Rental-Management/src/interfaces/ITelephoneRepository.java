package interfaces;

import java.sql.SQLException;
import java.util.List;

import entity.Person;

public interface ITelephoneRepository {

	public void save(Person telephone);

	public void updateAll(Person telephone);

	public void updateFirstTelephone(Person telephone);

	public void updateSecondTelephone(Person telephone);

	public void deleteByID(int id);

	public List<Person> getTelephone() throws SQLException;

	public Person getTelephoneById(int id) throws SQLException;

}
