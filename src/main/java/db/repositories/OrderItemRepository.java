package db.repositories;


import db.OrderItem;
import db.User;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderItemRepository implements PanacheRepositoryBase<OrderItem, Long> {

}
