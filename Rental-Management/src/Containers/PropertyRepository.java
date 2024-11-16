package containers;
// Repositório do Imovel

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import enums.PropertyOccupation;
import enums.PropertyType;
import interfaces.IPropertyRepository;
import connection.PropertyConnections;
import entity.Landlord;
import entity.Property;

public class PropertyRepository implements IPropertyRepository {

	@Override
	public void save(Property property) {
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
				pstm.setString(5, property.getLandlord().getCpf()); // Garantir que o Landlord está associado

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

	@Override
	public void updateAll(Property property) {
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

	@Override
	public void updateAddress(Property property) {
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

	@Override
	public void updateRentalValue(Property property) {
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

	@Override
	public void updateType(Property property) {
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

	@Override
	public void updateOccupation(Property property) {
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

	@Override
	public void deleteByID(int id) {
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

	@Override
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

	@Override
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
	
	@Override
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
			
			while(rset.next()) {
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

}
