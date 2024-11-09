package Interface;
// Interface Repositório de Imovel

import java.sql.SQLException;
import java.util.List;

import entity.Property;

public interface IPropertyRepository {
	public void propertySave(Property property);
	
	public void propertyUpdateAll(Property property);
	
	public void propertyUpdateAddress(Property property);
	
	public void propertyUpdateRentalValue(Property property);
	
	public void propertyUpdateType(Property property);
	
	public void propertyUpdateOccupation(Property property);
	
	public void propertyDeleteByID(int id);
	
	public List<Property> getProperty() throws SQLException;
	
	public Property getPropertyById(int id) throws SQLException;
	
	public List<Property> getPropertyByLandlordId(int id) throws SQLException;
}
