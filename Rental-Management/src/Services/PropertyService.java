package services;
// Serviço Imovel

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Scanner;

import containers.PropertyRepository;
import entity.Landlord;
import entity.Property;
import entity.PropertyCommercial;
import entity.PropertyResidential;
import enums.PropertyOccupation;
import enums.PropertyType;
import enums.TheTypeOfBusiness;
import exceptions.EnumLandlordException;
import exceptions.EnumPropertyException;
import exceptions.PropertyException;

public class PropertyService {
	// ATTRIBUTES

	private static final Scanner scanner = new Scanner(System.in);
	private static final PropertyRepository propertyDAO = new PropertyRepository();

	// ADD
	public void add(Landlord landlord, String address, double rentalValue, PropertyType type,
			PropertyOccupation occupation, int numberOfRooms, TheTypeOfBusiness business, boolean theLeisureArea)
			throws PropertyException {

		switch (type) {
		case COMMERCIAL:

			if (landlord == null) {
				throw new PropertyException("Erro: " + EnumLandlordException.LandlordInvalid);
			} else if (rentalValue < 0) {
				throw new PropertyException("Erro: " + EnumPropertyException.PropertyInvalidRentalValue);
			}

			Property prC = new Property(address, rentalValue, type, occupation, numberOfRooms, business);
			PropertyCommercial commercial = createCommercial(prC.getLandlord(), prC.getAddress(), prC.getRentalValue(),
					prC.getType(), prC.getOccupation(), prC.getNumberOfRooms(), prC.getBusiness());

			if (commercial != null) {
				commercial.setLandlord(landlord);
				commercial.getLandlord().setCpf(landlord.getCpf());
				propertyDAO.saveCommercial(commercial);
			} else {
				System.out.println(("Erro: " + EnumPropertyException.PropertyInvalid));
			}
			break;
		case RESIDENTIAL:

			if (landlord == null) {
				throw new PropertyException("Erro: " + EnumLandlordException.LandlordInvalid);
			} else if (rentalValue < 0) {
				throw new PropertyException("Erro: " + EnumPropertyException.PropertyInvalidRentalValue);
			}

			Property prR = new Property(address, rentalValue, type, occupation, numberOfRooms, theLeisureArea);
			PropertyResidential residential = createResidential(prR.getLandlord(), prR.getAddress(),
					prR.getRentalValue(), prR.getType(), prR.getOccupation(), prR.getNumberOfRooms(),
					prR.isTheLeisureArea());

			if (residential != null) {
				residential.setLandlord(landlord);
				residential.getLandlord().setCpf(landlord.getCpf());
				propertyDAO.saveResidential(residential);
			} else {
				System.out.println(("Erro: " + EnumPropertyException.PropertyInvalid));
			}
			break;
		default:
			System.out.println(("Erro: " + EnumPropertyException.PropertyInvalid));
			break;
		}

	}

	// CREATE COMMERCIAL AND RESIDENTIAL
	public PropertyCommercial createCommercial(Landlord landlord, String address, double rentalValue, PropertyType type,
			PropertyOccupation occupation, int numberOfRooms, TheTypeOfBusiness business) throws PropertyException {
		return new PropertyCommercial(addressFormat(address), rentalValue, type, occupation, numberOfRooms, business);

	}

	public PropertyResidential createResidential(Landlord landlord, String address, double rentalValue,
			PropertyType type, PropertyOccupation occupation, int numberOfRooms, boolean theLeisureArea) {
		return new PropertyResidential(addressFormat(address), rentalValue, type, occupation, numberOfRooms,
				theLeisureArea);

	}

	// Formart
	private String addressFormat(String address) {
		String addressFormat = address.toUpperCase();
		return addressFormat;
	}

	private String walletFormat(double wallet) {
		DecimalFormat df = new DecimalFormat("###,###.00");
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator(',');
		dfs.setGroupingSeparator('.');
		df.setDecimalFormatSymbols(dfs);
		return df.format(wallet);
	}

	// REMOVE
	public void removeCommercial(int id) {
		propertyDAO.deleteByIDCommercial(id);
	}

	// REMOVE
	public void removeResidential(int id) {
		propertyDAO.deleteByIDResidential(id);
	}

	// LIST
	public void list() throws SQLException {
		if (propertyDAO.getProperty().isEmpty()) {
			System.out.println(("Erro: " + EnumPropertyException.PropertyNoRegistered));
		} else {
			for (Property p : propertyDAO.getProperty()) {
				System.out.print("\nID Imóvel: " + p.getId() + "\n");
				System.out.print(" | Cpf Proprietário: " + p.getLandlord().getCpf());
				System.out.print("\n | Endereço: " + p.getAddress());
				System.out.print("\n | Valor do Aluguel: " + walletFormat(p.getRentalValue()));
				System.out.print("\n | Tipo: " + p.getType());
				System.out.print("\n | Ocupação: " + p.getOccupation());
				System.out.print("\n | Numeros de salas: " + p.getNumberOfRooms());

				if (p.getType() == PropertyType.COMMERCIAL) {
					System.out.print("\n | Tipo de Negócio: " + p.getBusiness() + "\n");
				} else {
					System.out.print("\n | Área de Lazer: " + p.isTheLeisureArea() + "\n");
				}
			}
		}
	}
	
