package db;

import dev.hyperapi.runtime.core.model.HyperEntity;
import dev.hyperapi.runtime.core.processor.annotations.Events;
import dev.hyperapi.runtime.core.processor.annotations.HyperResource;
import dev.hyperapi.runtime.core.processor.annotations.Pageable;
import dev.hyperapi.runtime.core.processor.enums.HttpMethod;
import dev.hyperapi.runtime.core.processor.enums.Scope;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "checkouts")
@HyperResource(
    path = "/checkouts",
    dto = "CheckoutDto",
    repositoryPackage = "repositories",
    scope = Scope.REQUEST,
    pageable = @Pageable(limit = 33, maxLimit = 500),
    disabledFor = {HttpMethod.GET},
    events = @Events(onCreate = true)
)
public class Checkout extends HyperEntity {

  private String paymentMethod;
  private String transactionId;
  private LocalDateTime paymentDate;
  private boolean paid;

  @OneToMany(mappedBy = "checkout", cascade = CascadeType.ALL, orphanRemoval = true)
  List<Order> orders = new ArrayList<>();

}
