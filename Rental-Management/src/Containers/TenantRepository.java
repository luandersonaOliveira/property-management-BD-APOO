package containers;
// Repositório do Inquilino

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import interfaces.ITenantRepository;
import connection.PropertyConnections;
import entity.Tenant;

public class TenantRepository implements ITenantRepository {

	@Override
	public void save(Tenant tenant) {
		String sql = "INSERT INTO inquilino (nome, cpf, email, saldo) VALUES (?, ?, ?, ?)";

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
			pstm.setString(3, tenant.getEmail());
			pstm.setDouble(4, tenant.getWallet());

			// Executar a query
			pstm.execute();
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
		String sql = "UPDATE inquilino SET nome = ?, email = ?, saldo = ? " + "WHERE idinquilino = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria conexão com o banco
			conn = PropertyConnections.createConnectionToMySQL();

			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			pstm.setString(1, tenant.getName());
			pstm.setString(2, tenant.getEmail());
			pstm.setDouble(3, tenant.getWallet());
			pstm.setInt(4, tenant.getId());

			// Executar a query
			pstm.execute();
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
		String sql = "UPDATE inquilino SET nome = ?" + "WHERE idinquilino = ?";

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
		String sql = "UPDATE inquilino SET email = ?" + "WHERE idinquilino = ?";

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
	public void updateWallet(Tenant tenant) {
		String sql = "UPDATE inquilino SET saldo = ? " + "WHERE idinquilino = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria conexão com o banco
			conn = PropertyConnections.createConnectionToMySQL();

			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			pstm.setDouble(1, tenant.getWallet());
			pstm.setInt(2, tenant.getId());

			// Executar a query
			pstm.execute();
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
		String sql = "DELETE FROM inquilino WHERE idinquilino = ?";

		Connection conn = null;

		PreparedStatement pstm = null;

		try {
			conn = PropertyConnections.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			pstm.setInt(1, id);

			pstm.execute();
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
		String sql = "SELECT * FROM inquilino";

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
				tenant.setId(rset.getInt("idinquilino"));

				// Recuperar o nome
				tenant.setName(rset.getString("nome"));

				// Recuoerar o cpf
				tenant.setCpf(rset.getString("cpf"));

				// Recuoerar o email
				tenant.setEmail(rset.getString("email"));

				// Recuperar o saldo
				tenant.setWallet(rset.getDouble("saldo"));

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
		String sql = "SELECT * FROM inquilino WHERE idinquilino = ?";
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
				tenant.setId(rset.getInt("idinquilino"));
				tenant.setName(rset.getString("nome"));
				tenant.setCpf(rset.getString("cpf"));
				tenant.setEmail(rset.getString("email"));
				tenant.setWallet(rset.getDouble("saldo"));
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
