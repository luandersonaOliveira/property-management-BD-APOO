package views;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import entity.Landlord;
import entity.Lease;
import entity.Person;
import entity.Property;
import entity.Tenant;
import enums.PersonsPosition;
import enums.PropertyOccupation;
import enums.PropertyType;
import enums.TheTypeOfBusiness;
import exceptions.EnumPropertyException;
import exceptions.LandlordException;
import exceptions.LeaseException;
import exceptions.PropertyException;
import exceptions.TenantException;
import services.LeaseService;
import services.PersonService;
import services.PropertyService;

public class Main {
	// ATTRIBUTES
	private static final Scanner scanner = new Scanner(System.in);

	// NEWS SERVICES
	private static PersonService personService = new PersonService();
	private static PropertyService propertyService = new PropertyService();
	private static LeaseService leaseService = new LeaseService();

	// CUSTOM METHODS

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

	// MENU TENANT
	private static void menuTenant() throws Exception {
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
				personService.listTenant();
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

	// MENU LANDLORD
	private static void menuLandlord() throws Exception {
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
				personService.listLandlord();
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

	// MENU PROPERTY
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
			System.out.println("---------------------------------------------");
			System.out.print("\n| Opção: ");
			int option = scanner.nextInt();
			scanner.nextLine();
			switch (option) {
			case 1:
				createProperty();
				break;
			case 2:
				propertyService.list();
				break;
			case 3:
				changeProperty();
				break;
			case 0:
				exit = true;
				break;
			}
		} while (!exit);
	}

	// MENU LEASE
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
				leaseService.list();
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
	private static void createTenants() throws TenantException, LandlordException, PropertyException, SQLException {
		try {

			System.out.print("\nNome: ");
			String name = scanner.nextLine();
			System.out.print("CPF: ");
			String cpf = scanner.nextLine();
			System.out.print("Email: ");
			String email = scanner.nextLine();
			System.out.print("Saldo: ");
			double wallet = scanner.nextDouble();

			Person person = new Person(name, cpf, email, wallet, PersonsPosition.TENANT);

			System.out.println("Quantos telefones?");
			int qntTelefones = scanner.nextInt();
			scanner.nextLine();
			for (int i = 0; i < qntTelefones; i++) {
				System.out.println("Qual o telefone?");
				String telephones = scanner.nextLine();
				person.addTelephone(telephones);
			}
			
			personService.add(person.getName(), person.getCpf(), person.getEmail(), person.getWallet(),
					person.getPositions());
		} catch (TenantException e) {
			System.out.println("\n" + e.getMessage());
		}
	}

	// CREATE LANDLORD
	private static void createLandlord() throws TenantException, PropertyException, SQLException {
		try {

			System.out.print("\nNome: ");
			String name = scanner.nextLine();
			System.out.print("CPF: ");
			String cpf = scanner.nextLine();
			System.out.print("Email: ");
			String email = scanner.nextLine();

			Person person = new Person(name, cpf, email, 0, PersonsPosition.LANDLORD);

			System.out.println("Quantos telefones?");
			int qntTelefones = scanner.nextInt();
			scanner.nextLine();
			for (int i = 0; i < qntTelefones; i++) {
				System.out.println("Qual o telefone?");
				String telephones = scanner.nextLine();
				person.addTelephone(telephones);
			}

			personService.add(person.getName(), person.getCpf(), person.getEmail(), person.getWallet(),
					person.getPositions());
		} catch (LandlordException e) {
			System.out.println("\n" + e.getMessage());
		}
	}

