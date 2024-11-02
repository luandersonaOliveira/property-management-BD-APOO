package views;
// MAIN

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import Enum.EnumPropertyException;
import Enum.PropertyOccupation;
import Enum.PropertyType;
import containers.LandlordRepository;
import containers.LeaseRepository;
import containers.PropertyRepository;
import entity.Landlord;
import entity.Lease;
import entity.Property;
import entity.Tenant;
import exceptions.LandlordException;
import exceptions.LeaseException;
import exceptions.PropertyException;
import exceptions.TenantException;
import services.LandlordService;
import services.LeaseService;
import services.PropertyService;
import services.TenantService;

public class Main {
	private static final Scanner scanner = new Scanner(System.in);

	// NEWS RESPOSITORYS
	private static PropertyRepository propertyRepository = new PropertyRepository();
	private static LeaseRepository leaseRepository = new LeaseRepository(new ArrayList<>());

	// NEWS SERVICES
	private static TenantService tenantService = new TenantService();
	private static LandlordService landlordService = new LandlordService();
	private static PropertyService propertyService = new PropertyService(propertyRepository);
	private static LeaseService leaseService = new LeaseService(leaseRepository);

	public static void main(String[] args)
			throws TenantException, LandlordException, PropertyException, LeaseException, ParseException, SQLException {

		// MENU OPTIONS
		boolean exit = false;
		do {
			menuMain();
			int option = scanner.nextInt();
			scanner.nextLine();
			switch (option) {
			case 1:
				menuTenant();
				break;
			case 2:
				menuLandlord();
				break;
			case 3:
				menuProperty();
				break;
			case 4:
				menuLease();
				break;
			case 5:
				removeSomething();
				break;
			case 0:
				System.out.println("\n-----------------------");
				System.out.println("| Programa Finalizado |");
				System.out.println("-----------------------");
				exit = true;
				break;
			}
		} while (!exit);
	}

	// METHODS PERSONALIZED

	// MENU
	private static void menuMain() {
		System.out.println("\n-------------------------------");
		System.out.println("| Menu Principal: ");
		System.out.println("-------------------------------");
		System.out.println("| 1.Para Acessar Inquilinos.");
		System.out.println("| 2.Para Acessar Proprietários.");
		System.out.println("| 3.Para Acessar Imoveis.");
		System.out.println("| 4.Para Acessar Contratos.");
		System.out.println("| 5.Para Remover Algo.");
		System.out.println("-------------------------------");
		System.out.println("| 0.Para Sair do Menu.");
		System.out.println("-------------------------------");
		System.out.print("\n| Opção: ");
	}

	private static void menuTenant() throws TenantException, SQLException {
		boolean exit = false;
		do {
			System.out.println("\n------------------------------");
			System.out.println("| Menu Inquilino: ");
			System.out.println("------------------------------");
			System.out.println("| 0.Nenhum.");
			System.out.println("| 1.Para Cadastrar Inquilinos.");
			System.out.println("| 2.Para Checar Inquilinos.");
			System.out.println("| 3.Para Editar Inquilinos.");
			System.out.println("------------------------------");
			System.out.print("\n| Opção: ");
			int option = scanner.nextInt();
			scanner.nextLine();
			switch (option) {
			case 1:
				createTenants();
				break;
			case 2:
				tenantService.listTenant();
				break;
			case 3:
				changeTenants();
				break;
			case 0:
				exit = true;
				break;
			}
		} while (!exit);
	}

	private static void menuLandlord() throws LandlordException, SQLException {
		boolean exit = false;
		do {
			System.out.println("\n---------------------------------");
			System.out.println("| Menu Proprietário: ");
			System.out.println("---------------------------------");
			System.out.println("| 0.Nenhum.");
			System.out.println("| 1.Para Cadastrar Proprietários.");
			System.out.println("| 2.Para Checar Proprietários.");
			System.out.println("| 3.Para Editar Proprietários.");
			System.out.println("---------------------------------");
			System.out.print("\n| Opção: ");
			int option = scanner.nextInt();
			scanner.nextLine();
			switch (option) {
			case 1:
				createLandlord();
				break;
			case 2:
				landlordService.listLandlord();
				break;
			case 3:
				changeLandlord();
				break;
			case 0:
				exit = true;
				break;
			}
		} while (!exit);
	}

