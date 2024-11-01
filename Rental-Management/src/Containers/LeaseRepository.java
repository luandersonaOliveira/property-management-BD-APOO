package containers;
// Repositório da Locação (Contrato)

import java.util.ArrayList;

import Interface.ILeaseRepository;
import entity.Lease;

public class LeaseRepository extends StackRepository<Lease> implements ILeaseRepository {
	// public ArrayList<Lease> leases = new ArrayList<>();
	public StackRepository<Lease> leases = new StackRepository<>(new ArrayList<>());

	public LeaseRepository(ArrayList<Lease> stack) {
		super(stack);
	}

	@Override
	public void addLease(Lease lease) {
		leases.push(lease);
	}

	@Override
	public void removeLease() {
		leases.pop();
	}

	@Override
	public void changeLease(int id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'changeLease'");
	}

	@Override
	public StackRepository<Lease> listLease() {
		return leases;
	}

	@Override
	public Lease searchLease(int id) {
		if (id >= 0 && id < leases.size()) {
			return leases.get(id);
		}
		return null;
	}
}
