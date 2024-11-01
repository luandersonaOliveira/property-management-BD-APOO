package views;

import dao.PropertyDAO;
import entity.Tenant;

public class App {
	public static void main(String[] args) throws Exception {
		PropertyDAO propertyDAO = new PropertyDAO();
		
		Tenant tenant = new Tenant();
		tenant.setName("Guru Guru");
		tenant.setCpf("14725836900");
		tenant.setTelephone("147258369");
		tenant.setEmail("guru@gmail.com");
		tenant.setBalance(9000);
		
		propertyDAO.tenantSave(tenant);
		
		Tenant tenant1 = new Tenant();
		tenant1.setName("Waka");
		tenant1.setCpf("12365478900");
		tenant1.setTelephone("123654789");
		tenant1.setEmail("waka@gmail.com");
		tenant1.setBalance(9000);
		
		//propertyDAO.tenantSave(tenant1);
		
		// Atualizar o inquilino
		Tenant t1 = new Tenant();
		t1.setName("Goro Goro");
		t1.setCpf("147852369");
		t1.setTelephone("147852369");
		t1.setEmail("guru@gmail.com");
		t1.setBalance(8000);
		t1.setId(1);
		
		// propertyDAO.TenantUpdate(t1);
		
		// Deletar o inquilino pelo seu numero de ID
		// propertyDAO.tenantDeleteByID(1);
		
		// Visualização dos registros do banco de dados TODOS
		
		for (Tenant t : propertyDAO.getTenants()) {
			System.out.println("\n-------------------------------------------------------------------------------");
			System.out.print("Inquilino: " + t.getId() + "\n");
			System.out.print(" | Nome: " + t.getName());
			System.out.print(" | CPF: " + t.getCpf());
			System.out.print("\n | Telefone: " + t.getTelephone());
			System.out.print(" | Email: " + t.getEmail());
			System.out.print(" | Saldo: " + t.getBalance() + " |");
			System.out.println("\n-------------------------------------------------------------------------------");
		}
	}
}
