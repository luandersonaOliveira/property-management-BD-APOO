package containers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.PropertyConnections;
import entity.Landlord;
import entity.PropertyCommercial;
import enums.PropertyOccupation;
import enums.PropertyType;
import enums.TheTypeOfBusiness;
import interfaces.IPropertyCommercial;

public class PropertyCommercialRepository implements IPropertyCommercial {

	@Override
	public void save(PropertyCommercial commercial) {
		String sql = "INSERT INTO imovel_comercial (cpfProprietario, endereco, valorDoAluguel, tipo, status, numeroDeQuartos, tiposNegocio) VALUES (?, ?, ?, ?, ?, ?)";

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
				pstm.setString(7, commercial.getBusiness().toString());

				pstm.execute();
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
	public void updateAll(PropertyCommercial commercial) {
		String sql = "UPDATE imovel_comercial SET endereco = ?, valorDoAluguel = ?, tipo = ?, status = ?, numeroDeQuartos = ?, tipoNegocio = ?"
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
			pstm.setString(6, commercial.getBusiness().toString());
			pstm.setInt(7, commercial.getId());

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
	public void updateAddress(PropertyCommercial commercial) {
		String sql = "UPDATE imovel_comercial SET endereco = ?" + "WHERE id = ?";

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
	public void updateRentalValue(PropertyCommercial commercial) {
		String sql = "UPDATE imovel_comercial SET valorDoAluguel = ?" + "WHERE id = ?";

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
	public void updateType(PropertyCommercial commercial) {
		String sql = "UPDATE imovel_comercial SET tipo = ?" + "WHERE id = ?";

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
	public void updateOccupation(PropertyCommercial commercial) {
		String sql = "UPDATE imovel_comercial SET status = ?" + "WHERE id = ?";

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
	public void updateBusiness(PropertyCommercial commercial) {
		String sql = "UPDATE imovel_comercial SET tiposNegocio = ?" + "WHERE id = ?";

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
		String sql = "DELETE FROM imovel_comercial WHERE id = ?";

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
	public List<PropertyCommercial> getProperty() throws SQLException {
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
	
	@Override
	public PropertyCommercial getPropertyById(int id) throws SQLException {
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
}
