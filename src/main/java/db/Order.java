package db;

import db.enums.OrderStatus;
import com.eorghe.hyperapi.model.HyperEntity;
import com.eorghe.hyperapi.processor.annotations.Events;
import com.eorghe.hyperapi.processor.annotations.HyperResource;
import com.eorghe.hyperapi.processor.annotations.Mapping;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
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
@Table(name = "orders")
@HyperResource(
    path = "/orders",
    repositoryPackage = "repositories",
    mapping = @Mapping(ignore = {"internalId"}),
    events = @Events(onCreate = true)
)
public class Order extends HyperEntity {

  private String internalId;
  private int orderNumber;

  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  private BigDecimal subtotal;
  private BigDecimal tax;
  private BigDecimal total;

  private LocalDateTime placedAt;
  private LocalDateTime deliveredAt;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  private Address shippingAddress;

  @ManyToOne
  private Address billingAddress;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "checkout_id")
  private Checkout checkout;

  @OneToMany(mappedBy = "order", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
  private List<OrderItem> items;

  @ManyToOne
  @JoinColumn(name = "coupon_id")
  private Coupon coupon;

  private BigDecimal discountAmount;
}
