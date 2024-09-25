package Services;
// Serviço Proprietário

import java.util.ArrayList;
import java.util.Scanner;

import Containers.LandlordRepository;
import Entity.Landlord;
import Enum.EnumLandlordException;
import Exceptions.LandlordException;

public class LandlordService {
    // ATTRIBUTES

    private static final Scanner scanner = new Scanner(System.in);
    private LandlordRepository landlordRepository = new LandlordRepository();

    // CONSTRUCTOR

    public LandlordService(LandlordRepository landlordRepository) {
        this.landlordRepository = landlordRepository;
    }

    // METHODS PERSONALIZED

    // CREATE
    public void addLandlord(String name, String cpf, String telephone, String email) throws LandlordException {
        if (cpf.length() != 11) {
            throw new LandlordException("Erro: " + EnumLandlordException.LandlordInvalidCPF);
        } else if (telephone.length() != 9 && telephone.length() != 11) {
            throw new LandlordException("Erro: " + EnumLandlordException.LandlordInvalidTelephone);
        }

        Landlord landlord = createLandlord(name, cpf, telephone, email);
        if (landlord != null) {
            landlordRepository.addLandlord(landlord);
            System.out.println("\nProprietário adicionado com sucesso!");
        } else {
            throw new LandlordException("Erro: " + EnumLandlordException.LandlordInvalid);
        }
    }

    public Landlord createLandlord(String name, String cpf, String telephone, String email) throws LandlordException {
        return new Landlord(nameFormart(name), cpfFormart(cpf), telephoneFormart(telephone), email);
    }

    private String nameFormart(String name) {
        String nameFormart = name.toUpperCase();
        return nameFormart;
    }

    private String cpfFormart(String cpf) throws LandlordException {
        if (cpf.length() == 11) {
            return String.format("%s.%s.%s-%s",
                    cpf.substring(0, 3),
                    cpf.substring(3, 6),
                    cpf.substring(6, 9),
                    cpf.substring(9, 11));
        } else {
            cpf = null;
            throw new LandlordException("Erro: " + EnumLandlordException.LandlordInvalidCPF);
        }
    }

    private String telephoneFormart(String telephone) throws LandlordException {
        if (telephone.length() == 9) {
            return String.format("%s-%s",
                    telephone.substring(0, 5),
                    telephone.substring(5, 9));
        } else if (telephone.length() == 11) {
            return String.format("(%s) %s-%s",
                    telephone.substring(0, 2),
                    telephone.substring(2, 7),
                    telephone.substring(7, 11));
        } else {
            telephone = null;
            throw new LandlordException("Erro: " + EnumLandlordException.LandlordInvalidTelephone);
        }
    }

    // REMOVE
    public void removeLandlord(int id) {
        if (landlordRepository.landlords.isEmpty()) {
            System.out.println(("Erro: " + EnumLandlordException.LandlordNoRegistered));
        } else {
            landlordRepository.landlords.remove(id);
            System.out.println("\nProprietário: " + id + ". Removido com sucesso!");
        }
    }

    // LIST
    public void listLandlord() {
        ArrayList<Landlord> landlords = landlordRepository.listLandlord();
        if (landlords.isEmpty()) {
            System.out.println(("Erro: " + EnumLandlordException.LandlordNoRegistered));
        } else {
            for (int i = 0; i < landlords.size(); i++) {
                Landlord l = landlords.get(i);
                l.setId(i);
                System.out.println("\n-------------------------------------------------------------------------------");
                System.out.print("Proprietário: " + l.getId() + "\n");
                System.out.print(" | Nome: " + l.getName());
                System.out.print(" | CPF: " + l.getCpf());
                System.out.print("\n | Telefone: " + l.getTelephone());
                System.out.print(" | Email: " + l.getEmail() + " |");
                System.out.println("\n-------------------------------------------------------------------------------");
            }
        }
    }

    // CHANGE
    public void changeLandlord(int id) throws LandlordException {
        if (landlordRepository.landlords.isEmpty()) {
            System.out.println(("Erro: " + EnumLandlordException.LandlordNoRegistered));
        } else {
            if (id < 0 || id >= landlordRepository.landlords.size()) {
                System.out.println(("Erro: " + EnumLandlordException.LandlordInvalidIndex));
            }

            Landlord landlord = landlordRepository.landlords.get(id);
            System.out.println(
                    "\nQuais as novas informações do Proprietário deseja mudar? \n| 0.Nenhum | 1.Nome | 2.Telefone | 3.Email |");
            System.out.print("\nOpção: ");
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    System.out.print("Novo Nome: ");
                    String newName = scanner.nextLine();
                    landlord.setName(nameFormart(newName));
                    System.out.println("\nProprietário atualizado com sucesso!");
                    break;
                case 2:
                    System.out.print("Novo Telefone: ");
                    String newTelephone = scanner.nextLine();
                    landlord.setTelephone(telephoneFormart(newTelephone));
                    System.out.println("\nProprietário atualizado com sucesso!");
                    break;
                case 3:
                    System.out.print("Novo Email: ");
                    String newEmail = scanner.nextLine();
                    landlord.setEmail(newEmail);
                    System.out.println("\nProprietário atualizado com sucesso!");
                    break;
                default:
                    option = 0;
                    System.out.println("\nProprietário não foi atualizado!");
                    break;
            }
        }
    }

    // Search
    public void searchLandlord(int id) {
        Landlord landlord = landlordRepository.searchLandlord(id);
        System.out.println(landlord.getName());
        System.out.println(landlord.getId());
    }
}
