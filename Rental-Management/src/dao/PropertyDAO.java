package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Enum.PropertyOccupation;
import Enum.PropertyType;
import connection.PropertyConnections;
import entity.Landlord;
import entity.Lease;
import entity.Property;
import entity.Tenant;
import entity.TenantLease;

public class PropertyDAO {

	/*
	 * CRUD C: Create R: Read U: Update D: Delete
	 */

	// Tenant
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

	public void TenantUpdateAll(Tenant tenant) {
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
			System.out.println("\nInquilino removido com sucesso!");
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

	// Landlord
	public void landlordSave(Landlord landlord) {
		String sql = "INSERT INTO landlord (name, cpf, telephone, email) VALUES (?, ?, ?, ?)";

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
			pstm.setString(3, landlord.getTelephone());
			pstm.setString(4, landlord.getEmail());

			// Executar a query
			pstm.execute();
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

	public void landlordUpdateAll(Landlord landlord) {
		String sql = "UPDATE landlord SET name = ?, telephone = ?, email = ?" + "WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria conexão com o banco
			conn = PropertyConnections.createConnectionToMySQL();

			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			pstm.setString(1, landlord.getName());
			pstm.setString(2, landlord.getTelephone());
			pstm.setString(3, landlord.getEmail());
			pstm.setInt(4, landlord.getId());

			// Executar a query
			pstm.execute();
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

	public void landlordUpdateName(Landlord landlord) {
		String sql = "UPDATE landlord SET name = ?" + "WHERE id = ?";

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

	public void landlordUpdateTelephone(Landlord landlord) {
		String sql = "UPDATE landlord SET telephone = ?" + "WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria conexão com o banco
			conn = PropertyConnections.createConnectionToMySQL();

			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			pstm.setString(1, landlord.getTelephone());
			pstm.setInt(2, landlord.getId());

			// Executar a query
			pstm.execute();
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

	public void landlordUpdateEmail(Landlord landlord) {
		String sql = "UPDATE landlord SET email = ?" + "WHERE id = ?";

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

	public void landlordDeleteByID(int id) {
		String sql = "DELETE FROM landlord  WHERE id = ?";

		Connection conn = null;

		PreparedStatement pstm = null;

		try {
			conn = PropertyConnections.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			pstm.setInt(1, id);

			pstm.execute();
			System.out.println("\nProprietário removido com sucesso!");
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

	public List<Landlord> getLandlords() throws SQLException {
		String sql = "SELECT * FROM landlord";

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
				landlord.setId(rset.getInt("id"));

				// Recuperar o nome
				landlord.setName(rset.getString("name"));

				// Recuoerar o cpf
				landlord.setCpf(rset.getString("cpf"));

				// Recuoerar o telefone
				landlord.setTelephone(rset.getString("telephone"));

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

	public Landlord getLandlordById(int id) throws SQLException {
		String sql = "SELECT * FROM landlord WHERE id = ?";
		Landlord landlord = null;

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			conn = PropertyConnections.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			rset = pstm.executeQuery();

			if (rset.next()) {
				landlord = new Landlord();
				landlord.setId(rset.getInt("id"));
				landlord.setName(rset.getString("name"));
				landlord.setCpf(rset.getString("cpf"));
				landlord.setTelephone(rset.getString("telephone"));
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

	// Property
	public void propertySave(Property property) {
		String sql = "INSERT INTO property (address, rental_value, type, occupation, cpf_landlord) VALUES (?, ?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = PropertyConnections.createConnectionToMySQL();

			if (conn != null) {
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, property.getAddress());
				pstm.setDouble(2, property.getRentalValue());
				pstm.setString(3, property.getType().toString());
				pstm.setString(4, property.getOccupation().toString());
				pstm.setString(5, property.getLandlord().getCpf());

				pstm.execute();
				System.out.println("\nImóvel adicionado com sucesso!");
			} else {
				System.out.println("Erro: Conexão com o banco de dados falhou.");
			}
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

	public void propertyUpdateAll(Property property) {
		String sql = "UPDATE property SET address = ?, rental_value = ?, type = ?, occupation = ?" + "WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria conexão com o banco
			conn = PropertyConnections.createConnectionToMySQL();

			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			pstm.setString(1, property.getAddress());
			pstm.setDouble(2, property.getRentalValue());
			pstm.setString(3, property.getType().toString());
			pstm.setString(4, property.getOccupation().toString());
			pstm.setInt(5, property.getId());

			// Executar a query
			pstm.execute();
			System.out.println("\nImóvel atualizado com sucesso!");
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

	public void propertyUpdateAddress(Property property) {
		String sql = "UPDATE property SET address = ?" + "WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria conexão com o banco
			conn = PropertyConnections.createConnectionToMySQL();

			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			pstm.setString(1, property.getAddress());
			pstm.setInt(2, property.getId());

			// Executar a query
			pstm.execute();
			System.out.println("\nImóvel atualizado com sucesso!");
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

	public void propertyUpdateRentalValue(Property property) {
		String sql = "UPDATE property SET rental_value = ?" + "WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria conexão com o banco
			conn = PropertyConnections.createConnectionToMySQL();

			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			pstm.setDouble(1, property.getRentalValue());
			pstm.setInt(2, property.getId());

			// Executar a query
			pstm.execute();
			System.out.println("\nImóvel atualizado com sucesso!");
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

	public void propertyUpdateType(Property property) {
		String sql = "UPDATE property SET type = ?" + "WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria conexão com o banco
			conn = PropertyConnections.createConnectionToMySQL();

			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			pstm.setString(1, property.getType().toString());
			pstm.setInt(2, property.getId());

			// Executar a query
			pstm.execute();
			System.out.println("\nImóvel atualizado com sucesso!");
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

	public void propertyUpdateOccupation(Property property) {
		String sql = "UPDATE property SET occupation = ?" + "WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria conexão com o banco
			conn = PropertyConnections.createConnectionToMySQL();

			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			pstm.setString(1, property.getOccupation().toString());
			pstm.setInt(2, property.getId());

			// Executar a query
			pstm.execute();
			System.out.println("\nImóvel atualizado com sucesso!");
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

	public void propertyDeleteByID(int id) {
		String sql = "DELETE FROM property  WHERE id = ?";

		Connection conn = null;

		PreparedStatement pstm = null;

		try {
			conn = PropertyConnections.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			pstm.setInt(1, id);

			pstm.execute();
			System.out.println("\nImóvel removido com sucesso!");
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

	public List<Property> getProperty() throws SQLException {
		String sql = "SELECT * FROM property";

		List<Property> propertys = new ArrayList<Property>();

		Connection conn = null;
		PreparedStatement pstm = null;

		// Classe que vai recuperar os dados no banco ****SELECT****
		ResultSet rset = null;

		try {
			conn = PropertyConnections.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			while (rset.next()) {
				Property property = new Property();

				// Recuperar o id
				property.setId(rset.getInt("id"));

				// Recuperar o endereço
				property.setAddress(rset.getString("address"));

				// Recuperar o valor do aluguel
				property.setRentalValue(rset.getDouble("rental_value"));

				// Recuperar o tipo
				PropertyType propertyType = PropertyType.valueOf(rset.getString("type").toUpperCase());
				property.setType(propertyType);

				// Recuperar a ocupação
				PropertyOccupation propertyOccupation = PropertyOccupation
						.valueOf(rset.getString("occupation").toUpperCase());
				property.setOccupation(propertyOccupation);

				// Recuperar o cpf_Landlord e associar ao Landlord
				Landlord landlord = new Landlord();
				landlord.setCpf(rset.getString("cpf_landlord"));
				property.setLandlord(landlord);

				propertys.add(property);
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
		return propertys;
	}

	public Property getPropertyById(int id) throws SQLException {
		String sql = "SELECT * FROM property WHERE id = ?";
		Property property = null;

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			conn = PropertyConnections.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id); // Define o ID do proprietário
			rset = pstm.executeQuery();

			if (rset.next()) {
				property = new Property();
				property.setId(rset.getInt("id"));
				property.setAddress(rset.getString("address"));
				property.setRentalValue(rset.getDouble("rental_value"));
				PropertyType propertyType = PropertyType.valueOf(rset.getString("type").toUpperCase());
				property.setType(propertyType);
				PropertyOccupation propertyOccupation = PropertyOccupation
						.valueOf(rset.getString("occupation").toUpperCase());
				property.setOccupation(propertyOccupation);
				Landlord landlord = new Landlord();
				landlord.setCpf(rset.getString("cpf_landlord"));
				property.setLandlord(landlord);
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

		return property;
	}

	public List<Property> getPropertyByLandlordId(int id) throws SQLException {
		String sql = "SELECT p.id, p.address, p.rental_value, p.type, p.occupation, p.cpf_landlord FROM landlord l JOIN property p ON l.cpf = p.cpf_landlord WHERE l.id = ? ORDER BY l.id";
		List<Property> propertys = new ArrayList<Property>();

		Connection conn = null;
		PreparedStatement pstm = null;

		// Classe que vai recuperar os dados no banco ****SELECT****
		ResultSet rset = null;

		try {
			conn = PropertyConnections.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id); // Define o ID do proprietário
			rset = pstm.executeQuery();

			while (rset.next()) {
				Property property = null;
				property = new Property();
				property.setId(rset.getInt("id"));
				property.setAddress(rset.getString("address"));
				property.setRentalValue(rset.getDouble("rental_value"));
				PropertyType propertyType = PropertyType.valueOf(rset.getString("type").toUpperCase());
				property.setType(propertyType);
				PropertyOccupation propertyOccupation = PropertyOccupation
						.valueOf(rset.getString("occupation").toUpperCase());
				property.setOccupation(propertyOccupation);
				Landlord landlord = new Landlord();
				landlord.setCpf(rset.getString("cpf_landlord"));
				property.setLandlord(landlord);

				propertys.add(property);
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

		return propertys;
	}

	// Lease
	public void leaseSave(Lease lease) {
		String sql = "INSERT INTO lease (start_date, end_date, id_property, cpf_landlord, cpf_tenant) VALUES (?, ?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = PropertyConnections.createConnectionToMySQL();

			if (conn != null) {
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, lease.getStartDate());
				pstm.setString(2, lease.getEndDate());
				pstm.setInt(3, lease.getProperty().getId());
				pstm.setString(4, lease.getLandlord().getCpf());
				pstm.setString(5, lease.getTenant().getCpf());

				pstm.execute();
				System.out.println("\nContrato adicionado com sucesso!");
			} else {
				System.out.println("Erro: Conexão com o banco de dados falhou.");
			}
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

	public void LeaseUpdateAll(Lease lease) {
		String sql = "UPDATE lease SET start_date = ?, end_date = ?" + "WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria conexão com o banco
			conn = PropertyConnections.createConnectionToMySQL();

			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			pstm.setString(1, lease.getStartDate());
			pstm.setString(2, lease.getEndDate());
			pstm.setInt(3, lease.getId());

			// Executar a query
			pstm.execute();
			System.out.println("\nContrato atualizado com sucesso!");
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

	public void LeaseUpdateStartDate(Lease lease) {
		String sql = "UPDATE lease SET start_date = ? " + "WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria conexão com o banco
			conn = PropertyConnections.createConnectionToMySQL();

			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			pstm.setString(1, lease.getStartDate());
			pstm.setInt(2, lease.getId());

			// Executar a query
			pstm.execute();
			System.out.println("\nContrato atualizado com sucesso!");
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

	public void LeaseUpdateEndDate(Lease lease) {
		String sql = "UPDATE lease SET end_date = ?" + "WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria conexão com o banco
			conn = PropertyConnections.createConnectionToMySQL();

			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			pstm.setString(1, lease.getEndDate());
			pstm.setInt(2, lease.getId());

			// Executar a query
			pstm.execute();
			System.out.println("\nContrato atualizado com sucesso!");
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

	public void LeaseDeleteByID(int id) {
		String sql = "DELETE FROM lease  WHERE id = ?";

		Connection conn = null;

		PreparedStatement pstm = null;

		try {
			conn = PropertyConnections.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			pstm.setInt(1, id);

			pstm.execute();
			System.out.println("\nContrato removido com sucesso!");
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

	public List<Lease> getLease() throws SQLException {
		String sql = "SELECT * FROM lease";

		List<Lease> leases = new ArrayList<Lease>();

		Connection conn = null;
		PreparedStatement pstm = null;

		// Classe que vai recuperar os dados no banco ****SELECT****
		ResultSet rset = null;

		try {
			conn = PropertyConnections.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			while (rset.next()) {
				Lease lease = new Lease();

				// Recuperar o id
				lease.setId(rset.getInt("id"));

				// Recuperar a data inicio
				lease.setStartDate(rset.getString("start_date"));

				// Recuperar a data final
				lease.setEndDate(rset.getString("end_date"));

				// Recuperar o id_property
				Property property = new Property();
				property.setId(rset.getInt("id_property"));
				lease.setProperty(property);

				// Recuperar o cpf_Landlord
				Landlord landlord = new Landlord();
				landlord.setCpf(rset.getString("cpf_landlord"));
				lease.setLandlord(landlord);

				// Recuperar o cpf_tenant
				Tenant tenant = new Tenant();
				tenant.setCpf(rset.getString("cpf_tenant"));
				lease.setTenant(tenant);

				leases.add(lease);
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
		return leases;
	}

	public Lease getLeaseById(int id) throws SQLException {
		String sql = "SELECT * FROM lease WHERE id = ?";
		Lease lease = null;

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			conn = PropertyConnections.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id); // Define o ID do proprietário
			rset = pstm.executeQuery();

			if (rset.next()) {
				lease = new Lease();
				lease.setId(rset.getInt("id"));
				lease.setStartDate(rset.getString("start_date"));
				lease.setEndDate(rset.getString("end_date"));

				Property property = new Property();
				property.setId(rset.getInt("id_property"));
				lease.setProperty(property);

				Landlord landlord = new Landlord();
				landlord.setCpf(rset.getString("cpf_landlord"));
				lease.setLandlord(landlord);

				Tenant tenant = new Tenant();
				tenant.setCpf(rset.getString("cpf_tenant"));
				lease.setTenant(tenant);
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

		return lease;
	}

	// TenantLease
	public void tenantLeaseSave(TenantLease tenantLease) {
		String sql = "INSERT INTO tenant_lease (id_tenant, id_lease) VALUES (?, ?)";

		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = PropertyConnections.createConnectionToMySQL();

			if (conn != null) {
				pstm = conn.prepareStatement(sql);
				pstm.setInt(1, tenantLease.getTenant().getId());
				pstm.setInt(2, tenantLease.getLease().getId());

				pstm.execute();
				System.out.println("\nInquilino adicionado no Contrato com sucesso!");
			} else {
				System.out.println("Erro: Conexão com o banco de dados falhou.");
			}
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

	public void tenantLeaseUpdateAll(TenantLease tenantLease) {
		String sql = "UPDATE tenant_lease SET id_tenant = ?, id_lease = ?" + "WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria conexão com o banco
			conn = PropertyConnections.createConnectionToMySQL();

			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			pstm.setInt(1, tenantLease.getTenant().getId());
			pstm.setInt(2, tenantLease.getLease().getId());
			pstm.setInt(3, tenantLease.getId());

			// Executar a query
			pstm.execute();
			System.out.println("\nContrato atualizado com sucesso!");
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

	public void tenantLeaseUpdateIdTenant(TenantLease tenantLease) {
		String sql = "UPDATE tenant_lease SET id_tenant = ? " + "WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria conexão com o banco
			conn = PropertyConnections.createConnectionToMySQL();

			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			pstm.setInt(1, tenantLease.getTenant().getId());
			pstm.setInt(2, tenantLease.getId());

			// Executar a query
			pstm.execute();
			System.out.println("\nContrato atualizado com sucesso!");
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

	public void tenantLeaseUpdateIdLease(TenantLease tenantLease) {
		String sql = "UPDATE tenant_lease SET id_lease = ?" + "WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria conexão com o banco
			conn = PropertyConnections.createConnectionToMySQL();

			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			pstm.setInt(1, tenantLease.getLease().getId());
			pstm.setInt(2, tenantLease.getId());

			// Executar a query
			pstm.execute();
			System.out.println("\nContrato atualizado com sucesso!");
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

	public void tenantLeaseDeleteByID(int id) {
		String sql = "DELETE FROM tenant_lease  WHERE id = ?";

		Connection conn = null;

		PreparedStatement pstm = null;

		try {
			conn = PropertyConnections.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			pstm.setInt(1, id);

			pstm.execute();
			System.out.println("\nInquilino removido no Contrato  com sucesso!");
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

	public List<TenantLease> getTenantLease() throws SQLException {
		String sql = "SELECT * FROM tenant_lease";

		List<TenantLease> tenantLeases = new ArrayList<TenantLease>();

		Connection conn = null;
		PreparedStatement pstm = null;

		// Classe que vai recuperar os dados no banco ****SELECT****
		ResultSet rset = null;

		try {
			conn = PropertyConnections.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			while (rset.next()) {
				TenantLease tenantLease = new TenantLease();

				// Recuperar o cpf_tenant
				Tenant tenant = new Tenant();
				tenant.setId(rset.getInt("id"));
				tenantLease.setTenant(tenant);

				Lease lease = new Lease();
				lease.setId(rset.getInt("id"));
				tenantLease.setLease(lease);

				tenantLeases.add(tenantLease);

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
		return tenantLeases;
	}

}