	private static void menuProperty() throws PropertyException, LandlordException {
		boolean exit = false;
		do {
			System.out.println("\n---------------------------------------------");
			System.out.println("| Menu Imovel: ");
			System.out.println("---------------------------------------------");
			System.out.println("| 0.Nenhum.");
			System.out.println("| 1.Para Cadastrar Imoveis aos Proprietários.");
			System.out.println("| 2.Para Checar Imoveis.");
			System.out.println("| 3.Para Editar Imoveis.");
			System.out.println("| 4.Para adicionar lista de imoveis pronta.");
			System.out.println("---------------------------------------------");
			System.out.print("\n| Opção: ");
			int option = scanner.nextInt();
			scanner.nextLine();
			switch (option) {
			case 1:
				createProperty();
				break;
			case 2:
				propertyService.listProperty();
				break;
			case 3:
				changeProperty();
				break;
			case 4:
				listProperties();
				break;
			case 0:
				exit = true;
				break;
			}
		} while (!exit);
	}

	private static void menuLease() throws LeaseException, ParseException {
		boolean exit = false;
		do {
			System.out.println("\n--------------------------");
			System.out.println("| Menu Contrato: ");
			System.out.println("--------------------------");
			System.out.println("| 0.Nenhum.");
			System.out.println("| 1.Para Criar Contratos.");
			System.out.println("| 2.Para Checar Contratos.");
			System.out.println("| 3.Para Editar Contratos.");
			System.out.println("--------------------------");
			System.out.print("\n| Opção: ");
			int option = scanner.nextInt();
			scanner.nextLine();
			switch (option) {
			case 1:
				createLease();
				break;
			case 2:
				leaseService.listLease();
				break;
			case 3:
				changeLease();
				break;
			case 0:
				exit = true;
				break;
			}
		} while (!exit);
	}

	// CREATE TENANTS
	private static void createTenants() throws TenantException {
		try {
			System.out.print("\nNome: ");
			String name = scanner.nextLine();
			System.out.print("CPF: ");
			String cpf = scanner.nextLine();
			System.out.print("Telefone: ");
			String telephone = scanner.nextLine();
			System.out.print("Email: ");
			String email = scanner.nextLine();
			System.out.print("Saldo: ");
			double balance = scanner.nextDouble();

			Tenant tenant = new Tenant(name, cpf, telephone, email, balance);
			tenantService.addTenant(tenant.getName(), tenant.getCpf(), tenant.getTelephone(), tenant.getEmail(),
					tenant.getBalance());
		} catch (TenantException e) {
			System.out.println("\n" + e.getMessage());
		}
	}

	// CHANGE TENANTS
	private static void changeTenants() throws TenantException, SQLException {
		System.out.print("\nInsira o ID do Inquilino à editar: ");
		int id = scanner.nextInt();
		tenantService.changeTenant(id);
	}

	// CREATE PROPERTY
	private static void createProperty() {
		try {
			System.out.print("\nInsira o ID do Proprietario: ");
			int idLandlord = scanner.nextInt();
			Landlord landlord = landlordRepository.searchLandlord(idLandlord);
			scanner.nextLine();
			if (landlord != null) {
				System.out.print("\nEndereço: ");
				String address = scanner.nextLine();
				System.out.print("Valor do Aluguel: ");
				double rentalValue = scanner.nextDouble();
				System.out.print("Tipo: \n1.Residencial | 2.Comercial |");
				System.out.print("\nOpção: ");
				int type = scanner.nextInt();
				System.out.print("Ocupação: \n1.Desocupado | 2.Ocupado |");
				System.out.print("\nOpção: ");
				int occupation = scanner.nextInt();

				PropertyType propertyType = null;
				switch (type) {
				case 1:
					propertyType = PropertyType.RESIDENTIAL;
					break;
				case 2:
					propertyType = PropertyType.COMMERCIAL;
					break;
				default:
					throw new PropertyException("Erro: " + EnumPropertyException.PropertyInvalidType);
				}

				PropertyOccupation propertyOccupation = null;
				switch (occupation) {
				case 1:
					propertyOccupation = PropertyOccupation.UNOCCUPIED;
					break;
				case 2:
					propertyOccupation = PropertyOccupation.OCCUPIED;
					break;
				default:
					throw new PropertyException("Erro: " + EnumPropertyException.PropertyInvalidOccupation);
				}

				Property property = new Property(address, rentalValue, propertyType, propertyOccupation);
				propertyService.addProperty(landlord, property.getAddress(), property.getRentalValue(),
						property.getType(), property.getOccupation());
				leaseService.assignPropertyToLandlord(landlord, property);
			} else {
				System.out.println("\nErro: Proprietário não foi cadastrado!");
			}

		} catch (PropertyException e) {
			System.out.println("\n" + e.getMessage());
		}
	}

