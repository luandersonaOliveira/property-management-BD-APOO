package Interface;
// Interface Repositório de Imovel

import java.util.ArrayList;

import Entity.Property;

public interface IPropertyRepository {
    public void addProperty(Property property);

    public void removeProperty(int id);

    public void changeProperty(int id);

    public ArrayList<Property> listProperty();

    public Property searchProperty(int id);
}
