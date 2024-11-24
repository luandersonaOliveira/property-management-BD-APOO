package interfaces;

import java.sql.SQLException;
import java.util.List;

import entity.PropertyResidential;

public interface IPropertyResidential {
	public void save(PropertyResidential residential);

	public void updateAll(PropertyResidential residential);

	public void updateAddress(PropertyResidential residential);

	public void updateRentalValue(PropertyResidential residential);

	public void updateType(PropertyResidential residential);

	public void updateOccupation(PropertyResidential residential);

	public void updateArea(PropertyResidential residential);

	public void deleteByID(int id);

	public List<PropertyResidential> getProperty() throws SQLException;

	public PropertyResidential getPropertyById(int id) throws SQLException;
}
