package containers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.PropertyConnections;
import entity.Landlord;
import entity.Tenant;
import interfaces.IPersonRepository;

public class PersonRepository implements IPersonRepository {
	// ATTRIBUTES

	private static final LandlordRepository landlordDAO = new LandlordRepository();
	private static final TenantRepository tenantDAO = new TenantRepository();

	// CUSTOM METHODS

	@Override
	public void saveLandlord(Landlord landlord) {
		String sql = "INSERT INTO pessoa (nome, cpf, email, cargo) VALUES (?, ?, ?, ?)";
		// person and landlord

		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			// Cria uma conexão com banco de dados
			conn = PropertyConnections.createConnectionToMySQL();

			// Criamos uma PreparedStatement, para executar uma query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores que são esperados pela query
			pstm.setString(1, landlord.getName());
			pstm.setString(2, landlord.getCpf());
			pstm.setString(3, landlord.getEmail());
			pstm.setString(4, landlord.getPositions().toString());

			// Executar a query
			pstm.execute();
			landlordDAO.save(landlord);
			System.out.println("\nProprietário adicionado com sucesso!");
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
	public void saveTenant(Tenant tenant) {
		String sql = "INSERT INTO pessoa (nome, cpf, email, saldo, cargo) VALUES (?, ?, ?, ?, ?)";
		// person and landlord

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
			pstm.setString(5, tenant.getPositions().toString());

			// Executar a query
			pstm.execute();
			tenantDAO.save(tenant);
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
	public void updateAllLandlord(Landlord landlord) {
		String sql = "UPDATE pessoa SET nome = ?, email = ?" + "WHERE idpessoa = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria conexão com o banco
			conn = PropertyConnections.createConnectionToMySQL();

			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			pstm.setString(1, landlord.getName());
			pstm.setString(2, landlord.getEmail());
			pstm.setInt(3, landlord.getId());

			// Executar a query
			pstm.execute();
			landlordDAO.updateAll(landlord);
			System.out.println("\nProprietário atualizado com sucesso!");
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
	public void updateAllTenant(Tenant tenant) {
		String sql = "UPDATE pessoa SET nome = ?, email = ?, saldo = ? " + "WHERE idpessoa = ?";

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
			tenantDAO.updateAll(tenant);
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
	public void updateLandlordName(Landlord landlord) {
		String sql = "UPDATE pessoa SET nome = ?" + "WHERE idpessoa = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria conexão com o banco
			conn = PropertyConnections.createConnectionToMySQL();

			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			pstm.setString(1, landlord.getName());
			pstm.setInt(2, landlord.getId());

			// Executar a query
			pstm.execute();
			landlordDAO.updateName(landlord);
			System.out.println("\nProprietário atualizado com sucesso!");
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
	public void updateTenantName(Tenant tenant) {
		String sql = "UPDATE pessoa SET nome = ?" + "WHERE idpessoa = ?";

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
			tenantDAO.updateName(tenant);
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
	public void updateLandlordEmail(Landlord landlord) {
		String sql = "UPDATE pessoa SET email = ?" + "WHERE idpessoa = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria conexão com o banco
			conn = PropertyConnections.createConnectionToMySQL();

			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			pstm.setString(1, landlord.getEmail());
			pstm.setInt(2, landlord.getId());

			// Executar a query
			pstm.execute();
			landlordDAO.updateEmail(landlord);
			System.out.println("\nProprietário atualizado com sucesso!");
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
	public void updateTenantEmail(Tenant tenant) {
		String sql = "UPDATE pessoa SET email = ?" + "WHERE idpessoa = ?";

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
			tenantDAO.updateEmail(tenant);
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
	public void updateTenantWallet(Tenant tenant) {
		String sql = "UPDATE pessoa SET saldo = ? " + "WHERE idpessoa = ?";

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
			tenantDAO.updateWallet(tenant);
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
	public void deleteLandlordByID(int id) {
		String sql = "DELETE FROM pessoa WHERE idpessoa = ?";

		Connection conn = null;

		PreparedStatement pstm = null;

		try {
			conn = PropertyConnections.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			pstm.setInt(1, id);

			pstm.execute();
			landlordDAO.deleteByID(id);
			System.out.println("Proprietário removido com sucesso!");
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
	public void deleteTenantByID(int id) {
		String sql = "DELETE FROM pessoa WHERE idpessoa = ?";

		Connection conn = null;

		PreparedStatement pstm = null;

		try {
			conn = PropertyConnections.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			pstm.setInt(1, id);

			pstm.execute();
			tenantDAO.deleteByID(id);
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
	public List<Landlord> getLandlords() throws SQLException {
		String sql = "SELECT * FROM proprietario";

		List<Landlord> landlords = new ArrayList<Landlord>();

		Connection conn = null;
		PreparedStatement pstm = null;

		// Classe que vai recuperar os dados no banco ****SELECT****
		ResultSet rset = null;

		try {
			conn = PropertyConnections.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			while (rset.next()) {
				Landlord landlord = new Landlord();

				// Recuperar o id
				landlord.setId(rset.getInt("idpessoa"));

				// Recuperar o nome
				landlord.setName(rset.getString("nome"));

				// Recuoerar o cpf
				landlord.setCpf(rset.getString("cpf"));

				// Recuoerar o email
				landlord.setEmail(rset.getString("email"));

				landlords.add(landlord);
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
		return landlords;
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
				tenant.setId(rset.getInt("idpessoa"));

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
	public Landlord getLandlordById(int id) throws SQLException {
		String sql = "SELECT * FROM proprietario WHERE id = ?";
		Landlord landlord = null;

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			conn = PropertyConnections.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id); // Define o ID do proprietário
			rset = pstm.executeQuery();

			if (rset.next()) {
				landlord = new Landlord();
				landlord.setId(rset.getInt("idpessoa"));
				landlord.setName(rset.getString("nome"));
				landlord.setCpf(rset.getString("cpf"));
				landlord.setEmail(rset.getString("email"));
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

		return landlord;
	}

	@Override
	public Tenant getTenantById(int id) throws SQLException {
		String sql = "SELECT * FROM inquilino WHERE id = ?";
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
				tenant.setId(rset.getInt("idpessoa"));
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
