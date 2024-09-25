package Interface;
// Interface Repositório de Inquilinos

import java.util.ArrayList;

import Entity.Tenant;

public interface ITenantRepository {
    public void addTenant(Tenant tenant);

    public void removeTenant(int id);

    public void changeTenant(int id);

    public ArrayList<Tenant> listTenant();

    public Tenant searchTenant(int id);
}
