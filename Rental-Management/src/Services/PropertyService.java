package services;
// Serviço Imovel

import java.sql.SQLException;
import java.util.Scanner;

import Enum.EnumLandlordException;
import Enum.EnumPropertyException;
import Enum.PropertyOccupation;
import Enum.PropertyType;
import containers.PropertyRepository;
import entity.Landlord;
import entity.Property;
import entity.PropertyResidential;
import exceptions.PropertyException;

public class PropertyService {
	// ATTRIBUTES

	private static final Scanner scanner = new Scanner(System.in);
	private static final PropertyRepository propertyDAO = new PropertyRepository();

	// CREATE
	public void addProperty(Landlord landlord, String address, double rentalValue, PropertyType type,
			PropertyOccupation occupation) throws PropertyException {
		if (landlord == null) {
			throw new PropertyException("Erro: " + EnumLandlordException.LandlordInvalid);
		} else if (type == null) {
			throw new PropertyException("Erro: " + EnumPropertyException.PropertyInvalidType);
		} else if (rentalValue < 0) {
			throw new PropertyException("Erro: " + EnumPropertyException.PropertyInvalidRentalValue);
		}

		Property property = createProperty(landlord, address, rentalValue, type, occupation);
		if (property != null) {
			property.setLandlord(landlord);
			property.getLandlord().setCpf(landlord.getCpf());
			propertyDAO.propertySave(property);
		} else {
			System.out.println(("Erro: " + EnumPropertyException.PropertyInvalid));
		}
	}

	private Property createProperty(Landlord landlord, String address, double rentalValue, PropertyType type,
			PropertyOccupation occupation) throws PropertyException {

		switch (type) {
		case RESIDENTIAL:
			System.out.print("\nNumeros de quartos: ");
			int numberOfRooms = scanner.nextInt();
			
			System.out.print("Área de Lazer: 1.Sim | 2.Nâo");
			System.out.print("\n| Opção: ");
			int theLeisureArea = scanner.nextInt();
			
			boolean area = false;
			if(theLeisureArea == 1) {
				area = true;
			}
			
			PropertyResidential residential = new PropertyResidential(addressFormat(address), rentalValue, type, occupation, numberOfRooms, area);
			return new Property(residential.getAddress(), residential.getRentalValue(), residential.getType(), residential.getOccupation());
		case COMMERCIAL:
			System.out.print("Tipos de Negócio: 0.Outro | 1.Alimentação | 2.HEALTH | 3.AUTOMOTIVESERVICES | 4.FASHION | 5.EDUCATION ");
			System.out.print("\n| Opção: ");
			int TheTypeOfBusiness = scanner.nextInt();
			
			System.out.print("\nNumeros de Salas: ");
			int numberOfRoom = scanner.nextInt();
			
			return new Property(addressFormat(address), rentalValue, type, occupation);
		default:
			throw new PropertyException("Erro: " + EnumPropertyException.PropertyInvalidType);
		}
	}

	// Formart
	private String addressFormat(String address) {
		String addressFormat = address.toUpperCase();
		return addressFormat;
	}

	// REMOVE
	public void removeProperty(int id) {
		propertyDAO.propertyDeleteByID(id);
	}

	// LIST
	public void listProperty() throws SQLException {
		if (propertyDAO.getProperty().isEmpty()) {
			System.out.println(("Erro: " + EnumPropertyException.PropertyNoRegistered));
		} else {
			for (Property p : propertyDAO.getProperty()) {
				System.out.print("\nID Imóvel: " + p.getId() + "\n");
				System.out.print(" | Cpf Proprietário: " + p.getLandlord().getCpf());
				System.out.print("\n | Endereço: " + p.getAddress());
				System.out.print("\n | Valor do Aluguel: " + p.getRentalValue());
				System.out.print("\n | Tipo: " + p.getType());
				System.out.print("\n | Ocupação: " + p.getOccupation() + "\n");
			}
		}
	}

	// LIST
	public void listPropertyByLandlordId(int id) throws SQLException {
		if (propertyDAO.getProperty().isEmpty()) {
			System.out.println(("Erro: " + EnumPropertyException.PropertyNoRegistered));
		} else {
			for (Property p : propertyDAO.getPropertyByLandlordId(id)) {
				System.out.print("\nID Imóvel: " + p.getId() + "\n");
				System.out.print(" | Cpf Proprietário: " + p.getLandlord().getCpf());
				System.out.print("\n | Endereço: " + p.getAddress());
				System.out.print("\n | Valor do Aluguel: " + p.getRentalValue());
				System.out.print("\n | Tipo: " + p.getType());
				System.out.print("\n | Ocupação: " + p.getOccupation() + "\n");
			}
		}
	}

	// CHANGE
	public void changeProperty(int id) throws PropertyException, SQLException {
		if (propertyDAO.getProperty().isEmpty()) {
			System.out.println(("Erro: " + EnumPropertyException.PropertyNoRegistered));
		} else {
			if (id <= 0 || id > propertyDAO.getProperty().size()) {
				throw new PropertyException("Erro: " + EnumPropertyException.PropertyInvalidIndex);
			}

			Property property = new Property();

			System.out.println(
					"\nQuais as novas informações do Imóvel deseja mudar? \n| 0.Nenhum | 1.Endereço | 2.Valor do Aluguel | 3.Tipo | 4.Ocupação |");
			System.out.print("\n| Opção: ");

			int option = scanner.nextInt();
			scanner.nextLine();
			switch (option) {
			case 1:
				System.out.print("Novo Endereço: ");
				String newAddress = scanner.nextLine();
				property.setAddress(addressFormat(newAddress));
				property.setId(id);
				propertyDAO.propertyUpdateAddress(property);
				break;
			case 2:
				System.out.print("Novo Valor do Aluguel: ");
				double newRentalValue = scanner.nextDouble();
				property.setRentalValue(newRentalValue);
				property.setId(id);
				propertyDAO.propertyUpdateRentalValue(property);
				break;
			case 3:
				System.out.print("Novo Tipo: \n1.Residencial | 2.Comercial |");
				System.out.print("\n| Opção: ");
				int newType = scanner.nextInt();

				if (newType == 1) {
					PropertyType propertyType = PropertyType.RESIDENTIAL;
					property.setType(propertyType);
					property.setId(id);
					propertyDAO.propertyUpdateType(property);
				} else if (newType == 2) {
					PropertyType propertyType = PropertyType.COMMERCIAL;
					property.setType(propertyType);
					property.setId(id);
					propertyDAO.propertyUpdateType(property);
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
					property.setOccupation(propertyOccupation);
					property.setId(id);
					propertyDAO.propertyUpdateOccupation(property);
				} else if (newOccupation == 2) {
					PropertyOccupation propertyOccupation = PropertyOccupation.OCCUPIED;
					property.setOccupation(propertyOccupation);
					property.setId(id);
					propertyDAO.propertyUpdateOccupation(property);
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

	public Property searchProperty(int id) throws SQLException, Exception {
		Property property = null;
		if (propertyDAO.getProperty().isEmpty()) {
			System.out.println("Erro: " + EnumLandlordException.LandlordNoRegistered);
		} else {
			property = propertyDAO.getPropertyById(id);
			if (property == null) {
				System.out.println("Erro: Imóvel não encontrado.");

			}
		}
		return property;
	}

}
