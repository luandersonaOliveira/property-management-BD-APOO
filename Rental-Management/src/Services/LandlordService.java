package services;
// Serviço Proprietário

import java.sql.SQLException;
import java.util.Scanner;

import Enum.EnumLandlordException;
import Enum.PersonPosition;
import containers.LandlordRepository;
import entity.Landlord;
import exceptions.LandlordException;

public class LandlordService {
	// ATTRIBUTES

	private static final Scanner scanner = new Scanner(System.in);
	private static final LandlordRepository landlordDAO = new LandlordRepository();

	// CREATE
	public void addLandlord(String name, String cpf, String telephone, String email) throws LandlordException {
		if (cpf.length() != 11) {
			throw new LandlordException("Erro: " + EnumLandlordException.LandlordInvalidCPF);
		} else if (telephone.length() != 9 && telephone.length() != 11) {
			throw new LandlordException("Erro: " + EnumLandlordException.LandlordInvalidTelephone);
		}

		Landlord landlord = createLandlord(name, cpf, telephone, email);
		if (landlord != null) {
			landlordDAO.save(landlord);

		} else {
			throw new LandlordException("Erro: " + EnumLandlordException.LandlordInvalid);
		}
	}

	public Landlord createLandlord(String name, String cpf, String telephone, String email) throws LandlordException {
		return new Landlord(nameFormart(name), cpfFormart(cpf), telephoneFormat(telephone), email, PersonPosition.Landlord);
	}

	private String nameFormart(String name) {
		String nameFormart = name.toUpperCase();
		return nameFormart;
	}

	private String cpfFormart(String cpf) throws LandlordException {
		if (cpf.length() == 11) {
			return String.format("%s.%s.%s-%s", cpf.substring(0, 3), cpf.substring(3, 6), cpf.substring(6, 9),
					cpf.substring(9, 11));
		} else {
			cpf = null;
			throw new LandlordException("Erro: " + EnumLandlordException.LandlordInvalidCPF);
		}
	}

	private String telephoneFormat(String telephone) throws LandlordException {
		if (telephone == null || telephone.isEmpty()) {
			throw new LandlordException("Erro: " + EnumLandlordException.LandlordInvalidTelephone);
		}

		telephone = telephone.replaceAll("[^0-9]", "");

		if (telephone.startsWith("55") && telephone.length() > 11) {
			telephone = telephone.substring(2);
		}

		switch (telephone.length()) {
		case 9:
			return String.format("%s-%s", telephone.substring(0, 5), telephone.substring(5, 9));
		case 10:
			return String.format("(%s) %s-%s", telephone.substring(0, 2), telephone.substring(2, 6),
					telephone.substring(6, 10));
		case 11:
			return String.format("(%s) %s-%s", telephone.substring(0, 2), telephone.substring(2, 7),
					telephone.substring(7, 11));
		case 12:
			return String.format("+%s (%s) %s-%s", telephone.substring(0, 2), telephone.substring(2, 4),
					telephone.substring(4, 8), telephone.substring(8, 12));
		case 13:
			return String.format("+%s (%s) %s-%s", telephone.substring(0, 2), telephone.substring(2, 4),
					telephone.substring(4, 9), telephone.substring(9, 13));
		default:
			throw new LandlordException("Erro: " + EnumLandlordException.LandlordInvalidTelephone);
		}
	}

	// REMOVE
	public void removeLandlord(int id) {
		landlordDAO.deleteByID(id);
	}

	// LIST
	public void listLandlord() throws SQLException {
		if (landlordDAO.getLandlords().isEmpty()) {
			System.out.println(("Erro: " + EnumLandlordException.LandlordNoRegistered));
		} else {
			for (Landlord l : landlordDAO.getLandlords()) {
				System.out.print("\nID Proprietário: " + l.getId() + "\n");
				System.out.print(" | Nome: " + l.getName());
				System.out.print(" | CPF: " + l.getCpf());
				System.out.print("\n | Telefone: " + l.getTelephone());
				System.out.print(" | Email: " + l.getEmail() + " |\n");
			}
		}
	}

	// CHANGE
	public void changeLandlord(int id) throws LandlordException, SQLException {
		if (landlordDAO.getLandlords().isEmpty()) {
			System.out.println(("Erro: " + EnumLandlordException.LandlordNoRegistered));
		} else {
			if (id <= 0 || id > landlordDAO.getLandlords().size()) {
				throw new LandlordException("Erro: " + EnumLandlordException.LandlordInvalidIndex);
			}

			Landlord landlord = new Landlord();

			System.out.println(
					"\nQuais as novas informações do Proprietário deseja mudar? \n| 0.Nenhum | 1.Nome | 2.Telefone | 3.Email |");
			System.out.print("\n| Opção: ");

			int option = scanner.nextInt();
			scanner.nextLine();
			switch (option) {
			case 1:
				System.out.print("Novo Nome: ");
				String newName = scanner.nextLine();
				landlord.setName(nameFormart(newName));
				landlord.setId(id);
				landlordDAO.updateName(landlord);
				break;
			case 2:
				System.out.print("Novo Telefone: ");
				String newTelephone = scanner.nextLine();
				landlord.setTelephone(telephoneFormat(newTelephone));
				landlord.setId(id);
				landlordDAO.updateTelephone(landlord);
				break;
			case 3:
				System.out.print("Novo Email: ");
				String newEmail = scanner.nextLine();
				landlord.setEmail(newEmail);
				landlord.setId(id);
				landlordDAO.updateEmail(landlord);
				break;
			default:
				System.out.println("\nProprietário não foi atualizado!");
				option = 0;
				break;
			}
		}
	}

	public Landlord searchLandlord(int id) throws SQLException, Exception {
	    Landlord landlord = null;
	    if (landlordDAO.getLandlords().isEmpty()) {
	        System.out.println("Erro: " + EnumLandlordException.LandlordNoRegistered);
	    } else {
	        landlord = landlordDAO.getLandlordById(id);
	        if (landlord == null) {
	        	System.out.println("Erro: Proprietário não encontrado.");  
	        }
	    }
	    return landlord;
	}

}
