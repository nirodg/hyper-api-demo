package db.repositories;

import db.Address;
import db.Checkout;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AddressRepository implements PanacheRepositoryBase<Address, Long> {
}
