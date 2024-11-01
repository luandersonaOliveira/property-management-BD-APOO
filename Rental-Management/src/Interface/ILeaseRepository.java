package Interface;
// Interface Repositório de Locação (Contratos)

import containers.StackRepository;
import entity.Lease;

public interface ILeaseRepository {
	public void addLease(Lease lease);

	public void removeLease();

	public void changeLease(int id);

	public StackRepository<Lease> listLease();

	public Lease searchLease(int id);
}
