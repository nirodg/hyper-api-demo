package db.repositories;


import db.Cart;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CartRepository implements PanacheRepositoryBase<Cart, Long> {

  public Cart getCartForUser(Long userId) {
    return find("userId", userId).firstResult();
  }
}
