package services;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Scanner;

import containers.PersonRepository;
import entity.Landlord;
import entity.Person;
import entity.Tenant;
import enums.PersonsPosition;
import exceptions.EnumLandlordException;
import exceptions.EnumTenantException;
import exceptions.LandlordException;
import exceptions.TenantException;

public class PersonService {
	// ATTRIBUTES

	private static final Scanner scanner = new Scanner(System.in);
	private static final PersonRepository personDAO = new PersonRepository();
	private static TelephoneService telephoneService = new TelephoneService();

	// ADD LANDLORD AND TENANT

	public void add(String name, String cpf, String email, double wallet, PersonsPosition positions)
			throws TenantException, LandlordException {
		switch (positions) {
		case LANDLORD:
			if (cpf.length() != 11) {
				throw new LandlordException("Erro: " + EnumLandlordException.LandlordInvalidCPF);
			}

			Person personL = new Person(name, cpf, email, wallet, positions);
			Landlord landlord = createLandlord(personL.getName(), personL.getCpf(), personL.getEmail(),
					personL.getWallet(), personL.getPositions());

			if (landlord != null) {
				personDAO.saveLandlord(landlord);
			} else {
				throw new LandlordException("Erro: " + EnumLandlordException.LandlordInvalid);
			}
			break;
		case TENANT:
			if (cpf.length() != 11) {
				throw new TenantException("Erro: " + EnumTenantException.TenantInvalidCPF);
			} else if (wallet < 0) {
				throw new TenantException("Erro: " + EnumTenantException.TenantInvalidBalance);
			}

			Person personT = new Person(name, cpf, email, wallet, positions);
			Tenant tenant = createTenant(personT.getName(), personT.getCpf(), personT.getEmail(), personT.getWallet(),
					personT.getPositions());

			if (tenant != null) {
				personDAO.saveTenant(tenant);
			} else {
				throw new TenantException("Erro: " + EnumTenantException.TenantInvalid);
			}
			break;
		default:
			break;
		}

	}

	// CREATE LANDLORD AND TENANT
	public Landlord createLandlord(String name, String cpf, String email, double wallet, PersonsPosition positions)
			throws LandlordException, TenantException {
		return new Landlord(nameFormart(name), cpfFormart(cpf), email, wallet, PersonsPosition.LANDLORD);
	}

	public Tenant createTenant(String name, String cpf, String email, double wallet, PersonsPosition positions)
			throws TenantException {
		return new Tenant(nameFormart(name), cpfFormart(cpf), email, wallet, PersonsPosition.TENANT);
	}

	// FORMART AND VALIDATE
	private String nameFormart(String name) {
		String nameFormart = name.toUpperCase();
		return nameFormart;
	}

	private String cpfFormart(String cpf) throws TenantException {
		validateCPF(cpf);
		if (cpf.length() == 11) {
			return String.format("%s.%s.%s-%s", cpf.substring(0, 3), cpf.substring(3, 6), cpf.substring(6, 9),
					cpf.substring(9, 11));
		} else {
			cpf = null;
			throw new TenantException("Erro: " + EnumTenantException.TenantInvalidCPF);
		}
	}

	private static String validateCPF(String cpf) {
		while (true) {
			String CPF = cpf.trim();
			if (CPF.length() != 11 || CPF.contains(" ") || CPF.isBlank() || CPF.isEmpty()) {
				System.out.print("\nErro: " + EnumTenantException.TenantInvalidCPF);
				return null;
			} else {
				for (int i = 0; i < CPF.length(); i++) {
					if (!Character.isDigit(CPF.charAt(i))) {
						return null;
					}
				}
				return CPF;
			}
		}
	}

	private String walletFormat(double wallet) {
		DecimalFormat df = new DecimalFormat("###,###.00");
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator(',');
		dfs.setGroupingSeparator('.');
		df.setDecimalFormatSymbols(dfs);
		return df.format(wallet);
	}

	// REMOVE LANDLORD AND TENANT
	public void removeLandlord(int id) {
		personDAO.deleteLandlordByID(id);
	}

	public void removeTenant(int id) {
		personDAO.deleteTenantByID(id);
	}

