package db;

import events.ProductEvents;
import dev.hyperapi.runtime.core.model.HyperEntity;
import dev.hyperapi.runtime.core.processor.annotations.Events;
import dev.hyperapi.runtime.core.processor.annotations.HyperResource;
import dev.hyperapi.runtime.core.processor.annotations.Mapping;
import dev.hyperapi.runtime.core.processor.enums.Scope;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "products")
@HyperResource(
    path = "/products",
    dto = "ProductDto",
    repositoryPackage = "repositories",
    scope = Scope.REQUEST,
    mapping = @Mapping(ignoreNested = {"orderItems.order.checkout.orders"}),
    events = @Events(onCreate = true, onDelete = true, onPatch = true, onUpdate = true, emitter = ProductEvents.class)
)
public class Product extends HyperEntity {

  private String name;
  private String description;
  private String sku;
  private BigDecimal price;
  private int stock;

  @JsonbTransient
  @ManyToMany(mappedBy = "product")
  private List<OrderItem> orderItems = new java.util.ArrayList<>();

}
