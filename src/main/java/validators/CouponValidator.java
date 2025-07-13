package validators;

import db.Coupon;
import db.User;
import jakarta.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@ApplicationScoped
public class CouponValidator {

  public void validateCoupon(Coupon coupon, User user) {
    if (!coupon.isActive()) throw new IllegalArgumentException("Coupon is not active");
    if (coupon.getValidUntil().isBefore(LocalDateTime.now())) throw new IllegalArgumentException("Coupon expired");
    if (coupon.getTimesUsed() >= coupon.getUsageLimit()) throw new IllegalArgumentException("Coupon usage limit exceeded");
    // Optional: validate per-user usage
  }

  public BigDecimal applyDiscount(Coupon coupon, BigDecimal subtotal) {
    return switch (coupon.getDiscountType()) {
      case FIXED -> coupon.getDiscountValue();
      case PERCENTAGE -> subtotal.multiply(coupon.getDiscountValue());
    };
  }
}
