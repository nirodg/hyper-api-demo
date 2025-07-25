package services;

import db.Coupon;
import db.Order;
import db.OrderItem;
import db.User;
import db.dto.CartItemDTO;
import db.enums.OrderStatus;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import validators.CouponValidator;

/**
 * OrderWorkflowService handles the full order creation lifecycle: - Retrieves the user's cart -
 * Applies optional coupons - Calculates totals and persists the order with items - Clears the
 * user's cart
 * <p>
 * This service encapsulates real business logic, separate from basic CRUD services.
 */
@ApplicationScoped
public class OrderWorkflowService {

  @Inject
  ExtendedCartService extendedCartService;
  @Inject
  ExtendedCouponService extendedCouponService;

  @Inject
  db.service.CouponService couponService;
  @Inject
  db.service.OrderService orderService;
  @Inject
  db.service.UserService userService;
  @Inject
  db.service.OrderItemService orderItemService;

  @Inject
  db.mapper.OrderMapper orderMapper;
  @Inject
  db.mapper.UserMapper userMapper;
  @Inject
  db.mapper.CouponMapper couponMapper;
  @Inject
  db.mapper.OrderItemMapper orderItemMapper;
  @Inject
  db.mapper.ProductMapper productMapper;
  @Inject
  db.mapper.CartItemMapper cartItemMapper;

  @Inject
  CouponValidator couponValidator;

  @Transactional
  public db.dto.OrderDTO createOrder(Long userId, String couponCode) {
    db.dto.UserDTO userDto = userService.findById(userId);
    User user = userMapper.toEntity(userDto);

    db.dto.CartDTO cartDto = extendedCartService.getCartForUser(userId);
    List<db.dto.CartItemDTO> cartItems = cartDto.getItems();

    if (cartItems.isEmpty()) {
      throw new IllegalStateException("Cart is empty");
    }

    List<OrderItem> orderItems = new ArrayList<>();
    BigDecimal subtotal = BigDecimal.ZERO;

    for (db.dto.CartItemDTO cartItem : cartItems) {
//      db.dto.ProductDTO product = productMapper.toDto(cartItem.getProduct());
      BigDecimal total = cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()));

      OrderItem orderItem = OrderItem.builder()
          .product(productMapper.toEntity(cartItem.getProduct()))
          .quantity(cartItem.getQuantity())
          .unitPrice(cartItem.getProduct().getPrice())
          .total(total)
          .build();

      orderItems.add(orderItem);
      subtotal = subtotal.add(total);
    }

    Order.OrderBuilder builder = Order.builder()
        .user(user)
        .orderNumber(generateOrderNumber())
        .status(OrderStatus.NEW)
        .placedAt(LocalDateTime.now())
        .subtotal(subtotal)
        .items(new ArrayList<>());

    if (couponCode != null && !couponCode.isBlank()) {
      db.dto.CouponDTO couponDto = extendedCouponService.findByCode(couponCode);
      Coupon coupon = couponMapper.toEntity(couponDto);
      couponValidator.validateCoupon(coupon, user);
      BigDecimal discount = couponValidator.applyDiscount(coupon, subtotal);

      builder
          .coupon(coupon)
          .discountAmount(discount)
          .total(subtotal.subtract(discount));

      coupon.setTimesUsed(coupon.getTimesUsed() + 1);
      couponService.update(couponMapper.toDto(coupon));
    } else {
      builder
          .discountAmount(BigDecimal.ZERO)
          .total(subtotal);
    }

    Order order = builder.build();
    db.dto.OrderDTO savedOrderDto = orderService.create(orderMapper.toDto(order));
    Order savedOrder = orderMapper.toEntity(savedOrderDto);

    for (OrderItem item : orderItems) {
      item.setOrder(savedOrder);
      db.dto.OrderItemDTO dto = orderItemService.create(orderItemMapper.toDto(item));
      savedOrder.getItems().add(orderItemMapper.toEntity(dto));
    }

    extendedCartService.clearCart(userId);
    return orderMapper.toDto(savedOrder);
  }

  private int generateOrderNumber() {
    return (int) (System.currentTimeMillis() % 100_000);
  }
}
