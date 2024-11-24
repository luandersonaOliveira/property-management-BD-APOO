package services;

import java.sql.SQLException;
import java.util.Scanner;

import containers.TelephoneRepository;
import entity.Person;
import entity.Telephone;
import exceptions.EnumLandlordException;
import exceptions.EnumTenantException;
import exceptions.LandlordException;
import exceptions.PropertyException;
import exceptions.TenantException;

public class TelephoneService {
	// ATTRIBUTES

	private static final Scanner scanner = new Scanner(System.in);
	private static final TelephoneRepository telephoneDAO = new TelephoneRepository();

	// ADD

	public void add(String firstTelephone, String secondTelephone, Person person)
			throws PropertyException, LandlordException, TenantException {

		if (person == null) {
			throw new PropertyException("Erro: " + EnumLandlordException.LandlordInvalid);
		} else if (firstTelephone.length() != 9 && firstTelephone.length() != 11) {
			throw new LandlordException("Erro: " + EnumLandlordException.LandlordInvalidTelephone);
		} else if (secondTelephone.length() != 9 && secondTelephone.length() != 11) {
			throw new LandlordException("Erro: " + EnumLandlordException.LandlordInvalidTelephone);
		}

		Telephone telephone = create(firstTelephone, secondTelephone, person);
		if (telephone != null) {
			telephone.setPerson(person);
			telephone.getPerson().setId(person.getId());
			telephoneDAO.save(telephone);
		}
	}
	
	// CREATE
	public Telephone create(String firstTelephone, String secondTelephone, Person person) throws TenantException {
		return new Telephone(telephoneFormat(firstTelephone), telephoneFormat(secondTelephone), person);
	}

	// FORMAT
	private String telephoneFormat(String telephone) throws TenantException {
		if (telephone == null || telephone.isEmpty()) {
			throw new TenantException("Erro: " + EnumTenantException.TenantInvalidTelephone);
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
			throw new TenantException("Erro: " + EnumTenantException.TenantInvalidTelephone);
		}
	}

	// REMOVE
	public void remove(int id) {
		telephoneDAO.deleteByID(id);
	}

	// LIST
	public void list() throws Exception {
		if (telephoneDAO.getTelephone().isEmpty()) {
			System.out.println(("Erro: " + EnumLandlordException.LandlordNoRegistered));
		} else {
			for (Telephone t : telephoneDAO.getTelephone()) {
				System.out.println("\n | Telefones: " + "Primario: " + telephoneFormat(t.getFirstTelephone())  + " | Segundario:"
						+ telephoneFormat(t.getSecondTelephone()));
			}
		}
	}

	// CHANGE
	public void change(int id) throws TenantException, SQLException {
		if (telephoneDAO.getTelephone().isEmpty()) {
			System.out.println(("Erro: " + EnumTenantException.TenantNoRegistered));
		} else {
			if (id <= 0 || id > telephoneDAO.getTelephone().size()) {
				throw new TenantException("Erro: " + EnumTenantException.TenantInvalidIndex);
			}

			Telephone telephone = new Telephone();

			System.out.println(
					"\nQuais as novas informações do Inquilino deseja mudar? \n| 0.Nenhum | 1.Nome | 2.Telefone | 3.Email | 4.Saldo |");
			System.out.print("\nOpção: ");

			System.out.print("Qual telefone deseja mudar? \n| 0.Nenhum | 1.Primeiro | 2.Segundo |");
			int optionT = scanner.nextInt();
			scanner.nextLine();
			switch (optionT) {
			case 1:
				System.out.print("Novo Telefone: ");
				String newTelephone1 = scanner.nextLine();
				telephone.setFirstTelephone(telephoneFormat(newTelephone1));
				telephoneDAO.updateFirstTelephone(telephone);
				break;
			case 2:
				System.out.print("Novo Telefone: ");
				String newTelephone2 = scanner.nextLine();
				telephone.setSecondTelephone(telephoneFormat(newTelephone2));
				telephoneDAO.updateSecondTelephone(telephone);
				break;
			default:
				System.out.println("\nTelefone não foi atualizado!");
				optionT = 0;
				break;

			}
		}
	}

	// SEARCH
	public Telephone searchTelephone(int id) throws SQLException, Exception {
		Telephone telephone = null;
		if (telephoneDAO.getTelephone().isEmpty()) {
			System.out.println("Erro: " + EnumLandlordException.LandlordNoRegistered);
		} else {
			telephone = telephoneDAO.getTelephoneById(id);
			if (telephone == null) {
				System.out.println("Erro: Telefone não encontrado.");
			}
		}
		return telephone;
	}
}
