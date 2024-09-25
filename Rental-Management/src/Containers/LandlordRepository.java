package Containers;
// Repositório do Proprietário

import java.util.ArrayList;

import Entity.Landlord;
import Interface.ILandlordRepository;

public class LandlordRepository implements ILandlordRepository {
    public ArrayList<Landlord> landlords = new ArrayList<>();

    @Override
    public void addLandlord(Landlord landlord) {
        landlords.add(landlord);
    }

    @Override
    public void removeLandlord(int id) {
        landlords.remove(id);
    }

    @Override
    public void changeLandlord(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addLease'");
    }

    @Override
    public ArrayList<Landlord> listLandlord() {
        return landlords;
    }

    @Override
    public Landlord searchLandlord(int id) {
        if (id >= 0 && id < landlords.size()) {
            return landlords.get(id);
        }
        return null;
    }
}
