package Entity;
// Herança Aluguel Comercial

import Enum.PropertyOccupation;
import Enum.PropertyType;

public class CommercialProperty extends Property {
    // ATTRIBUTES

    private int numberOfRooms;
    private String typeOfTrade;

    // CONSTRUCTOR

    public CommercialProperty(Landlord landlord, String address, double rentalValue, PropertyType type,
            PropertyOccupation occupation) {
        super(address, rentalValue, type, occupation);
        this.setLandlord(landlord);
    }

    // METODOS ESPECIAS

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public String getTypeOfTrade() {
        return typeOfTrade;
    }

    public void setTypeOfTrade(String typeOfTrade) {
        this.typeOfTrade = typeOfTrade;
    }
}