	// CHANGE PROPERTY
	private static void changeProperty() throws PropertyException {
		System.out.print("\nInsira o ID do Imovel à editar: ");
		int id = scanner.nextInt();
		propertyService.changeProperty(id);
	}

	// CREATE LANDLORD
	private static void createLandlord() {
		try {
			System.out.print("\nNome: ");
			String name = scanner.nextLine();
			System.out.print("CPF: ");
			String cpf = scanner.nextLine();
			System.out.print("Telefone: ");
			String telephone = scanner.nextLine();
			System.out.print("Email: ");
			String email = scanner.nextLine();

			Landlord landlord = new Landlord(name, cpf, telephone, email);
			landlordService.addLandlord(landlord.getName(), landlord.getCpf(), landlord.getTelephone(),
					landlord.getEmail());
		} catch (LandlordException e) {
			System.out.println("\n" + e.getMessage());
		}
	}

	// CHANGE LANDLORD
	private static void changeLandlord() throws LandlordException, SQLException {
		System.out.print("\nInsira o ID do Proprietário à editar: ");
		int id = scanner.nextInt();
		landlordService.changeLandlord(id);
	}

	// CREATE LEASE
	private static void createLease() throws LeaseException, ParseException {
		try {
			System.out.print("\nInsira o ID do Inquilino: ");
			int idTenant = scanner.nextInt();
			Tenant tenant = tenantRepository.searchTenant(idTenant);
			System.out.print("\nInsira o ID do Imovel: ");
			int idProperty = scanner.nextInt();
			Property property = propertyRepository.searchProperty(idProperty);
			scanner.nextLine();
			if (tenant != null && property != null) {
				System.out.print("\nData de Inicio (YYYY/MM/DD): ");
				String startDate = scanner.nextLine();
				System.out.print("\nData de Fim (YYYY/MM/DD): ");
				String endDate = scanner.nextLine();

				Lease lease = new Lease(startDate, endDate, property.getLandlord(), property, tenant);
				leaseService.addLease(lease.getStartDate(), lease.getEndDate(), lease.getLandlord(),
						lease.getProperty(), lease.getTenant());

			} else {
				System.out.println("\nErro: Proprietário não foi cadastrado!");
			}

		} catch (LeaseException e) {
			System.out.println("\n" + e.getMessage());
		}

	}

	// CHANGE LEASE
	private static void changeLease() throws LeaseException, ParseException {
		System.out.print("\nInsira o ID do Contrato à editar: ");
		int id = scanner.nextInt();
		leaseService.changeLease(id);
	}

	// REMOVE ALL
	public static void removeSomething() {
		boolean exit = false;
		do {
			System.out.println("\n-----------------------------");
			System.out.println("| Deseja remover: ");
			System.out.println("-----------------------------");
			System.out.println("| 0.Nenhum.");
			System.out.println("| 1.Inquilinos.");
			System.out.println("| 2.Proprietários.");
			System.out.println("| 3.Imovel do Proprietário.");
			System.out.println("-----------------------------");
			System.out.print("\n| Opção: ");
			int option = scanner.nextInt();
			scanner.nextLine();
			switch (option) {
			case 1:
				removeTenants();
				break;
			case 2:
				removeLandlord();
				break;
			case 3:
				removePropertyLandlord();
				break;
			case 4:
				deleteLease();
				break;
			case 0:
				exit = true;
				break;
			}
		} while (!exit);
	}

