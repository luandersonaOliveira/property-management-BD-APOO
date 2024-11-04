package containers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Interface.ITenantLeaseRepository;
import connection.PropertyConnections;
import entity.Lease;
import entity.Tenant;
import entity.TenantLease;

public class TenantLeaseRepository implements ITenantLeaseRepository {

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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
