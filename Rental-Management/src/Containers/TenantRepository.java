package containers;
// Repositório do Inquilino

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Interface.ITenantRepository;
import connection.PropertyConnections;
import entity.Tenant;

public class TenantRepository implements ITenantRepository {

	@Override
	public void save(Tenant tenant) {
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

	@Override
	public void updateAll(Tenant tenant) {
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

	@Override
	public void updateName(Tenant tenant) {
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

	@Override
	public void updateTelephone(Tenant tenant) {
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

	@Override
	public void updateEmail(Tenant tenant) {
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

	@Override
	public void updateBalance(Tenant tenant) {
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

	@Override
	public void deleteByID(int id) {
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

	@Override
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

	@Override
	public Tenant getTenantById(int id) throws SQLException {
		String sql = "SELECT * FROM tenant WHERE id = ?";
		Tenant tenant = null;

	    Connection conn = null;
	    PreparedStatement pstm = null;
	    ResultSet rset = null;

	    try {
	        conn = PropertyConnections.createConnectionToMySQL();
	        pstm = conn.prepareStatement(sql);
	        pstm.setInt(1, id); // Define o ID do proprietário
	        rset = pstm.executeQuery();

	        if (rset.next()) {
	        	tenant = new Tenant();
	        	tenant.setId(rset.getInt("id"));
	        	tenant.setName(rset.getString("name"));
	        	tenant.setCpf(rset.getString("cpf"));
	        	tenant.setTelephone(rset.getString("telephone"));
	        	tenant.setEmail(rset.getString("email"));
	        	tenant.setBalance(rset.getDouble("balance"));
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

	    return tenant;
	}

}
