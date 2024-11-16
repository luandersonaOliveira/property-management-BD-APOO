package services;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Scanner;

import containers.LandlordRepository;
import containers.TenantRepository;
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
	private static final TenantRepository tenantDAO = new TenantRepository();
	private static final LandlordRepository landlordDAO = new LandlordRepository();

	// ADD LANDLORD AND TENANT

	public void add(String name, String cpf, String telephone, String email, double wallet, PersonsPosition positions)
			throws TenantException, LandlordException {
		switch (positions) {
		case LANDLORD:
			if (cpf.length() != 11) {
				throw new LandlordException("Erro: " + EnumLandlordException.LandlordInvalidCPF);
			} else if (telephone.length() != 9 && telephone.length() != 11) {
				throw new LandlordException("Erro: " + EnumLandlordException.LandlordInvalidTelephone);
			}

			Person personL = new Person(name, cpf, telephone, email, wallet, positions);
			Landlord landlord = createLandlord(personL.getName(), personL.getCpf(), personL.getTelephone(),
					personL.getEmail(), personL.getWallet(), personL.getPositions());

			if (landlord != null) {
				landlordDAO.save(landlord);
			} else {
				throw new LandlordException("Erro: " + EnumLandlordException.LandlordInvalid);
			}
			break;
		case TENANT:
			if (cpf.length() != 11) {
				throw new TenantException("Erro: " + EnumTenantException.TenantInvalidCPF);
			} else if (telephone.length() != 9 && telephone.length() != 11) {
				throw new TenantException("Erro: " + EnumTenantException.TenantInvalidTelephone);
			} else if (wallet < 0) {
				throw new TenantException("Erro: " + EnumTenantException.TenantInvalidBalance);
			}

			Person personT = new Person(name, cpf, telephone, email, wallet, positions);
			Tenant tenant = createTenant(personT.getName(), personT.getCpf(), personT.getTelephone(),
					personT.getEmail(), personT.getWallet(), personT.getPositions());

			if (tenant != null) {
				tenantDAO.save(tenant);
			} else {
				throw new TenantException("Erro: " + EnumTenantException.TenantInvalid);
			}
			break;
		default:
			break;
		}

	}

	// CREATE LANDLORD AND TENANT
	public Landlord createLandlord(String name, String cpf, String telephone, String email, double wallet,
			PersonsPosition positions) throws LandlordException, TenantException {
		return new Landlord(nameFormart(name), cpfFormart(cpf), telephoneFormat(telephone), email, wallet,
				PersonsPosition.LANDLORD);
	}

	public Tenant createTenant(String name, String cpf, String telephone, String email, double wallet,
			PersonsPosition positions) throws TenantException {
		return new Tenant(nameFormart(name), cpfFormart(cpf), telephoneFormat(telephone), email, wallet,
				positions);
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

	private String walletFormat(double wallet) {
		DecimalFormat df = new DecimalFormat("###,###.00");
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator(',');
		dfs.setGroupingSeparator('.');
		df.setDecimalFormatSymbols(dfs);
		return df.format(wallet);
	}

	// REMOVE LANDLORD AND TENANT
	public void removeTenant(int id) {
		tenantDAO.deleteByID(id);
	}

	public void removeLandlord(int id) {
		landlordDAO.deleteByID(id);
	}

	// LIST LANDLORD AND TENANT
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

	public void listTenant() throws SQLException {
		if (tenantDAO.getTenants().isEmpty()) {
			System.out.println(("Erro: " + EnumTenantException.TenantNoRegistered));
		} else {
			for (Tenant t : tenantDAO.getTenants()) {
				System.out.print("\nID Inquilino: " + t.getId() + "\n");
				System.out.print(" | Nome: " + t.getName());
				System.out.print(" | CPF: " + t.getCpf());
				System.out.print(" | Carteira: " + walletFormat(t.getWallet()));
				System.out.print("\n | Telefone: " + t.getTelephone());
				System.out.print(" | Email: " + t.getEmail() + " |\n");
			}
		}
	}

	// CHANGE LANDLORD AND TENANT
	public void changeLandlord(int id) throws LandlordException, SQLException, TenantException {
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

	public void changeTenant(int id) throws TenantException, SQLException {
		if (tenantDAO.getTenants().isEmpty()) {
			System.out.println(("Erro: " + EnumTenantException.TenantNoRegistered));
		} else {
			if (id <= 0 || id > tenantDAO.getTenants().size()) {
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
				tenantDAO.updateName(tenant);
				break;
			case 2:
				System.out.print("Novo Telefone: ");
				String newTelephone = scanner.nextLine();
				tenant.setTelephone(telephoneFormat(newTelephone));
				tenant.setId(id);
				tenantDAO.updateTelephone(tenant);
				break;
			case 3:
				System.out.print("Novo Email: ");
				String newEmail = scanner.nextLine();
				tenant.setEmail(newEmail);
				tenant.setId(id);
				tenantDAO.updateEmail(tenant);
				break;
			case 4:
				System.out.print("Novo Saldo: ");
				double newWallet = scanner.nextDouble();
				tenant.setWallet(newWallet);
				tenant.setId(id);
				tenantDAO.updateBalance(tenant);
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

	public Tenant searchTenant(int id) throws SQLException, Exception {
		Tenant tenant = null;
		if (tenantDAO.getTenants().isEmpty()) {
			System.out.println("Erro: " + EnumTenantException.TenantNoRegistered);
		} else {
			tenant = tenantDAO.getTenantById(id);
			if (tenant == null) {
				System.out.println("Erro: Inquilino não encontrado.");
			}
		}
		return tenant;
	}

}
