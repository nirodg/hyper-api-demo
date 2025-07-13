package db;

import dev.hyperapi.runtime.core.model.HyperEntity;
import dev.hyperapi.runtime.core.processor.annotations.Events;
import dev.hyperapi.runtime.core.processor.annotations.HyperResource;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "order_items")
@HyperResource(
    path = "/orderItems",
    repositoryPackage = "repositories",
    events = @Events(onCreate = true)
)
public class OrderItem extends HyperEntity {

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  @ManyToOne
  @JoinColumn(name = "order_id")
  private Order order;

  private int quantity;
  private BigDecimal unitPrice;
  private BigDecimal total;
}
