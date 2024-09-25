package Services;
// Serviço Contrato

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Containers.LeaseRepository;
import Containers.StackRepository;
import Entity.Landlord;
import Entity.Lease;
import Entity.Property;
import Entity.Tenant;
import Enum.EnumLandlordException;
import Enum.EnumLeaseException;
import Enum.EnumPropertyException;
import Enum.PropertyOccupation;
import Exceptions.LeaseException;
import Utils.DatetimeExtensions;

public class LeaseService {
    // ATTRIBUTES

    private static final Scanner scanner = new Scanner(System.in);
    private LeaseRepository leaseRepository = new LeaseRepository(new ArrayList<>());

    // CONSTRUCTOR

    public LeaseService(LeaseRepository leaseRepository) {
        this.leaseRepository = leaseRepository;
    }

    // METHODS PERSONALIZED

    // CREATE
    public void addLease(String startDate, String endDate, Landlord landlord, Property property,
            Tenant tenant) throws LeaseException, ParseException {
        if (landlord == null || property == null || tenant == null) {
            throw new LeaseException("Erro: " + EnumLeaseException.LeaseNoRegistered);
        } else if (property.getLandlord().equals(null)) {
            throw new LeaseException("Erro: " + EnumLeaseException.LandlordNotAddedToProperty);
        } else if (startDate.equals(null) || endDate.equals(null)) {
            throw new LeaseException("Erro: " + EnumLeaseException.LeaseInvalidStartOrEndDate);
        }

        Lease lease = createLease(startDate, endDate, landlord, property, tenant);
        if (lease != null) {
            leaseRepository.addLease(lease);
            System.out.println("\nContrato adicionado com sucesso!");
        } else {
            System.out.println("Erro: " + EnumLeaseException.LeaseInvalid);
        }
    }

    public Lease createLease(String startDate, String endDate, Landlord landlord, Property property,
            Tenant tenant) throws LeaseException, ParseException {
        if (landlord.getCpf().equals(tenant.getCpf())) {
            throw new LeaseException("Erro: " + EnumLeaseException.LandlordAndTenantHaveTheSameCPF);
        } else if (property.getOccupation().equals(PropertyOccupation.OCCUPIED)) {
            throw new LeaseException("Erro: " + EnumPropertyException.PropertyInvalidOccupation);
        }
        assignTenantToProperty(property, tenant);
        return new Lease(dateTimeExtensions(startDate), dateTimeExtensions(endDate), landlord, property, tenant);
    }

    private String dateTimeExtensions(String date) throws ParseException {
        DatetimeExtensions.toDate(date);
        return date;
    }

    public void assignTenantToProperty(Property property, Tenant tenant) {
        // Verifica se o imóvel já tem um inquilino associado
        if (property.getTenant() != null) {
            System.out.println("Erro: O imóvel já tem um inquilino associado!");
        }

        // Adiciona o inquilino à lista do imóvel
        List<Tenant> tenants = property.getTenant();
        if (tenants == null) {
            tenants = new ArrayList<>();
            property.setTenant(tenants);
        }
        tenants.add(tenant);

        // Atualiza a associação bidirecional
        tenant.setProperty(property);

        System.out.println("\n| Inquilino " + tenant.getName() + "\n| Cadastrado ao Imóvel " + property.getaddress());
    }

    public void assignPropertyToLandlord(Landlord landlord, Property property) {
        // Verifica se o imóvel já tem um proprietário associado
        if (property.getLandlord() != null) {
            System.out.println("Erro: O imóvel já tem um proprietário associado!");
        }

        // Adiciona a propriedade à lista do proprietário
        List<Property> properties = landlord.getProperty();
        if (properties == null) {
            properties = new ArrayList<>();
            landlord.setProperty(properties);
        }
        properties.add(property);

        // Atualiza a associação bidirecional
        property.setLandlord(landlord);

        System.out.println(
                "\n| Imóvel " + property.getaddress() + "\n| Cadastrado ao proprietário " + landlord.getName());
    }

    // REOMVE (FALTA MEHORAR)
    public void removeLease() {
        if (leaseRepository.leases.empty()) {
            System.out.println(("Erro: " + EnumLandlordException.LandlordNoRegistered));
        } else {
            Lease pop = leaseRepository.leases.pop();
            System.out.println("\nContrato: " + pop.getId() + ". Removido com sucesso!");
        }
    }

    // LIST
    public void listLease() {
        StackRepository<Lease> leases = leaseRepository.listLease();
        if (leases.empty()) {
            System.out.println(("Erro: " + EnumPropertyException.PropertyNoRegistered));
        } else {
            for (int i = 0; i < leases.size(); i++) {
                Lease l = leases.get(i);
                l.setId(i);
                System.out.println("\n-------------------------------------------------------------------------------");
                System.out.print("Contrato: " + l.getId() + "\n");
                System.out.print(
                        " | Proprietário: " + l.getLandlord().getId() + " - " + l.getLandlord().getName() + "\n");
                System.out.print(" | Imovel: " + l.getProperty().getId() + " - " + l.getProperty().getaddress() + "\n");
                System.out.print(" | Inquilino: " + l.getTenant().getId() + " - " + l.getTenant().getName() + "\n");
                System.out.print(" | Data de Inicio: " + l.getStartDate());
                System.out.print(" | Data de Fim: " + l.getEndDate());
                System.out.print(" | Valor: " + l.getProperty().getRentalValue() + " |");
                System.out.println("\n-------------------------------------------------------------------------------");
            }
        }
    }

    // CHANGE
    public void changeLease(int id) throws LeaseException, ParseException {
        if (leaseRepository.leases.empty()) {
            System.out.println(("Erro: " + EnumLeaseException.LeaseNoRegistered));
        } else {
            if (id < 0 || id >= leaseRepository.leases.size()) {
                System.out.println(("Erro: " + EnumLeaseException.LeaseInvalidIndex));
            }

            Lease lease = leaseRepository.leases.get(id);
            System.out.println(
                    "\nQuais as novas informações do Contrato deseja mudar? \n| 0.Nenhum | 1.Data de Inicio | 2.Data de Fim |");
            System.out.print("\nOpção: ");
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    System.out.print("\nNova Data de Inicio (DD/MM/AA): ");
                    String newStartDate = scanner.nextLine();
                    lease.setStartDate(dateTimeExtensions(newStartDate));
                    System.out.println("\nContrato atualizado com sucesso!");
                    break;
                case 2:
                    System.out.print("\nNova Data de Fim (DD/MM/AA): ");
                    String newEndDate = scanner.nextLine();
                    lease.setEndDate(dateTimeExtensions(newEndDate));
                    System.out.println("\nContrato atualizado com sucesso!");
                    break;
                default:
                    option = 0;
                    System.out.println("\nContrato não foi atualizado!");
                    break;
            }
        }
    }

    // SEARCH
    public void searchLease(int id) {
        Lease lease = leaseRepository.searchLease(id);
        System.out.println(lease.getId());
        System.out.println(lease.getLandlord().getId());
        System.out.println(lease.getLandlord().getName());
        System.out.println(lease.getLandlord().getProperty());
    }
}
