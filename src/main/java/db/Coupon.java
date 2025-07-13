package db;

import db.enums.DiscountType;
import dev.hyperapi.runtime.core.model.HyperEntity;
import dev.hyperapi.runtime.core.processor.annotations.Events;
import dev.hyperapi.runtime.core.processor.annotations.HyperResource;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "coupons")
@HyperResource(
    path = "/coupons",
    repositoryPackage = "repositories",
    events = @Events(onCreate = true, onUpdate = true, onDelete = true)
)
public class Coupon extends HyperEntity {

  private String code;
  private String description;

  @Enumerated(EnumType.STRING)
  private DiscountType discountType;

  private BigDecimal discountValue; // e.g., 10.00 or 0.15 for 15%

  private LocalDateTime validFrom;
  private LocalDateTime validUntil;
  private boolean active;
  private int usageLimit;

  private int timesUsed;

  @ManyToOne
  @JoinColumn(name = "created_by_user")
  private User createdByUser;

  @OneToMany(mappedBy = "coupon")
  private List<Order> orders = new ArrayList<>();
}