	// LIST LANDLORD AND TENANT
	public void listLandlord() throws Exception {
		if (personDAO.getLandlords().isEmpty()) {
			System.out.println(("Erro: " + EnumLandlordException.LandlordNoRegistered));
		} else {
			for (Landlord l : personDAO.getLandlords()) {
				System.out.print("\nID Proprietário: " + l.getId() + "\n");
				System.out.print(" | Nome: " + l.getName());
				System.out.print(" | CPF: " + l.getCpf());
				telephoneService.list();
				System.out.print(" | Email: " + l.getEmail() + " |\n");
			}
		}
	}

	public void listTenant() throws Exception {
		if (personDAO.getTenants().isEmpty()) {
			System.out.println(("Erro: " + EnumTenantException.TenantNoRegistered));
		} else {
			for (Tenant t : personDAO.getTenants()) {
				System.out.print("\nID Inquilino: " + t.getId() + "\n");
				System.out.print(" | Nome: " + t.getName());
				System.out.print(" | CPF: " + t.getCpf());
				System.out.print(" | Carteira: " + walletFormat(t.getWallet()));
				telephoneService.list();
				System.out.print(" | Email: " + t.getEmail() + " |\n");
			}
		}
	}

	// CHANGE LANDLORD AND TENANT
	public void changeLandlord(int id) throws LandlordException, SQLException, TenantException {
		if (personDAO.getLandlords().isEmpty()) {
			System.out.println(("Erro: " + EnumLandlordException.LandlordNoRegistered));
		} else {
			if (id <= 0 || id > personDAO.getLandlords().size()) {
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
				personDAO.updateLandlordName(landlord);
				break;
			case 2:
				telephoneService.change(id);
			case 3:
				System.out.print("Novo Email: ");
				String newEmail = scanner.nextLine();
				landlord.setEmail(newEmail);
				landlord.setId(id);
				personDAO.updateLandlordEmail(landlord);
				break;
			default:
				System.out.println("\nProprietário não foi atualizado!");
				option = 0;
				break;
			}
		}
	}

	public void changeTenant(int id) throws TenantException, SQLException {
		if (personDAO.getTenants().isEmpty()) {
			System.out.println(("Erro: " + EnumTenantException.TenantNoRegistered));
		} else {
			if (id <= 0 || id > personDAO.getTenants().size()) {
				throw new TenantException("Erro: " + EnumTenantException.TenantInvalidIndex);
			}

			Tenant tenant = new Tenant();

			System.out.println(
					"\nQuais as novas informações do Inquilino deseja mudar? \n| 0.Nenhum | 1.Nome | 2.Telefone | 3.Email | 4.Saldo |");
			System.out.print("\nOpção: ");

			int option = scanner.nextInt();
			scanner.nextLine();
			switch (option) {
			case 1:
				System.out.print("Novo Nome: ");
				String newName = scanner.nextLine();
				tenant.setName(nameFormart(newName));
				tenant.setId(id);
				personDAO.updateTenantName(tenant);
				break;
			case 2:
				telephoneService.change(id);
			case 3:
				System.out.print("Novo Email: ");
				String newEmail = scanner.nextLine();
				tenant.setEmail(newEmail);
				tenant.setId(id);
				personDAO.updateTenantEmail(tenant);
				break;
			case 4:
				System.out.print("Novo Saldo: ");
				double newWallet = scanner.nextDouble();
				tenant.setWallet(newWallet);
				tenant.setId(id);
				personDAO.updateTenantWallet(tenant);
				break;
			default:
				System.out.println("\nInquilino não foi atualizado!");
				option = 0;
				break;
			}
		}
	}

	// SEARCH LANDLORD AND TENANT
	public Landlord searchLandlord(int id) throws SQLException, Exception {
		Landlord landlord = null;
		if (personDAO.getLandlords().isEmpty()) {
			System.out.println("Erro: " + EnumLandlordException.LandlordNoRegistered);
		} else {
			landlord = personDAO.getLandlordById(id);
			if (landlord == null) {
				System.out.println("Erro: Proprietário não encontrado.");
			}
		}
		return landlord;
	}

	public Tenant searchTenant(int id) throws SQLException, Exception {
		Tenant tenant = null;
		if (personDAO.getTenants().isEmpty()) {
			System.out.println("Erro: " + EnumTenantException.TenantNoRegistered);
		} else {
			tenant = personDAO.getTenantById(id);
			if (tenant == null) {
				System.out.println("Erro: Inquilino não encontrado.");
			}
		}
		return tenant;
	}

}