	private static void removeTenants() {
		System.out.print("\nInsira o ID do Inquilino para remover: ");
		int id = scanner.nextInt();
		tenantService.removeTenant(id);
	}

	private static void removePropertyLandlord() {
		System.out.print("\nInsira o ID do Imovel para remover: ");
		int id = scanner.nextInt();
		propertyService.removeProperty(id);
	}

	private static void removeLandlord() {
		System.out.print("\nInsira o ID do Proprietário para remover: ");
		int id = scanner.nextInt();
		landlordService.removeLandlord(id);
	}

	private static void deleteLease() {
		leaseService.removeLease();
	}

	// LIST OF READY PROPERTIES
	private static void listProperties() throws PropertyException, LandlordException {

		// LANDLORD ADD
		Landlord l1 = new Landlord("Liang", "74678506039", "86986012358", "Liang@gmail.com.br");
		Landlord l2 = new Landlord("Ravi", "89867001826", "62989335737", "Ravi@gmail.com.br");
		Landlord l3 = new Landlord("Elli", "21422187926", "63998845787", "Elli@gmail.com.br");
		Landlord l4 = new Landlord("Norabel", "38766718686", "92999042606", "Norabel@gmail.com.br");
		Landlord l5 = new Landlord("YuYan", "94614156487", "62991046653", "YuYan@gmail.com.br");

		// LANDLORD SERVICE
		landlordService.addLandlord(l1.getName(), l1.getCpf(), l1.getTelephone(), l1.getEmail());
		landlordService.addLandlord(l2.getName(), l2.getCpf(), l2.getTelephone(), l2.getEmail());
		landlordService.addLandlord(l3.getName(), l3.getCpf(), l3.getTelephone(), l3.getEmail());
		landlordService.addLandlord(l4.getName(), l4.getCpf(), l4.getTelephone(), l4.getEmail());
		landlordService.addLandlord(l5.getName(), l5.getCpf(), l5.getTelephone(), l5.getEmail());

		// PROPERTY ADD
		Property p1 = new Property("Rua Gonçalo de Carvalho (RS)", 1000, PropertyType.RESIDENTIAL,
				PropertyOccupation.OCCUPIED);
		Property p2 = new Property("Rua do Mucugê (BA)", 1200, PropertyType.COMMERCIAL, PropertyOccupation.UNOCCUPIED);
		Property p3 = new Property("Rua das Pedras (RJ)", 1600, PropertyType.RESIDENTIAL,
				PropertyOccupation.UNOCCUPIED);
		Property p4 = new Property("Rua da Aurora (PE)", 1800, PropertyType.COMMERCIAL, PropertyOccupation.UNOCCUPIED);
		Property p5 = new Property("Rua Bento Gonçalves (RS)", 2000, PropertyType.RESIDENTIAL,
				PropertyOccupation.UNOCCUPIED);

		// PROPERTY SERVICE
		propertyService.addProperty(l1, p1.getAddress(), p1.getRentalValue(), p1.getType(), p1.getOccupation());
		propertyService.addProperty(l2, p2.getAddress(), p2.getRentalValue(), p2.getType(), p2.getOccupation());
		propertyService.addProperty(l3, p3.getAddress(), p3.getRentalValue(), p3.getType(), p3.getOccupation());
		propertyService.addProperty(l4, p4.getAddress(), p4.getRentalValue(), p4.getType(), p4.getOccupation());
		propertyService.addProperty(l5, p5.getAddress(), p5.getRentalValue(), p5.getType(), p5.getOccupation());

		// ASSIGN PROPERTY TO LANDLORD
		leaseService.assignPropertyToLandlord(l1, p1);
		leaseService.assignPropertyToLandlord(l2, p2);
		leaseService.assignPropertyToLandlord(l3, p3);
		leaseService.assignPropertyToLandlord(l4, p4);
		leaseService.assignPropertyToLandlord(l5, p5);
	}

}
