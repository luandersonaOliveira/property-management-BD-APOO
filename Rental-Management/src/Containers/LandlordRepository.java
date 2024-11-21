package containers;
// Repositório do Proprietário

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.PropertyConnections;
import entity.Landlord;
import interfaces.ILandlordRepository;

public class LandlordRepository implements ILandlordRepository {

	@Override
	public void save(Landlord landlord) {
		String sql = "INSERT INTO proprietario (nome, cpf, telefone, email) VALUES (?, ?, ?, ?)";
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

	@Override
	public void updateAll(Landlord landlord) {
		String sql = "UPDATE proprietario SET nome = ?, telefone = ?, email = ?" + "WHERE id = ?";

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

	@Override
	public void updateName(Landlord landlord) {
		String sql = "UPDATE proprietario SET nome = ?" + "WHERE id = ?";

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

	@Override
	public void updateTelephone(Landlord landlord) {
		String sql = "UPDATE proprietario SET telefone = ?" + "WHERE id = ?";

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

	@Override
	public void updateEmail(Landlord landlord) {
		String sql = "UPDATE proprietario SET email = ?" + "WHERE id = ?";

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

	@Override
	public void deleteByID(int id) {
		String sql = "DELETE FROM proprietario WHERE id = ?";

		Connection conn = null;

		PreparedStatement pstm = null;

		try {
			conn = PropertyConnections.createConnectionToMySQL();
			pstm = (PreparedStatement) conn.prepareStatement(sql);

			pstm.setInt(1, id);

			pstm.execute();
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
				landlord.setId(rset.getInt("id"));

				// Recuperar o nome
				landlord.setName(rset.getString("nome"));

				// Recuoerar o cpf
				landlord.setCpf(rset.getString("cpf"));

				// Recuoerar o telefone
				landlord.setTelephone(rset.getString("telefone"));

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
				landlord.setId(rset.getInt("id"));
				landlord.setName(rset.getString("nome"));
				landlord.setCpf(rset.getString("cpf"));
				landlord.setTelephone(rset.getString("telefone"));
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

}
