package views;
// MAIN

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import Enum.EnumPropertyException;
import Enum.PersonPosition;
import Enum.PropertyOccupation;
import Enum.PropertyType;
import entity.Landlord;
import entity.Lease;
import entity.Person;
import entity.Property;
import entity.PropertyResidential;
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

	// NEWS SERVICES
	private static TenantService tenantService = new TenantService();
	private static LandlordService landlordService = new LandlordService();
	private static PropertyService propertyService = new PropertyService();
	private static LeaseService leaseService = new LeaseService();

	public static void main(String[] args) throws Exception {

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
			System.out.println("\n------------------------------------------");
			System.out.println("| Menu Proprietário: ");
			System.out.println("------------------------------------------");
			System.out.println("| 0.Nenhum.");
			System.out.println("| 1.Para Cadastrar Proprietários.");
			System.out.println("| 2.Para Checar Proprietários.");
			System.out.println("| 3.Para Checar Imoveis dos Proprietários.");
			System.out.println("| 4.Para Editar Proprietários.");
			System.out.println("------------------------------------------");
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
				listPropertiesByLandlordId();
				break;
			case 4:
				changeLandlord();
				break;
			case 0:
				exit = true;
				break;
			}
		} while (!exit);
	}

	private static void menuProperty() throws Exception {
		boolean exit = false;
		do {
			System.out.println("\n---------------------------------------------");
			System.out.println("| Menu Imóvel: ");
			System.out.println("---------------------------------------------");
			System.out.println("| 0.Nenhum.");
			System.out.println("| 1.Para Cadastrar Imoveis aos Proprietários.");
			System.out.println("| 2.Para Checar Imoveis.");
			System.out.println("| 3.Para Editar Imoveis.");
			System.out.println("| 4.Para Adicionar lista de Imoveis pronta.");
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

	private static void menuLease() throws Exception {
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

			Person person = new Person(name, cpf, telephone, email, PersonPosition.TENANT);
			Tenant tenant = new Tenant(person.getName(), person.getCpf(), person.getTelephone(), person.getEmail(),
					balance, person.getPosition());

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

			Person person = new Person(name, cpf, telephone, email, PersonPosition.LANDLORD);
			Landlord landlord = new Landlord(person.getName(), person.getCpf(), person.getTelephone(),
					person.getEmail(), person.getPosition());

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

	// CREATE PROPERTY
	private static void createProperty() throws Exception {
		try {
			System.out.print("\nInsira o ID do Proprietario: ");
			int idLandlord = scanner.nextInt();
			scanner.nextLine();
			Landlord landlord = landlordService.searchLandlord(idLandlord);
			if (landlord != null) {
				System.out.print("\nEndereço: ");
				String address = scanner.nextLine();
				System.out.print("Valor do Aluguel: ");
				double rentalValue = scanner.nextDouble();
				System.out.print("Tipo: 1.Residencial | 2.Comercial");
				System.out.print("\n| Opção: ");
				int type = scanner.nextInt();
				System.out.print("Ocupação: 1.Desocupado | 2.Ocupado");
				System.out.print("\n| Opção: ");
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
	private static void changeProperty() throws PropertyException, SQLException {
		System.out.print("\nInsira o ID do Imóvel à editar: ");
		int id = scanner.nextInt();
		propertyService.changeProperty(id);
	}

	// CREATE LEASE
	private static void createLease() throws SQLException, Exception {
		try {
			System.out.print("\nInsira o ID do Inquilino: ");
			int idTenant = scanner.nextInt();
			Tenant tenant = tenantService.searchTenant(idTenant);

			System.out.print("\nInsira o ID do Imóvel: ");
			int idProperty = scanner.nextInt();
			Property property = propertyService.searchProperty(idProperty);
			scanner.nextLine();

			if (tenant != null && property != null) {
				System.out.print("\nData de Inicio (YYYY/MM/DD): ");
				String startDate = scanner.nextLine();
				System.out.print("\nData de Fim (YYYY/MM/DD): ");
				String endDate = scanner.nextLine();

				Lease lease = new Lease(leaseService.dateTimeExtensionss(startDate),
						leaseService.dateTimeExtensionss(endDate), property.getLandlord(), property, tenant);
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
	private static void changeLease() throws LeaseException, ParseException, SQLException {
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
			System.out.println("| 3.Imóvel do Proprietário.");
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
		System.out.print("\nInsira o ID do Imóvel para remover: ");
		int id = scanner.nextInt();
		propertyService.removeProperty(id);
	}

	private static void removeLandlord() {
		System.out.print("\nInsira o ID do Proprietário para remover: ");
		int id = scanner.nextInt();
		landlordService.removeLandlord(id);
	}

	private static void deleteLease() {
		System.out.print("\nInsira o ID do Contrato para remover: ");
		int id = scanner.nextInt();
		leaseService.removeLease(id);
	}

	// LIST OF READY PROPERTIES
	private static void listPropertiesByLandlordId() throws SQLException {
		System.out.print("\nInsira o ID do Proprietário: ");
		int id = scanner.nextInt();
		propertyService.listPropertyByLandlordId(id);
	}

	private static void listProperties() throws SQLException, Exception {
		// NÃO ESTOU CONSEGUINDO MANDO ESTA LISTA PARA O BANCO DE DADOS. SOMENTE O
		// LANDLORD.

		// LANDLORD ADD
		Person person1 = new Person("Liang", "74678506039", "86986012358", "Liang@gmail.com.br",
				PersonPosition.LANDLORD);
		Landlord l1 = new Landlord(person1.getName(), person1.getCpf(), person1.getTelephone(), person1.getEmail(),
				person1.getPosition());

		Person person2 = new Person("Ravi", "89867001826", "62989335737", "Ravi@gmail.com.br", PersonPosition.LANDLORD);
		Landlord l2 = new Landlord(person2.getName(), person2.getCpf(), person2.getTelephone(), person2.getEmail(),
				person2.getPosition());

		Person person3 = new Person("Elli", "21422187926", "63998845787", "Elli@gmail.com.br", PersonPosition.LANDLORD);
		Landlord l3 = new Landlord(person3.getName(), person3.getCpf(), person3.getTelephone(), person3.getEmail(),
				person3.getPosition());

		Person person4 = new Person("Norabel", "38766718686", "92999042606", "Norabel@gmail.com.br",
				PersonPosition.LANDLORD);
		Landlord l4 = new Landlord(person4.getName(), person4.getCpf(), person4.getTelephone(), person4.getEmail(),
				person4.getPosition());

		Person person5 = new Person("YuYan", "94614156487", "62991046653", "YuYan@gmail.com.br",
				PersonPosition.LANDLORD);
		Landlord l5 = new Landlord(person5.getName(), person5.getCpf(), person5.getTelephone(), person5.getEmail(),
				person5.getPosition());

		// LANDLORD SERVICE ADD
		landlordService.addLandlord(l1.getName(), l1.getCpf(), l1.getTelephone(), l1.getEmail());
		landlordService.addLandlord(l2.getName(), l2.getCpf(), l2.getTelephone(), l2.getEmail());
		landlordService.addLandlord(l3.getName(), l3.getCpf(), l3.getTelephone(), l3.getEmail());
		landlordService.addLandlord(l4.getName(), l4.getCpf(), l4.getTelephone(), l4.getEmail());
		landlordService.addLandlord(l5.getName(), l5.getCpf(), l5.getTelephone(), l5.getEmail());

		// LANDLORD SERVICE SEARCH
		Landlord ll1 = landlordService.searchLandlord(3);
		Landlord ll2 = landlordService.searchLandlord(4);
		Landlord ll3 = landlordService.searchLandlord(5);
		Landlord ll4 = landlordService.searchLandlord(6);
		Landlord ll5 = landlordService.searchLandlord(7);

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
		propertyService.addProperty(ll1, p1.getAddress(), p1.getRentalValue(), p1.getType(), p1.getOccupation());
		propertyService.addProperty(ll2, p2.getAddress(), p2.getRentalValue(), p2.getType(), p2.getOccupation());
		propertyService.addProperty(ll3, p3.getAddress(), p3.getRentalValue(), p3.getType(), p3.getOccupation());
		propertyService.addProperty(ll4, p4.getAddress(), p4.getRentalValue(), p4.getType(), p4.getOccupation());
		propertyService.addProperty(ll5, p5.getAddress(), p5.getRentalValue(), p5.getType(), p5.getOccupation());

		// ASSIGN PROPERTY TO LANDLORD
		leaseService.assignPropertyToLandlord(ll1, p1);
		leaseService.assignPropertyToLandlord(ll2, p2);
		leaseService.assignPropertyToLandlord(ll3, p3);
		leaseService.assignPropertyToLandlord(ll4, p4);
		leaseService.assignPropertyToLandlord(ll5, p5);

	}

}
