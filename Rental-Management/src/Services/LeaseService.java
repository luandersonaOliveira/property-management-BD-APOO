package services;
// Serviço Contrato

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import containers.LeaseRepository;
import entity.Landlord;
import entity.Lease;
import entity.Property;
import entity.Tenant;
import enums.PropertyOccupation;
import exceptions.EnumLeaseException;
import exceptions.EnumPropertyException;
import exceptions.LeaseException;
import utils.DatetimeExtensions;

public class LeaseService {
	// ATTRIBUTES

	private static final Scanner scanner = new Scanner(System.in);
	private static final LeaseRepository leaseDAO = new LeaseRepository();

	// ADD
	public void add(Landlord landlord, Property property, Tenant tenant, String startDate, String endDate)
			throws LeaseException, ParseException {
		if (landlord == null || property == null || tenant == null) {
			throw new LeaseException("Erro: " + EnumLeaseException.LeaseNoRegistered);
		} else if (property.getLandlord().equals(null)) {
			throw new LeaseException("Erro: " + EnumLeaseException.LandlordNotAddedToProperty);
		} else if (startDate.equals(null) || endDate.equals(null)) {
			throw new LeaseException("Erro: " + EnumLeaseException.LeaseInvalidStartOrEndDate);
		}

		Lease lease = create(landlord, property, tenant, startDate, endDate);
		if (lease != null) {
			leaseDAO.save(lease);
		} else {
			System.out.println("Erro: " + EnumLeaseException.LeaseInvalid);
		}
	}

	// CREATE
	public Lease create(Landlord landlord, Property property, Tenant tenant, String startDate, String endDate)
			throws LeaseException, ParseException {
		if (landlord.getCpf().equals(tenant.getCpf())) {
			throw new LeaseException("Erro: " + EnumLeaseException.LandlordAndTenantHaveTheSameCPF);
		} else if (property.getOccupation().equals(PropertyOccupation.OCCUPIED)) {
			throw new LeaseException("Erro: " + EnumPropertyException.PropertyInvalidOccupation);
		}
		assignTenantToProperty(property, tenant);
		return new Lease(startDate, endDate, property, landlord, tenant);
	}

	// Formart
	public Date dateTimeExtensions(Date date) throws ParseException {
		DatetimeExtensions.toString(date);
		return date;
	}

	public String dateTimeExtensionss(String date) throws ParseException {
		DatetimeExtensions.toDate(date);
		return date;
	}

	// Assign Tenant To Property
	public void assignTenantToProperty(Property property, Tenant tenant) {
		if (property.getTenant() != null) {
			System.out.println("Erro: O Imóvel já tem um inquilino associado!");
		}

		List<Tenant> tenants = property.getTenant();
		if (tenants == null) {
			tenants = new ArrayList<>();
			property.setTenant(tenants);
		}
		tenants.add(tenant);

		tenant.setProperty(property);
		property.setOccupation(PropertyOccupation.OCCUPIED);

		System.out.println("\n| Inquilino " + tenant.getName() + "\n| Cadastrado ao Imóvel " + property.getAddress());
	}

	// Assign Property To Landlord
	public void assignPropertyToLandlord(Landlord landlord, Property property) {
		if (property.getLandlord() != null) {
			System.out.println("Erro: O Imóvel já tem um proprietário associado!");
		}

		List<Property> properties = landlord.getProperty();
		if (properties == null) {
			properties = new ArrayList<>();
			landlord.setProperty(properties);
		}
		properties.add(property);

		property.setLandlord(landlord);

		System.out.println(
				"\n| Imóvel " + property.getAddress() + "\n| Cadastrado ao proprietário " + landlord.getName());
	}

	// REMOVE
	public void remove(int id) {
		Lease lease = new Lease();
		lease.setPropertyReturnDate(
				"\nO prazo geral para desocupação de um imóvel alugado é de 30 dias, contados a partir da data em que o proprietário comunica o inquilino de sua intenção de encerrar o contrato.\n");
		lease.getPropertyReturnDate();
		leaseDAO.deleteByID(id);
	}

	// LIST
	public void list() throws SQLException {
		if (leaseDAO.getLease().isEmpty()) {
			System.out.println(("Erro: " + EnumPropertyException.PropertyNoRegistered));
		} else {
			for (Lease l : leaseDAO.getLease()) {
				System.out.print("\nID Contrato: " + l.getId() + "\n");
				System.out.print(" | Proprietário: " + l.getLandlord().getCpf());
				System.out.print("\n | Inquilino: " + l.getTenant().getCpf());
				System.out.print("\n | ID Imóvel: " + l.getProperty().getId());
				System.out.print("\n | Data de Inicio: " + l.getStartDate().toString());
				System.out.print("\n | Data de Fim: " + l.getEndDate().toString());
			}
		}
	}

	// CHANGE
	public void change(int id) throws LeaseException, ParseException, SQLException {
		if (leaseDAO.getLease().isEmpty()) {
			System.out.println(("Erro: " + EnumLeaseException.LeaseNoRegistered));
		} else {
			if (id <= 0 || id > leaseDAO.getLease().size()) {
				throw new LeaseException("Erro: " + EnumLeaseException.LeaseInvalid);
			}

			Lease lease = new Lease();
			System.out.println(
					"\nQuais as novas informações do Contrato deseja mudar? \n| 0.Nenhum | 1.Data de Inicio | 2.Data de Fim |");
			System.out.print("\n| Opção: ");
			int option = scanner.nextInt();
			scanner.nextLine();
			switch (option) {
			case 1:
				System.out.print("\nNova Data de Inicio (YYYY/MM/DD): ");
				String newStartDate = scanner.nextLine();
				lease.setStartDate(dateTimeExtensionss(newStartDate));
				lease.setId(id);
				leaseDAO.updateStartDate(lease);
				break;
			case 2:
				System.out.print("\nNova Data de Fim (YYYY/MM/DD): ");
				String newEndDate = scanner.nextLine();
				lease.setEndDate(dateTimeExtensionss(newEndDate));
				lease.setId(id);
				leaseDAO.updateEndDate(lease);
				break;
			default:
				System.out.println("\nContrato não foi atualizado!");
				option = 0;
				break;
			}
		}
	}

	// SEARCH
	public Lease searchLease(int id) throws SQLException, Exception {
		Lease lease = null;
		if (leaseDAO.getLease().isEmpty()) {
			System.out.println("Erro: " + EnumLeaseException.LeaseNoRegistered);
		} else {
			lease = leaseDAO.getLeaseById(id);
			if (lease == null) {
				System.out.println("Erro: Contrato não encontrado.");
			}
		}
		return lease;
	}

}
