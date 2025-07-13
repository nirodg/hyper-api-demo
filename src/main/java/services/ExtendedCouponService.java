package services;

import db.Coupon;
import db.repositories.CouponRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ExtendedCouponService {

  @Inject
  private CouponRepository repository;

  @Inject
  private db.mapper.CouponMapper mapper;

  public db.dto.CouponDTO findByCode(String couponCode) {
    Coupon coupon = repository.findByCouponCode(couponCode);
    if (coupon == null) {
      throw new IllegalArgumentException("Coupon not found: " + couponCode);
    }
    return mapper.toDto(coupon);
  }
}
