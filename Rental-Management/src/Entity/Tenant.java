package Entity;
// Inquilino

public class Tenant {
    private static int nextId = 0;

    // ATTRIBUTES

    private int id;
    private double balance;
    private String name, cpf, telephone, email;
    private Property property;

    // CONSTRUCTOR

    public Tenant(String name, String cpf, String telephone, String email, double balance) {
        this.name = name;
        this.cpf = cpf;
        this.telephone = telephone;
        this.email = email;
        this.balance = balance;
        this.setId(nextId++);
    }

    // METODOS ESPECIAS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}
