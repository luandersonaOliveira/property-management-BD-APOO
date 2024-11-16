package containers;
// Repositório da Locação (Contrato)

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import interfaces.ILeaseRepository;
import connection.PropertyConnections;
import entity.Landlord;
import entity.Lease;
import entity.Property;
import entity.Tenant;

public class LeaseRepository implements ILeaseRepository {

	@Override
	public void save(Lease lease) {
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

	@Override
	public void updateAll(Lease lease) {
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

	@Override
	public void updateStartDate(Lease lease) {
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

	@Override
	public void updateEndDate(Lease lease) {
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

	@Override
	public void deleteByID(int id) {
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

	@Override
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

	@Override
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

}
