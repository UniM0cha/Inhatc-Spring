package kr.inhatc.spring.solstice_shop.thymeleaf.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {
    private Long id; // 상품 코드

    private String itemNm; // 상품 이름

    private int price; // 상품 가격

    private int stockNumber; // 재고 수량

    private String itemSellStatus;

    private String itemDetail; // 상품 상세 설명

    private LocalDateTime regTime; // 등록시간

    private LocalDateTime updateTime; // 수정시간
}
