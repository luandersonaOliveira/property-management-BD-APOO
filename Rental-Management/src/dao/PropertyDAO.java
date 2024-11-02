package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.PropertyConnections;
import entity.Tenant;

public class PropertyDAO {

	/*
	 * CRUD C: Create R: Read U: Update D: Delete
	 */

	public void tenantSave(Tenant tenant) {
		String sql = "INSERT INTO tenant (name, cpf, telephone, email, balance) VALUES (?, ?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			// Cria uma conexão com banco de dados
			conn = PropertyConnections.createConnectionToMySQL();

			// Criamos uma PreparedStatement, para executar uma query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores que são esperados pela query
			pstm.setString(1, tenant.getName());
			pstm.setString(2, tenant.getCpf());
			pstm.setString(3, tenant.getTelephone());
			pstm.setString(4, tenant.getEmail());
			pstm.setDouble(5, tenant.getBalance());

			// Executar a query
			pstm.execute();
			System.out.println("\nInquilino adicionado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Fechar as conexões
			try {
				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void TenantUpdate(Tenant tenant) {
		String sql = "UPDATE tenant SET name = ?, telephone = ?, email = ?, balance = ? " + "WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria conexão com o banco
			conn = PropertyConnections.createConnectionToMySQL();

			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			pstm.setString(1, tenant.getName());
			pstm.setString(2, tenant.getTelephone());
			pstm.setString(3, tenant.getEmail());
			pstm.setDouble(4, tenant.getBalance());
			pstm.setInt(5, tenant.getId());

			// Executar a query
			pstm.execute();
			System.out.println("\nInquilino atualizado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	public void TenantUpdateName(Tenant tenant) {
		String sql = "UPDATE tenant SET name = ?" + "WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria conexão com o banco
			conn = PropertyConnections.createConnectionToMySQL();

			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			pstm.setString(1, tenant.getName());
			pstm.setInt(2, tenant.getId());

			// Executar a query
			pstm.execute();
			System.out.println("\nInquilino atualizado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void TenantUpdateTelephone(Tenant tenant) {
		String sql = "UPDATE tenant SET telephone = ?" + "WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria conexão com o banco
			conn = PropertyConnections.createConnectionToMySQL();

			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			pstm.setString(1, tenant.getTelephone());
			pstm.setInt(2, tenant.getId());

			// Executar a query
			pstm.execute();
			System.out.println("\nInquilino atualizado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	public void TenantUpdateEmail(Tenant tenant) {
		String sql = "UPDATE tenant SET email = ?" + "WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria conexão com o banco
			conn = PropertyConnections.createConnectionToMySQL();

			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			pstm.setString(1, tenant.getEmail());
			pstm.setInt(2, tenant.getId());

			// Executar a query
			pstm.execute();
			System.out.println("\nInquilino atualizado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	public void TenantUpdateBalance(Tenant tenant) {
		String sql = "UPDATE tenant SET balance = ? " + "WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria conexão com o banco
			conn = PropertyConnections.createConnectionToMySQL();

			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			pstm.setDouble(1, tenant.getBalance());
			pstm.setInt(2, tenant.getId());

			// Executar a query
			pstm.execute();
			System.out.println("\nInquilino atualizado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	public void tenantDeleteByID(int id) {
		String sql = "DELETE FROM tenant  WHERE id = ?";

		Connection conn = null;

		PreparedStatement pstm = null;

		try {
			conn = PropertyConnections.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			pstm.setInt(1, id);

			pstm.execute();
			System.out.println("Inquilino removido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Tenant> getTenants() throws SQLException {
		String sql = "SELECT * FROM tenant";

		List<Tenant> tenants = new ArrayList<Tenant>();

		Connection conn = null;
		PreparedStatement pstm = null;

		// Classe que vai recuperar os dados no banco ****SELECT****
		ResultSet rset = null;

		try {
			conn = PropertyConnections.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			while (rset.next()) {
				Tenant tenant = new Tenant();

				// Recuperar o id
				tenant.setId(rset.getInt("id"));

				// Recuperar o nome
				tenant.setName(rset.getString("name"));

				// Recuoerar o cpf
				tenant.setCpf(rset.getString("cpf"));
				
				// Recuoerar o telefone
				tenant.setTelephone(rset.getString("telephone"));
				
				// Recuoerar o email
				tenant.setEmail(rset.getString("email"));
				
				// Recuperar o saldo
				tenant.setBalance(rset.getDouble("balance"));

				tenants.add(tenant);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}

				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return tenants;
	}

}
