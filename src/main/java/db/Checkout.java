package db;

import com.eorghe.hyperapi.model.HyperEntity;
import com.eorghe.hyperapi.processor.annotations.Events;
import com.eorghe.hyperapi.processor.annotations.HyperResource;
import com.eorghe.hyperapi.processor.annotations.Pageable;
import com.eorghe.hyperapi.processor.enums.HttpMethod;
import com.eorghe.hyperapi.processor.enums.Scope;
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
