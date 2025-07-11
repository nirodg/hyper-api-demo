package db.repositories;

import db.Checkout;
import db.Product;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CheckoutRepository implements PanacheRepositoryBase<Checkout, Object> {
}
