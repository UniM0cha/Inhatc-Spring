package kr.inhatc.spring.solstice_shop.item.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import kr.inhatc.spring.solstice_shop.item.constant.ItemSellStatus;
import kr.inhatc.spring.solstice_shop.utils.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "item")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Item extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "item_id")
  private Long id;

  @Column(nullable = false, length = 50)
  private String itemNm;

  @Column(nullable = false)
  private int price;

  @Column(nullable = false, name = "number")
  private int stockNumber;

  @Enumerated(EnumType.STRING)
  private ItemSellStatus itemSellStatus;

  @Lob
  @Column(nullable = false)
  private String itemDetail;
}