	public void listCommercial() throws SQLException {
		if (propertyDAO.getPropertyCommercial().isEmpty()) {
			System.out.println(("Erro: " + EnumPropertyException.PropertyNoRegistered));
		} else {
			for (Property p : propertyDAO.getPropertyCommercial()) {
				System.out.print("\nID Imóvel: " + p.getId() + "\n");
				System.out.print(" | Cpf Proprietário: " + p.getLandlord().getCpf());
				System.out.print("\n | Endereço: " + p.getAddress());
				System.out.print("\n | Valor do Aluguel: " + walletFormat(p.getRentalValue()));
				System.out.print("\n | Tipo: " + p.getType());
				System.out.print("\n | Ocupação: " + p.getOccupation());
				System.out.print("\n | Numeros de salas: " + p.getNumberOfRooms());

				if (p.getType() == PropertyType.COMMERCIAL) {
					System.out.print("\n | Tipo de Negócio: " + p.getBusiness() + "\n");
				} else {
					System.out.print("\n | Área de Lazer: " + p.isTheLeisureArea() + "\n");
				}
			}
		}
	}

	public void listResidential() throws SQLException {
		if (propertyDAO.getPropertyResidential().isEmpty()) {
			System.out.println(("Erro: " + EnumPropertyException.PropertyNoRegistered));
		} else {
			for (Property p : propertyDAO.getPropertyResidential()) {
				System.out.print("\nID Imóvel: " + p.getId() + "\n");
				System.out.print(" | Cpf Proprietário: " + p.getLandlord().getCpf());
				System.out.print("\n | Endereço: " + p.getAddress());
				System.out.print("\n | Valor do Aluguel: " + walletFormat(p.getRentalValue()));
				System.out.print("\n | Tipo: " + p.getType());
				System.out.print("\n | Ocupação: " + p.getOccupation());
				System.out.print("\n | Numeros de salas: " + p.getNumberOfRooms());

				if (p.getType() == PropertyType.COMMERCIAL) {
					System.out.print("\n | Tipo de Negócio: " + p.getBusiness() + "\n");
				} else {
					System.out.print("\n | Área de Lazer: " + p.isTheLeisureArea() + "\n");
				}
			}
		}
	}

	public void listPropertyByLandlordId(int id) throws SQLException {
		if (propertyDAO.getProperty().isEmpty()) {
			System.out.println(("Erro: " + EnumPropertyException.PropertyNoRegistered));
		} else {
			for (Property p : propertyDAO.getPropertyByLandlordId(id)) {
				System.out.print("\nID Imóvel: " + p.getId() + "\n");
				System.out.print(" | Cpf Proprietário: " + p.getLandlord().getCpf());
				System.out.print("\n | Endereço: " + p.getAddress());
				System.out.print("\n | Valor do Aluguel: " + walletFormat(p.getRentalValue()));
				System.out.print("\n | Tipo: " + p.getType());
				System.out.print("\n | Ocupação: " + p.getOccupation());

				if (p.getType() == PropertyType.COMMERCIAL) {
					System.out.print("\n | Tipo de Negócio: " + p.getBusiness() + "\n");
				} else {
					System.out.print("\n | Área de Lazer: " + p.isTheLeisureArea() + "\n");
				}
			}
		}
	}

	// CHANGE
	public void changeCommercial(int id) throws PropertyException, SQLException {
		if (propertyDAO.getPropertyCommercial().isEmpty()) {
			System.out.println(("Erro: " + EnumPropertyException.PropertyNoRegistered));
		} else {
			if (id <= 0 || id > propertyDAO.getPropertyCommercial().size()) {
				throw new PropertyException("Erro: " + EnumPropertyException.PropertyInvalidIndex);
			}

			PropertyCommercial commercial = new PropertyCommercial();

			System.out.println(
					"\nQuais as novas informações do Imóvel deseja mudar? \n| 0.Nenhum | 1.Endereço | 2.Valor do Aluguel | 3.Tipo | 4.Ocupação |");
			System.out.print("\n| Opção: ");

			int option = scanner.nextInt();
			scanner.nextLine();
			switch (option) {
			case 1:
				System.out.print("Novo Endereço: ");
				String newAddress = scanner.nextLine();
				commercial.setAddress(addressFormat(newAddress));
				commercial.setId(id);
				propertyDAO.updateAddressCommercial(commercial);
				break;
			case 2:
				System.out.print("Novo Valor do Aluguel: ");
				double newRentalValue = scanner.nextDouble();
				commercial.setRentalValue(newRentalValue);
				commercial.setId(id);
				propertyDAO.updateRentalValueCommercial(commercial);
				break;
			case 3:
				System.out.print("Novo Tipo: \n1.Residencial | 2.Comercial |");
				System.out.print("\n| Opção: ");
				int newType = scanner.nextInt();

				if (newType == 1) {
					PropertyType propertyType = PropertyType.RESIDENTIAL;
					commercial.setType(propertyType);
					commercial.setId(id);
					propertyDAO.updateTypeCommercial(commercial);
				} else if (newType == 2) {
					PropertyType propertyType = PropertyType.COMMERCIAL;
					commercial.setType(propertyType);
					commercial.setId(id);
					propertyDAO.updateTypeCommercial(commercial);
				} else {
					throw new PropertyException("Erro: " + EnumPropertyException.PropertyInvalidType);
				}
				break;
			case 4:
				System.out.print("Nova Ocupação: \n1.Desocupado | 2.Ocupado |");
				System.out.print("\n| Opção: ");
				int newOccupation = scanner.nextInt();

				if (newOccupation == 1) {
					PropertyOccupation propertyOccupation = PropertyOccupation.UNOCCUPIED;
					commercial.setOccupation(propertyOccupation);
					commercial.setId(id);
					propertyDAO.updateOccupationCommercial(commercial);
				} else if (newOccupation == 2) {
					PropertyOccupation propertyOccupation = PropertyOccupation.OCCUPIED;
					commercial.setOccupation(propertyOccupation);
					commercial.setId(id);
					propertyDAO.updateOccupationCommercial(commercial);
				} else {
					throw new PropertyException("Erro: " + EnumPropertyException.PropertyInvalidOccupation);
				}
				break;
			default:
				option = 0;
				System.out.println("\nImóvel não foi atualizado!");
				break;
			}
		}
	}
	
