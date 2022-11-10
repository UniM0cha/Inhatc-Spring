package kr.inhatc.spring.solstice_shop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.inhatc.spring.solstice_shop.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
