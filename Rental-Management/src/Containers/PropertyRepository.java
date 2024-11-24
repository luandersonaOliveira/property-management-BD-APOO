package containers;
// Repositório do Imovel

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.PropertyConnections;
import entity.Landlord;
import entity.Property;
import entity.PropertyCommercial;
import entity.PropertyResidential;
import enums.PropertyOccupation;
import enums.PropertyType;
import enums.TheTypeOfBusiness;

public class PropertyRepository {
	// ATTRIBUTES

	private static final PropertyCommercialRepository commercialDAO = new PropertyCommercialRepository();
	private static final PropertyResidentialRepository residentialDAO = new PropertyResidentialRepository();

	// CUSTOM METHODS

	public void saveCommercial(PropertyCommercial commercial) {
		String sql = "INSERT INTO imovel (cpfProprietario, endereco, valorDoAluguel, tipo, status, numeroDeQuartos) VALUES (?, ?, ?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = PropertyConnections.createConnectionToMySQL();

			if (conn != null) {
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, commercial.getLandlord().getCpf());
				pstm.setString(2, commercial.getAddress());
				pstm.setDouble(3, commercial.getRentalValue());
				pstm.setString(4, commercial.getType().toString());
				pstm.setString(5, commercial.getOccupation().toString());
				pstm.setInt(6, commercial.getNumberOfRooms());

				pstm.execute();
				commercialDAO.save(commercial);
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

	public void saveResidential(PropertyResidential residential) {
		String sql = "INSERT INTO imovel (cpfProprietario, endereco, valorDoAluguel, tipo, status, numeroDeQuartos) VALUES (?, ?, ?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = PropertyConnections.createConnectionToMySQL();

			if (conn != null) {
				pstm = conn.prepareStatement(sql);
				pstm.setString(1, residential.getLandlord().getCpf());
				pstm.setString(2, residential.getAddress());
				pstm.setDouble(3, residential.getRentalValue());
				pstm.setString(4, residential.getType().toString());
				pstm.setString(5, residential.getOccupation().toString());
				pstm.setInt(6, residential.getNumberOfRooms());

				pstm.execute();
				residentialDAO.save(residential);
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

	public void updateAllCommercial(PropertyCommercial commercial) {
		String sql = "UPDATE imovel SET endereco = ?, valorDoAluguel = ?, tipo = ?, status = ?, numeroDeQuartos = ?"
				+ "WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria conexão com o banco
			conn = PropertyConnections.createConnectionToMySQL();

			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			pstm.setString(1, commercial.getAddress());
			pstm.setDouble(2, commercial.getRentalValue());
			pstm.setString(3, commercial.getType().toString());
			pstm.setString(4, commercial.getOccupation().toString());
			pstm.setInt(5, commercial.getNumberOfRooms());
			pstm.setInt(6, commercial.getId());

			// Executar a query
			pstm.execute();
			commercialDAO.updateAll(commercial);
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

	public void updateAllResidential(PropertyResidential residential) {
		String sql = "UPDATE imovel SET endereco = ?, valorDoAluguel = ?, tipo = ?, status = ?, numeroDeQuartos = ?"
				+ "WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria conexão com o banco
			conn = PropertyConnections.createConnectionToMySQL();

			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			pstm.setString(1, residential.getAddress());
			pstm.setDouble(2, residential.getRentalValue());
			pstm.setString(3, residential.getType().toString());
			pstm.setString(4, residential.getOccupation().toString());
			pstm.setInt(5, residential.getNumberOfRooms());
			pstm.setInt(6, residential.getId());

			// Executar a query
			pstm.execute();
			residentialDAO.updateAll(residential);
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

	public void updateAddressCommercial(PropertyCommercial commercial) {
		String sql = "UPDATE imovel SET endereco = ?" + "WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria conexão com o banco
			conn = PropertyConnections.createConnectionToMySQL();

			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			pstm.setString(1, commercial.getAddress());
			pstm.setInt(2, commercial.getId());

			// Executar a query
			pstm.execute();
			commercialDAO.updateAddress(commercial);
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

	public void updateAddressResidential(PropertyResidential residential) {
		String sql = "UPDATE imovel SET endereco = ?" + "WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria conexão com o banco
			conn = PropertyConnections.createConnectionToMySQL();

			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			pstm.setString(1, residential.getAddress());
			pstm.setInt(2, residential.getId());

			// Executar a query
			pstm.execute();
			residentialDAO.updateAddress(residential);
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

	public void updateRentalValueCommercial(PropertyCommercial commercial) {
		String sql = "UPDATE imovel SET valorDoAluguel = ?" + "WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria conexão com o banco
			conn = PropertyConnections.createConnectionToMySQL();

			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			pstm.setDouble(1, commercial.getRentalValue());
			pstm.setInt(2, commercial.getId());

			// Executar a query
			pstm.execute();
			commercialDAO.updateRentalValue(commercial);
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

	public void updateRentalValueResidential(PropertyResidential residential) {
		String sql = "UPDATE imovel SET valorDoAluguel = ?" + "WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria conexão com o banco
			conn = PropertyConnections.createConnectionToMySQL();

			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			pstm.setDouble(1, residential.getRentalValue());
			pstm.setInt(2, residential.getId());

			// Executar a query
			pstm.execute();
			residentialDAO.updateRentalValue(residential);
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

	public void updateTypeCommercial(PropertyCommercial commercial) {
		String sql = "UPDATE imovel SET tipo = ?" + "WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria conexão com o banco
			conn = PropertyConnections.createConnectionToMySQL();

			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			pstm.setString(1, commercial.getType().toString());
			pstm.setInt(2, commercial.getId());

			// Executar a query
			pstm.execute();
			commercialDAO.updateType(commercial);
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

	public void updateTypeResidential(PropertyResidential residential) {
		String sql = "UPDATE imovel SET tipo = ?" + "WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria conexão com o banco
			conn = PropertyConnections.createConnectionToMySQL();

			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			pstm.setString(1, residential.getType().toString());
			pstm.setInt(2, residential.getId());

			// Executar a query
			pstm.execute();
			residentialDAO.updateType(residential);
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

	public void updateOccupationCommercial(PropertyCommercial commercial) {
		String sql = "UPDATE imovel SET status = ?" + "WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria conexão com o banco
			conn = PropertyConnections.createConnectionToMySQL();

			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			pstm.setString(1, commercial.getOccupation().toString());
			pstm.setInt(2, commercial.getId());

			// Executar a query
			pstm.execute();
			commercialDAO.updateOccupation(commercial);
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

	public void updateOccupationResidential(PropertyResidential residential) {
		String sql = "UPDATE imovel SET status = ?" + "WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria conexão com o banco
			conn = PropertyConnections.createConnectionToMySQL();

			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			pstm.setString(1, residential.getOccupation().toString());
			pstm.setInt(2, residential.getId());

			// Executar a query
			pstm.execute();
			residentialDAO.updateOccupation(residential);
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

	public void updateBusiness(PropertyCommercial commercial) {
		String sql = "UPDATE imovel_commercial SET tiposNegocio = ?" + "WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria conexão com o banco
			conn = PropertyConnections.createConnectionToMySQL();

			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			pstm.setString(1, commercial.getBusiness().toString());
			pstm.setInt(2, commercial.getId());

			// Executar a query
			pstm.execute();
			commercialDAO.updateBusiness(commercial);
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

	public void updateArea(PropertyResidential residential) {
		String sql = "UPDATE imovel_residencial SET areaLazer = ?" + "WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Cria conexão com o banco
			conn = PropertyConnections.createConnectionToMySQL();

			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			pstm.setBoolean(1, residential.isTheLeisureArea());
			pstm.setInt(2, residential.getId());

			// Executar a query
			pstm.execute();
			residentialDAO.updateArea(residential);
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

	public void deleteByIDCommercial(int id) {
		String sql = "DELETE FROM imovel  WHERE id = ?";

		Connection conn = null;

		PreparedStatement pstm = null;

		try {
			conn = PropertyConnections.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			pstm.setInt(1, id);

			pstm.execute();
			commercialDAO.deleteByID(id);
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

	public void deleteByIDResidential(int id) {
		String sql = "DELETE FROM imovel WHERE id = ?";

		Connection conn = null;

		PreparedStatement pstm = null;

		try {
			conn = PropertyConnections.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			pstm.setInt(1, id);

			pstm.execute();
			residentialDAO.deleteByID(id);
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

	public List<PropertyCommercial> getPropertyCommercial() throws SQLException {
		String sql = "SELECT * FROM imovel_comercial";

		List<PropertyCommercial> commercials = new ArrayList<PropertyCommercial>();

		Connection conn = null;
		PreparedStatement pstm = null;

		// Classe que vai recuperar os dados no banco ****SELECT****
		ResultSet rset = null;

		try {
			conn = PropertyConnections.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			while (rset.next()) {
				PropertyCommercial commercial = new PropertyCommercial();

				// Recuperar o id
				commercial.setId(rset.getInt("id"));

				// Recuperar o endereço
				commercial.setAddress(rset.getString("address"));

				// Recuperar o valor do aluguel
				commercial.setRentalValue(rset.getDouble("rental_value"));

				// Recuperar o tipo
				PropertyType propertyType = PropertyType.valueOf(rset.getString("type").toUpperCase());
				commercial.setType(propertyType);

				// Recuperar a ocupação
				PropertyOccupation propertyOccupation = PropertyOccupation
						.valueOf(rset.getString("occupation").toUpperCase());
				commercial.setOccupation(propertyOccupation);

				TheTypeOfBusiness business = TheTypeOfBusiness.valueOf(rset.getString("tiposNegocio").toUpperCase());
				commercial.setBusiness(business);

				// Recuperar o cpf_Landlord e associar ao Landlord
				Landlord landlord = new Landlord();
				landlord.setCpf(rset.getString("cpfProprietario"));
				commercial.setLandlord(landlord);

				commercials.add(commercial);
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
		return commercials;
	}

	public List<PropertyResidential> getPropertyResidential() throws SQLException {
		String sql = "SELECT * FROM imovel_residencial";

		List<PropertyResidential> residentials = new ArrayList<PropertyResidential>();

		Connection conn = null;
		PreparedStatement pstm = null;

		// Classe que vai recuperar os dados no banco ****SELECT****
		ResultSet rset = null;

		try {
			conn = PropertyConnections.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			while (rset.next()) {
				PropertyResidential residential = new PropertyResidential();

				// Recuperar o id
				residential.setId(rset.getInt("id"));

				// Recuperar o endereço
				residential.setAddress(rset.getString("address"));

				// Recuperar o valor do aluguel
				residential.setRentalValue(rset.getDouble("rental_value"));

				// Recuperar o tipo
				PropertyType propertyType = PropertyType.valueOf(rset.getString("type").toUpperCase());
				residential.setType(propertyType);

				// Recuperar a ocupação
				PropertyOccupation propertyOccupation = PropertyOccupation
						.valueOf(rset.getString("occupation").toUpperCase());
				residential.setOccupation(propertyOccupation);

				residential.setTheLeisureArea(rset.getBoolean("areaLazer"));

				// Recuperar o cpf_Landlord e associar ao Landlord
				Landlord landlord = new Landlord();
				landlord.setCpf(rset.getString("cpfProprietario"));
				residential.setLandlord(landlord);

				residentials.add(residential);
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
		return residentials;
	}

	public PropertyCommercial getPropertyByIdCommercial(int id) throws SQLException {
		String sql = "SELECT * FROM imovel_comercial WHERE id = ?";
		PropertyCommercial commercial = null;

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			conn = PropertyConnections.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id); // Define o ID do proprietário
			rset = pstm.executeQuery();

			if (rset.next()) {
				commercial = new PropertyCommercial();
				commercial.setId(rset.getInt("id"));
				commercial.setAddress(rset.getString("address"));
				commercial.setRentalValue(rset.getDouble("rental_value"));

				PropertyType propertyType = PropertyType.valueOf(rset.getString("tipo").toUpperCase());
				commercial.setType(propertyType);

				PropertyOccupation propertyOccupation = PropertyOccupation
						.valueOf(rset.getString("occupation").toUpperCase());
				commercial.setOccupation(propertyOccupation);

				TheTypeOfBusiness business = TheTypeOfBusiness.valueOf(rset.getString("tiposNegocio").toUpperCase());
				commercial.setBusiness(business);

				Landlord landlord = new Landlord();
				landlord.setCpf(rset.getString("cpfProprietario"));
				commercial.setLandlord(landlord);
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

		return commercial;
	}

	public PropertyResidential getPropertyByIdResidential(int id) throws SQLException {
		String sql = "SELECT * FROM imovel_residencial WHERE id = ?";
		PropertyResidential residential = null;

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;

		try {
			conn = PropertyConnections.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id); // Define o ID do proprietário
			rset = pstm.executeQuery();

			if (rset.next()) {
				residential = new PropertyResidential();
				residential.setId(rset.getInt("id"));
				residential.setAddress(rset.getString("address"));
				residential.setRentalValue(rset.getDouble("rental_value"));

				PropertyType propertyType = PropertyType.valueOf(rset.getString("tipo").toUpperCase());
				residential.setType(propertyType);

				PropertyOccupation propertyOccupation = PropertyOccupation
						.valueOf(rset.getString("occupation").toUpperCase());
				residential.setOccupation(propertyOccupation);

				residential.setTheLeisureArea(rset.getBoolean("areaLazer"));

				Landlord landlord = new Landlord();
				landlord.setCpf(rset.getString("cpfProprietario"));
				residential.setLandlord(landlord);
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

		return residential;
	}

	public List<Property> getPropertyByLandlordId(int id) throws SQLException {
		String sql = "SELECT i.id, i.address, i.rental_value, i.type, i.occupation, i.cpf_landlord FROM proprietario p JOIN property i ON p.cpf = i.cpf_proprietario WHERE p.id = ? ORDER BY p.id";
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
				PropertyType propertyType = PropertyType.valueOf(rset.getString("tipo").toUpperCase());
				property.setType(propertyType);
				PropertyOccupation propertyOccupation = PropertyOccupation
						.valueOf(rset.getString("status").toUpperCase());
				property.setOccupation(propertyOccupation);
				Landlord landlord = new Landlord();
				landlord.setCpf(rset.getString("cpfProprietario"));
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