	public void changeResidential(int id) throws PropertyException, SQLException {
		if (propertyDAO.getPropertyResidential().isEmpty()) {
			System.out.println(("Erro: " + EnumPropertyException.PropertyNoRegistered));
		} else {
			if (id <= 0 || id > propertyDAO.getPropertyResidential().size()) {
				throw new PropertyException("Erro: " + EnumPropertyException.PropertyInvalidIndex);
			}

			PropertyResidential residential = new PropertyResidential();

			System.out.println(
					"\nQuais as novas informações do Imóvel deseja mudar? \n| 0.Nenhum | 1.Endereço | 2.Valor do Aluguel | 3.Tipo | 4.Ocupação |");
			System.out.print("\n| Opção: ");

			int option = scanner.nextInt();
			scanner.nextLine();
			switch (option) {
			case 1:
				System.out.print("Novo Endereço: ");
				String newAddress = scanner.nextLine();
				residential.setAddress(addressFormat(newAddress));
				residential.setId(id);
				propertyDAO.updateAddressResidential(residential);
				break;
			case 2:
				System.out.print("Novo Valor do Aluguel: ");
				double newRentalValue = scanner.nextDouble();
				residential.setRentalValue(newRentalValue);
				residential.setId(id);
				propertyDAO.updateRentalValueResidential(residential);
				break;
			case 3:
				System.out.print("Novo Tipo: \n1.Residencial | 2.Comercial |");
				System.out.print("\n| Opção: ");
				int newType = scanner.nextInt();

				if (newType == 1) {
					PropertyType propertyType = PropertyType.RESIDENTIAL;
					residential.setType(propertyType);
					residential.setId(id);
					propertyDAO.updateTypeResidential(residential);
				} else if (newType == 2) {
					PropertyType propertyType = PropertyType.COMMERCIAL;
					residential.setType(propertyType);
					residential.setId(id);
					propertyDAO.updateTypeResidential(residential);
				} else {
					throw new PropertyException("Erro: " + EnumPropertyException.PropertyInvalidType);
				}
				break;
			case 4:
				System.out.print("Nova Ocupação: \n1.Desocupado | 2.Ocupado |");
				System.out.print("\n| Opção: ");
				int newOccupation = scanner.nextInt();

				if (newOccupation == 1) {
					PropertyOccupation propertyOccupation = PropertyOccupation.UNOCCUPIED;
					residential.setOccupation(propertyOccupation);
					residential.setId(id);
					propertyDAO.updateOccupationResidential(residential);
				} else if (newOccupation == 2) {
					PropertyOccupation propertyOccupation = PropertyOccupation.OCCUPIED;
					residential.setOccupation(propertyOccupation);
					residential.setId(id);
					propertyDAO.updateOccupationResidential(residential);
				} else {
					throw new PropertyException("Erro: " + EnumPropertyException.PropertyInvalidOccupation);
				}
				break;
			default:
				option = 0;
				System.out.println("\nImóvel não foi atualizado!");
				break;
			}
		}
	}

	// SEARCH
	public Property search(int id) throws SQLException, Exception {
		Property property = null;
		if (propertyDAO.getProperty().isEmpty()) {
			System.out.println("Erro: " + EnumLandlordException.LandlordNoRegistered);
		} else {
			property = propertyDAO.getPropertyByIdCommercial(id);
			property = propertyDAO.getPropertyByIdResidential(id);
			if (property == null) {
				System.out.println("Erro: Imóvel não encontrado.");

			}
		}
		return property;
	}

}
