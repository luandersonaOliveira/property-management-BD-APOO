package containers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.PropertyConnections;
import entity.Landlord;
import entity.PropertyResidential;
import enums.PropertyOccupation;
import enums.PropertyType;
import interfaces.IPropertyResidential;

public class PropertyResidentialRepository implements IPropertyResidential {

	@Override
	public void save(PropertyResidential residential) {
		String sql = "INSERT INTO imovel_residencial (cpfProprietario, endereco, valorDoAluguel, tipo, status, numeroDeQuartos, areaLazer) VALUES (?, ?, ?, ?, ?, ?)";

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
				pstm.setBoolean(7, residential.isTheLeisureArea());

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
	public void updateAll(PropertyResidential residential) {
		String sql = "UPDATE imovel_residencial SET endereco = ?, valorDoAluguel = ?, tipo = ?, status = ?, numeroDeQuartos = ?, areaLazer = ?"
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
			pstm.setBoolean(6, residential.isTheLeisureArea());
			pstm.setInt(7, residential.getId());

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
	public void updateAddress(PropertyResidential residential) {
		String sql = "UPDATE imovel_residencial SET endereco = ?" + "WHERE id = ?";

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
	public void updateRentalValue(PropertyResidential residential) {
		String sql = "UPDATE imovel_residencial SET valorDoAluguel = ?" + "WHERE id = ?";

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
	public void updateType(PropertyResidential residential) {
		String sql = "UPDATE imovel_residencial SET tipo = ?" + "WHERE id = ?";

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
	public void updateOccupation(PropertyResidential residential) {
		String sql = "UPDATE imovel_residencial SET status = ?" + "WHERE id = ?";

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
		String sql = "DELETE FROM imovel_residencial WHERE id = ?";

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
	public List<PropertyResidential> getProperty() throws SQLException {
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

	@Override
	public PropertyResidential getPropertyById(int id) throws SQLException {
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

}
