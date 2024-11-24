package interfaces;

import java.sql.SQLException;
import java.util.List;

import entity.PropertyCommercial;

public interface IPropertyCommercial {

	public void save(PropertyCommercial commercial);

	public void updateAll(PropertyCommercial commercial);

	public void updateAddress(PropertyCommercial commercial);

	public void updateRentalValue(PropertyCommercial commercial);

	public void updateType(PropertyCommercial commercial);

	public void updateOccupation(PropertyCommercial commercial);

	public void updateBusiness(PropertyCommercial commercial);

	public void deleteByID(int id);

	public List<PropertyCommercial> getProperty() throws SQLException;

	public PropertyCommercial getPropertyById(int id) throws SQLException;
}
