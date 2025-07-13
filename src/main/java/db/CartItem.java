package db;

import dev.hyperapi.runtime.core.model.HyperEntity;
import dev.hyperapi.runtime.core.processor.annotations.Events;
import dev.hyperapi.runtime.core.processor.annotations.HyperResource;
import dev.hyperapi.runtime.core.processor.annotations.Mapping;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "cart_items")
@HyperResource(
    path = "/cart-items",
    repositoryPackage = "repositories",
    events = @Events(onCreate = true, onUpdate = true, onDelete = true),
    mapping = @Mapping(ignoreNested = {"cart.user.orders", "product.orderItems"})
)
public class CartItem extends HyperEntity {

  @ManyToOne
  private Cart cart;

  @ManyToOne
  private Product product;

  private int quantity;
  private BigDecimal unitPrice;

  public BigDecimal getTotal() {
    return unitPrice.multiply(BigDecimal.valueOf(quantity));
  }
}
