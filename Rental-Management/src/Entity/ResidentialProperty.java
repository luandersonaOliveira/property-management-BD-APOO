package Entity;
// Herança Aluguel Residencial

import Enum.PropertyOccupation;
import Enum.PropertyType;

public class ResidentialProperty extends Property {
    // ATTRIBUTES

    private int numberOfRooms;
    private boolean ItHasALeisureArea;

    // CONSTRUCTOR

    public ResidentialProperty(Landlord landlord, String address, double rentalValue, PropertyType type,
            PropertyOccupation occupation) {
        super(address, rentalValue, type, occupation);
        this.setLandlord(landlord);
    }

    // METODOS ESPECIAS

    public int getnumberOfRooms() {
        return numberOfRooms;
    }

    public void setnumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public boolean isItHasALeisureArea() {
        return ItHasALeisureArea;
    }

    public void setItHasALeisureArea(boolean itHasALeisureArea) {
        ItHasALeisureArea = itHasALeisureArea;
    }
}