	// CREATE PROPERTY
	private static void createProperty() throws Exception {
		try {
			System.out.print("\nInsira o ID do Proprietario: ");
			int idLandlord = scanner.nextInt();
			scanner.nextLine();
			Landlord landlord = personService.searchLandlord(idLandlord);
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
				System.out.print("Numeros de salas: ");
				int rooms = scanner.nextInt();

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

				PropertyType propertyType = null;
				switch (type) {
				case 1:
					System.out.print("Área de Lazer: 1.Sim | 2.Não ");
					System.out.print("\n| Opção: ");
					int area = scanner.nextInt();

					boolean areas = false;
					if (area == 1) {
						areas = true;
					} else if (area != 1) {
						areas = false;
					}

					propertyType = PropertyType.RESIDENTIAL;

					Property prR = new Property(address, rentalValue, propertyType, propertyOccupation, rooms, areas);

					propertyService.add(landlord, prR.getAddress(), prR.getRentalValue(), prR.getType(),
							prR.getOccupation(), prR.getNumberOfRooms(), prR.getBusiness(), prR.isTheLeisureArea());

					leaseService.assignPropertyToLandlord(landlord, prR);
					break;
				case 2:
					System.out.print(
							"Os Tipos de Negócios: 1.Alimentação | 2.Saúde | 3.Serviços Automotivos | 4.Moda | 5.Educação");
					System.out.print("\n| Opção: ");
					int business = scanner.nextInt();

					TheTypeOfBusiness theTypeOfBusiness = null;
					switch (business) {
					case 1:
						theTypeOfBusiness = TheTypeOfBusiness.FOOD;
						break;
					case 2:
						theTypeOfBusiness = TheTypeOfBusiness.HEALTH;
						break;
					case 3:
						theTypeOfBusiness = TheTypeOfBusiness.AUTOMOTIVESERVICES;
						break;
					case 4:
						theTypeOfBusiness = TheTypeOfBusiness.FASHION;
						break;
					case 5:
						theTypeOfBusiness = TheTypeOfBusiness.EDUCATION;
						break;
					default:
						throw new PropertyException("Erro: " + EnumPropertyException.PropertyInvalid);
					}

					propertyType = PropertyType.COMMERCIAL;

					Property prC = new Property(address, rentalValue, propertyType, propertyOccupation, rooms,
							theTypeOfBusiness);

					propertyService.add(landlord, prC.getAddress(), prC.getRentalValue(), prC.getType(),
							prC.getOccupation(), prC.getNumberOfRooms(), prC.getBusiness(), prC.isTheLeisureArea());

					leaseService.assignPropertyToLandlord(landlord, prC);
					break;
				default:
					throw new PropertyException("Erro: " + EnumPropertyException.PropertyInvalidType);
				}

			} else {
				System.out.println("\nErro: Proprietário não foi cadastrado!");
			}

		} catch (PropertyException e) {
			System.out.println("\n" + e.getMessage());
		}
	}

	// CREATE LEASE
	private static void createLease() throws SQLException, Exception {
		try {
			System.out.print("\nInsira o ID do Inquilino: ");
			int idTenant = scanner.nextInt();
			Tenant tenant = personService.searchTenant(idTenant);

			System.out.print("\nInsira o ID do Imóvel: ");
			int idProperty = scanner.nextInt();
			Property property = propertyService.search(idProperty);
			scanner.nextLine();

			if (tenant != null && property != null) {
				System.out.print("\nData de Inicio (YYYY/MM/DD): ");
				String startDate = scanner.nextLine();
				System.out.print("\nData de Fim (YYYY/MM/DD): ");
				String endDate = scanner.nextLine();

				Lease lease = new Lease(startDate, endDate, property, property.getLandlord(), tenant);

				leaseService.add(property.getLandlord(), property, tenant, lease.getStartDate(), lease.getEndDate());
			} else {
				System.out.println("\nErro: Proprietário não foi cadastrado!");
			}

		} catch (LeaseException e) {
			System.out.println("\n" + e.getMessage());
		}

	}

	// CHANGE TENANTS
	private static void changeTenants() throws TenantException, SQLException {
		System.out.print("\nInsira o ID do Inquilino à editar: ");
		int id = scanner.nextInt();
		personService.changeTenant(id);
	}

	// CHANGE LANDLORD
	private static void changeLandlord() throws LandlordException, SQLException, TenantException {
		System.out.print("\nInsira o ID do Proprietário à editar: ");
		int id = scanner.nextInt();
		personService.changeLandlord(id);
	}

	// CHANGE PROPERTY
	private static void changeProperty() throws PropertyException, SQLException {
		System.out.print("\n Tipo do imovel: 1.Residencial | 2.Comercial |");
		System.out.print("\n| Opção: ");
		int type = scanner.nextInt();
		if(type == 1) {
			System.out.print("\nInsira o ID do Imóvel à editar: ");
			int idR = scanner.nextInt();
			propertyService.changeResidential(idR);
		}else if(type == 2) {
			System.out.print("\nInsira o ID do Imóvel à editar: ");
			int idC = scanner.nextInt();
			propertyService.changeCommercial(idC);
		}else {
			System.out.println("Erro!");
		}
	}

	// CHANGE LEASE
	private static void changeLease() throws LeaseException, ParseException, SQLException {
		System.out.print("\nInsira o ID do Contrato à editar: ");
		int id = scanner.nextInt();
		leaseService.change(id);
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
				removeLandlords();
				break;
			case 3:
				removePropertys();
				break;
			case 4:
				deleteLeases();
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
		personService.removeTenant(id);
	}

	private static void removeLandlords() {
		System.out.print("\nInsira o ID do Proprietário para remover: ");
		int id = scanner.nextInt();
		personService.removeLandlord(id);
	}

	private static void removePropertys() {
		System.out.print("\n Tipo do imovel: 1.Residencial | 2.Comercial |");
		System.out.print("\n| Opção: ");
		int type = scanner.nextInt();
		if(type == 1) {
			System.out.print("\nInsira o ID do Imóvel à remover: ");
			int idR = scanner.nextInt();
			propertyService.removeResidential(idR);
		}else if(type == 2) {
			System.out.print("\nInsira o ID do Imóvel à remover: ");
			int idC = scanner.nextInt();
			propertyService.removeCommercial(idC);
		}else {
			System.out.println("Erro!");
		}
	}

	private static void deleteLeases() {
		System.out.print("\nInsira o ID do Contrato para remover: ");
		int id = scanner.nextInt();
		leaseService.remove(id);
	}

	// LIST OF READY PROPERTIES
	private static void listPropertiesByLandlordId() throws SQLException {
		System.out.print("\nInsira o ID do Proprietário: ");
		int id = scanner.nextInt();
		propertyService.listPropertyByLandlordId(id);
	}
}
