package db.repositories;


import db.Coupon;
import db.OrderItem;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CouponRepository implements PanacheRepositoryBase<Coupon, Long> {

  public Coupon findByCouponCode(String couponCode) {
    return find("couponCode", couponCode).firstResult();
  }
}
