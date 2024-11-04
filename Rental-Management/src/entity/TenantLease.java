package entity;

public class TenantLease {
	// ATTRIBUTES

	private int id;
	private Tenant Tenant;
	private Lease Lease;

	// CONSTRUCTOR

	public TenantLease() {

	}

	public TenantLease(Tenant tenant, Lease lease) {
		this.Tenant = tenant;
		this.Lease = lease;
	}
	// METODOS ESPECIAS

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Tenant getTenant() {
		return Tenant;
	}

	public void setTenant(Tenant idTenant) {
		this.Tenant = idTenant;
	}

	public Lease getLease() {
		return Lease;
	}

	public void setLease(Lease idLease) {
		this.Lease = idLease;
	}

}
