package Containers;
// Repositório do Imovel

import java.util.ArrayList;

import Entity.Property;
import Interface.IPropertyRepository;

public class PropertyRepository implements IPropertyRepository {
    public ArrayList<Property> properties = new ArrayList<>();

    @Override
    public void addProperty(Property property) {
        properties.add(property);
    }

    @Override
    public void removeProperty(int id) {
        properties.remove(id);
    }

    @Override
    public void changeProperty(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'changeProperty'");
    }

    @Override
    public ArrayList<Property> listProperty() {
        return properties;
    }

    @Override
    public Property searchProperty(int id) {
        if (id >= 0 && id < properties.size()) {
            return properties.get(id);
        }
        return null;
    }
}
