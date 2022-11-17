package kr.inhatc.spring.solstice_shop.item.dto;

import java.time.LocalDateTime;

import kr.inhatc.spring.solstice_shop.utils.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto extends BaseEntity {

    private Long id;

    private String itemNm;

    private Integer price;

    private String itemDetail;

    private String sellStatCd;

    private LocalDateTime regTime;

    private LocalDateTime updateTime;

}
