package interfaces;
// Interface Repositório de Imovel

import java.sql.SQLException;
import java.util.List;

import entity.Property;

public interface IPropertyRepository {
	public void save(Property property);
	
	public void updateAll(Property property);
	
	public void updateAddress(Property property);
	
	public void updateRentalValue(Property property);
	
	public void updateType(Property property);
	
	public void updateOccupation(Property property);
	
	public void deleteByID(int id);
	
	public List<Property> getProperty() throws SQLException;
	
	public Property getPropertyById(int id) throws SQLException;
	
	public List<Property> getPropertyByLandlordId(int id) throws SQLException;
}
