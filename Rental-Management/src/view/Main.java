package view;
// MAIN

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import Enum.EnumPropertyException;
import Enum.PropertyOccupation;
import Enum.PropertyType;
import containers.LandlordRepository;
import containers.LeaseRepository;
import containers.PropertyRepository;
import containers.TenantRepository;
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
	private static TenantRepository tenantRepository = new TenantRepository();
	private static LandlordRepository landlordRepository = new LandlordRepository();
	private static LeaseRepository leaseRepository = new LeaseRepository(new ArrayList<>());

	// NEWS SERVICES
	private static PropertyService propertyService = new PropertyService(propertyRepository);
	private static TenantService tenantService = new TenantService(tenantRepository);
	private static LandlordService landlordService = new LandlordService(landlordRepository);
	private static LeaseService leaseService = new LeaseService(leaseRepository);

	public static void main(String[] args)
			throws TenantException, LandlordException, PropertyException, LeaseException, ParseException {

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
				exit = true;
				break;
			}
		} while (!exit);
	}

	// METHODS PERSONALIZED

	// MENU
	private static void menuMain() {
		System.out.println("--------------------------------");
		System.out.println("Menu Principal: ");
		System.out.println("| 1.Para Acessar Inquilinos.");
		System.out.println("| 2.Para Acessar Proprietários.");
		System.out.println("| 3.Para Acessar Imoveis.");
		System.out.println("| 4.Para Acessar Contratos.");
		System.out.println("| 5.Para Remover Algo.");
		System.out.println("--------------------------------");
		System.out.println("| 0.Para Sair do Menu.");
		System.out.println("--------------------------------");
		System.out.print("\nOpção: ");
	}

	private static void menuTenant() throws TenantException {
		boolean exit = false;
		do {
			System.out.println("\nMenu Inquilino: ");
			System.out.println("| 0.Nenhum.");
			System.out.println("| 1.Para Cadastrar Inquilinos.");
			System.out.println("| 2.Para Checar Inquilinos.");
			System.out.println("| 3.Para Editar Inquilinos.");
			System.out.print("\nOpção: ");
			int option = scanner.nextInt();
			scanner.nextLine();
			switch (option) {
			case 1:
				createTenants();
				tenantService.listTenant();
				break;
			case 2:
				tenantService.listTenant();
				break;
			case 3:
				changeTenants();
				break;
			case 4:
				searchTenant();
				break;
			case 0:
				exit = true;
				break;
			}
		} while (!exit);
	}

	private static void menuLandlord() throws LandlordException {
		boolean exit = false;
		do {
			System.out.println("\nMenu Proprietário: ");
			System.out.println("| 0.Nenhum.");
			System.out.println("| 1.Para Cadastrar Proprietários.");
			System.out.println("| 2.Para Checar Proprietários.");
			System.out.println("| 3.Para Editar Proprietários.");
			System.out.print("\nOpção: ");
			int option = scanner.nextInt();
			scanner.nextLine();
			switch (option) {
			case 1:
				createLandlord();
				landlordService.listLandlord();
				break;
			case 2:
				landlordService.listLandlord();
				break;
			case 3:
				changeLandlord();
				break;
			case 4:
				searchLandlord();
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
			System.out.println("\nMenu Imovel: ");
			System.out.println("| 0.Nenhum.");
			System.out.println("| 1.Para Cadastrar Imoveis aos Proprietários.");
			System.out.println("| 2.Para Checar Imoveis.");
			System.out.println("| 3.Para Editar Imoveis.");
			System.out.println("| 4.Para adicionar lista de imoveis pronta.");
			System.out.print("\nOpção: ");
			int option = scanner.nextInt();
			scanner.nextLine();
			switch (option) {
			case 1:
				createProperty();
				propertyService.listProperty();
				break;
			case 2:
				propertyService.listProperty();
				break;
			case 3:
				changeProperty();
				break;
			case 4:
				listProperties();
				propertyService.listProperty();
				break;
			case 5:
				searchProperty();
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
			System.out.println("\nMenu Contrato: ");
			System.out.println("| 0.Nenhum.");
			System.out.println("| 1.Para Criar Contratos.");
			System.out.println("| 2.Para Checar Contratos.");
			System.out.println("| 3.Para Editar Contratos.");
			System.out.print("\nOpção: ");
			int option = scanner.nextInt();
			scanner.nextLine();
			switch (option) {
			case 1:
				createLease();
				leaseService.listLease();
				break;
			case 2:
				leaseService.listLease();
				break;
			case 3:
				changeLease();
				break;
			case 4:
				searchLease();
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
	private static void changeTenants() throws TenantException {
		System.out.print("\nInsira o índice do Inquilino à editar: ");
		int id = scanner.nextInt();
		tenantService.changeTenant(id);
	}

	// CREATE PROPERTY
	private static void createProperty() {
		try {
			System.out.print("\nInsira o índice do Proprietario: ");
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
		System.out.print("\nInsira o índice do Imovel à editar: ");
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
	private static void changeLandlord() throws LandlordException {
		System.out.print("\nInsira o índice do Proprietário à editar: ");
		int id = scanner.nextInt();
		landlordService.changeLandlord(id);
	}

	// CREATE LEASE
	private static void createLease() throws LeaseException, ParseException {
		try {
			System.out.print("\nInsira o índice do Inquilino: ");
			int idTenant = scanner.nextInt();
			Tenant tenant = tenantRepository.searchTenant(idTenant);
			System.out.print("\nInsira o índice do Imovel: ");
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
		System.out.print("\nInsira o índice do Contrato à editar: ");
		int id = scanner.nextInt();
		leaseService.changeLease(id);
	}

	// REMOVE ALL
	public static void removeSomething() {
		boolean exit = false;
		do {
			System.out.println("\n DESEJA REMOVER QUAL OPÇÃO: ");
			System.out.println("(O Contrato será excluido junto com a opção escolhida!)");
			System.out.println("| 0.Nenhum.");
			System.out.println("| 1.Inquilinos.");
			System.out.println("| 2.Proprietários.");
			System.out.println("| 3.Imovel do Proprietário.");
			System.out.print("\nOpção: ");
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
		System.out.print("\nInsira o índice do Inquilino para remover: ");
		int id = scanner.nextInt();
		tenantService.removeTenant(id);
	}

	private static void removePropertyLandlord() {
		System.out.print("\nInsira o índice do Imovel para remover: ");
		int id = scanner.nextInt();
		propertyService.removeProperty(id);
	}

	private static void removeLandlord() {
		System.out.print("\nInsira o índice do Proprietário para remover: ");
		int id = scanner.nextInt();
		landlordService.removeLandlord(id);
	}

	private static void deleteLease() {
		leaseService.removeLease();
	}

	// SEARCH
	private static void searchTenant() {
		System.out.print("\nInsira o índice do Inquilino: ");
		int idTenant = scanner.nextInt();
		tenantService.searchTenant(idTenant);
	}

	private static void searchProperty() {
		System.out.print("\nInsira o índice do Imovel: ");
		int idProperty = scanner.nextInt();
		propertyService.searchProperty(idProperty);
	}

	private static void searchLandlord() {
		System.out.print("\nInsira o índice do Proprietario: ");
		int idLandlord = scanner.nextInt();
		landlordService.searchLandlord(idLandlord);
	}

	private static void searchLease() {
		System.out.print("\nInsira o índice do Contrato: ");
		int idLease = scanner.nextInt();
		leaseService.searchLease(idLease);
	}

	// LIST OF READY PROPERTIES
	private static void listProperties() throws PropertyException, LandlordException {

		// LANDLORD ADD
		Landlord landlord01 = new Landlord("Liang", "74678506039", "86986012358", "Liang@gmail.com.br");
		Landlord landlord02 = new Landlord("Ravi", "89867001826", "62989335737", "Ravi@gmail.com.br");
		Landlord landlord03 = new Landlord("Elli", "21422187926", "63998845787", "Elli@gmail.com.br");
		Landlord landlord04 = new Landlord("Norabel", "38766718686", "92999042606", "Norabel@gmail.com.br");
		Landlord landlord05 = new Landlord("YuYan", "94614156487", "62991046653", "YuYan@gmail.com.br");

		// LANDLORD SERVICE
		landlordService.addLandlord(landlord01.getName(), landlord01.getCpf(), landlord01.getTelephone(),
				landlord01.getEmail());
		landlordService.addLandlord(landlord02.getName(), landlord02.getCpf(), landlord02.getTelephone(),
				landlord02.getEmail());
		landlordService.addLandlord(landlord03.getName(), landlord03.getCpf(), landlord03.getTelephone(),
				landlord03.getEmail());
		landlordService.addLandlord(landlord04.getName(), landlord04.getCpf(), landlord04.getTelephone(),
				landlord04.getEmail());
		landlordService.addLandlord(landlord05.getName(), landlord05.getCpf(), landlord05.getTelephone(),
				landlord05.getEmail());

		// PROPERTY ADD
		Property property01 = new Property("Rua Gonçalo de Carvalho (RS)", 1000, PropertyType.RESIDENTIAL,
				PropertyOccupation.OCCUPIED);
		Property property02 = new Property("Rua do Mucugê (BA)", 1200, PropertyType.COMMERCIAL,
				PropertyOccupation.UNOCCUPIED);
		Property property03 = new Property("Rua das Pedras (RJ)", 1600, PropertyType.RESIDENTIAL,
				PropertyOccupation.UNOCCUPIED);
		Property property04 = new Property("Rua da Aurora (PE)", 1800, PropertyType.COMMERCIAL,
				PropertyOccupation.UNOCCUPIED);
		Property property05 = new Property("Rua Bento Gonçalves (RS)", 2000, PropertyType.RESIDENTIAL,
				PropertyOccupation.UNOCCUPIED);

		// PROPERTY SERVICE
		propertyService.addProperty(landlord01, property01.getAddress(), property01.getRentalValue(),
				property01.getType(), property01.getOccupation());
		propertyService.addProperty(landlord02, property02.getAddress(), property02.getRentalValue(),
				property02.getType(), property02.getOccupation());
		propertyService.addProperty(landlord03, property03.getAddress(), property03.getRentalValue(),
				property03.getType(), property03.getOccupation());
		propertyService.addProperty(landlord04, property04.getAddress(), property04.getRentalValue(),
				property04.getType(), property04.getOccupation());
		propertyService.addProperty(landlord05, property05.getAddress(), property05.getRentalValue(),
				property05.getType(), property05.getOccupation());

		// ASSIGN PROPERTY TO LANDLORD
		leaseService.assignPropertyToLandlord(landlord01, property01);
		leaseService.assignPropertyToLandlord(landlord02, property02);
		leaseService.assignPropertyToLandlord(landlord03, property03);
		leaseService.assignPropertyToLandlord(landlord04, property04);
		leaseService.assignPropertyToLandlord(landlord05, property05);
	}

}
