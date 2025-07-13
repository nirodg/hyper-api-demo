package db.repositories;


import db.Cart;
import db.CartItem;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CartItemRepository implements PanacheRepositoryBase<CartItem, Long> {


}
