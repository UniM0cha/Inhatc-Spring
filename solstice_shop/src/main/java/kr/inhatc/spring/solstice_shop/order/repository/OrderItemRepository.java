package kr.inhatc.spring.solstice_shop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.inhatc.spring.solstice_shop.order.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
