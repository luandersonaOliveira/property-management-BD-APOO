package interfaces;

import java.sql.SQLException;
import java.util.List;

import entity.Person;

public interface ITelephoneRepository {

	public void save(Person person);

	public void updateTelephone(Person person);

	public void deleteByID(int id);

	public List<Person> getTelephone() throws SQLException;

	public Person getTelephoneById(int id) throws SQLException;

}
