package services;
// Serviço Inquilino

import java.sql.SQLException;
import java.util.Scanner;

import Enum.EnumPropertyException;
import Enum.EnumTenantException;
import dao.PropertyDAO;
import entity.Tenant;
import exceptions.TenantException;

public class TenantService {
	// ATTRIBUTES

	private static final Scanner scanner = new Scanner(System.in);
	private static final PropertyDAO propertyDAO = new PropertyDAO();

	// CREATE
	public void addTenant(String name, String cpf, String telephone, String email, double balance)
			throws TenantException {
		if (cpf.length() != 11) {
			throw new TenantException("Erro: " + EnumTenantException.TenantInvalidCPF);
		} else if (telephone.length() != 9 && telephone.length() != 11) {
			throw new TenantException("Erro: " + EnumTenantException.TenantInvalidTelephone);
		} else if (balance < 0) {
			throw new TenantException("Erro: " + EnumTenantException.TenantInvalidBalance);
		}

		Tenant tenant = createTenant(name, cpf, telephone, email, balance);
		if (tenant != null) {
			propertyDAO.tenantSave(tenant);
		} else {
			throw new TenantException("Erro: " + EnumTenantException.TenantInvalid);
		}
	}

	private Tenant createTenant(String name, String cpf, String telephone, String email, double balance)
			throws TenantException {
		return new Tenant(nameFormart(name), cpfFormart(cpf), telephoneFormat(telephone), email, balance);
	}

	// FORMART
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

	public static String validateCPF(String cpf) {
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

	// REMOVE
	public void removeTenant(int id) {
		propertyDAO.tenantDeleteByID(id);
	}

	// LIST
	public void listTenant() throws SQLException {
		if (propertyDAO.getTenants().isEmpty()) {
			System.out.println(("Erro: " + EnumTenantException.TenantNoRegistered));
		} else {
			for (Tenant t : propertyDAO.getTenants()) {
				System.out.println("\n-------------------------------------------------------------------------------");
				System.out.print("ID Inquilino: " + t.getId() + "\n");
				System.out.print(" | Nome: " + t.getName());
				System.out.print(" | CPF: " + t.getCpf());
				System.out.print("\n | Telefone: " + t.getTelephone());
				System.out.print(" | Email: " + t.getEmail());
				System.out.print(" | Saldo: " + t.getBalance() + " |");
				System.out.println("\n-------------------------------------------------------------------------------");
			}
		}
	}

	// CHANGE
	public void changeTenant(int id) throws TenantException, SQLException {
		if (propertyDAO.getTenants().isEmpty()) {
			System.out.println(("Erro: " + EnumTenantException.TenantNoRegistered));
		} else {
			if (id <= 0 || id > propertyDAO.getTenants().size()) {
				throw new TenantException("Erro: " + EnumPropertyException.PropertyInvalidIndex);
			}
			
			Tenant tenant = new Tenant();

			System.out.println(
					"\nQuais as novas informações do Inquilino deseja mudar? \n0.Nenhum | 1.Nome | 2.Telefone | 3.Email | 4.Saldo |");
			System.out.print("\nOpção: ");

			int option = scanner.nextInt();
			scanner.nextLine();
			switch (option) {
			case 1:
				System.out.print("Novo Nome: ");
				String newName = scanner.nextLine();
				tenant.setName(nameFormart(newName));
				tenant.setId(id);
				propertyDAO.TenantUpdateName(tenant);
				break;
			case 2:
				System.out.print("Novo Telefone: ");
				String newTelephone = scanner.nextLine();
				tenant.setTelephone(telephoneFormat(newTelephone));
				tenant.setId(id);
				propertyDAO.TenantUpdateTelephone(tenant);
				break;
			case 3:
				System.out.print("Novo Email: ");
				String newEmail = scanner.nextLine();
				tenant.setEmail(newEmail);
				tenant.setId(id);
				propertyDAO.TenantUpdateEmail(tenant);
				break;
			case 4:
				System.out.print("Novo Saldo: ");
				double newBalance = scanner.nextDouble();
				tenant.setBalance(newBalance);
				tenant.setId(id);
				propertyDAO.TenantUpdateBalance(tenant);
				break;
			default:
				option = 0;
				System.out.println("\nInquilino não foi atualizado!");
				break;
			}
		}
	}

}
