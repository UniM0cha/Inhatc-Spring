package kr.inhatc.spring.solstice_shop.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.inhatc.spring.solstice_shop.cart.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
