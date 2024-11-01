package interfaces;
// Interface Repositório de Proprietário

import java.util.ArrayList;

import entity.Landlord;

public interface ILandlordRepository {
	public void addLandlord(Landlord landlord);

	public void removeLandlord(int id);

	public void changeLandlord(int id);

	public ArrayList<Landlord> listLandlord();

	public Landlord searchLandlord(int id);
}
