package db;

import db.events.KafkaEventsForOrder;
import dev.hyperapi.runtime.core.model.BaseEntity;
import dev.hyperapi.runtime.core.processor.annotations.RestService;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "orders")
@RestService(
    path = "/orders",
        repositoryPackage = "repositories",
    mapping =
        @RestService.Mapping(
            ignore = {"internalId"}
            //                ignoreNested = {"checkout.orders", "products.orders"}
            ),
//    disabledFor = {
//      RestService.HttpMethod.DELETE,
//      RestService.HttpMethod.PATCH,
//      RestService.HttpMethod.POST,
//      RestService.HttpMethod.PUT,
//      RestService.HttpMethod.GET
//    },
    events = @RestService.Events(onCreate = true, emitter = KafkaEventsForOrder.class))
public class Order extends BaseEntity {

  int orderNumber;
  int stockQuantity;
  String internalId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "checkout_id")
  private Checkout checkout;

  @ManyToMany
  @JoinTable(
      name = "order_product",
      joinColumns = @JoinColumn(name = "order_id"),
      inverseJoinColumns = @JoinColumn(name = "product_id"))
  private List<Product> products = new ArrayList<>();

}
