package services;

import db.Cart;
import db.repositories.CartRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ExtendedCartService {

  @Inject
  private CartRepository cartRepository;

  @Inject
  private db.mapper.CartMapper mapper;

  public db.dto.CartDTO getCartForUser(Long userId) {
    Cart cart = cartRepository.getCartForUser(userId);
    if (cart == null) {
      throw new IllegalArgumentException("Cart not found for user ID: " + userId);
    }
    return mapper.toDto(cart);
  }

  public void clearCart(Long userId) {
    Cart cart = cartRepository.getCartForUser(userId);
    if (cart == null) {
      throw new IllegalArgumentException("Cart not found for user ID: " + userId);
    }
    cart.getItems().clear();
    cartRepository.persist(cart);
  }
}
