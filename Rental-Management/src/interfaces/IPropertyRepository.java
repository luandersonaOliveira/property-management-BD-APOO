package interfaces;
// Interface Repositório de Imovel

import java.sql.SQLException;
import java.util.List;

import entity.Property;
import entity.PropertyCommercial;
import entity.PropertyResidential;

public interface IPropertyRepository {
	public void saveCommercial(PropertyCommercial commercial);

	public void saveResidential(PropertyResidential residential);

	public void updateAllCommercial(PropertyCommercial commercial);

	public void updateAllResidential(PropertyResidential residential);

	public void updateAddressCommercial(PropertyCommercial commercial);

	public void updateAddressResidential(PropertyResidential residential);

	public void updateRentalValueCommercial(PropertyCommercial commercial);

	public void updateRentalValueResidential(PropertyResidential residential);

	public void updateTypeCommercial(PropertyCommercial commercial);

	public void updateTypeResidential(PropertyResidential residential);

	public void updateOccupationCommercial(PropertyCommercial commercial);

	public void updateOccupationResidential(PropertyResidential residential);

	public void updateBusiness(PropertyCommercial commercial);

	public void updateArea(PropertyResidential residential);

	public void deleteByIDCommercial(int id);

	public void deleteByIDResidential(int id);

	public List<PropertyCommercial> getPropertyCommercial() throws SQLException;

	public List<PropertyResidential> getPropertyResidential() throws SQLException;

	public PropertyCommercial getPropertyByIdCommercial(int id) throws SQLException;

	public PropertyResidential getPropertyByIdResidential(int id) throws SQLException;

	public List<Property> getPropertyByLandlordId(int id) throws SQLException;
}
