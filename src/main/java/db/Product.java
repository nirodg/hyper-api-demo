package db;

import events.ProductEvents;
import com.eorghe.hyperapi.model.HyperEntity;
import com.eorghe.hyperapi.processor.annotations.Events;
import com.eorghe.hyperapi.processor.annotations.HyperResource;
import com.eorghe.hyperapi.processor.annotations.Mapping;
import com.eorghe.hyperapi.processor.enums.Scope;
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
