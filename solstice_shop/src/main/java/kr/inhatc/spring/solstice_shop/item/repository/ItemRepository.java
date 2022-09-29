package kr.inhatc.spring.solstice_shop.item.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import kr.inhatc.spring.solstice_shop.item.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item> {

  List<Item> findByItemNm(String itemNm);

  List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

  @Query("select i from Item i where i.itemDetail like %:itemDetail% "
      + "order by i.price desc")
  List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);

  @Query(value = "select * from item i where i.item_detail like %:itemDetail% "
      + "order by i.price desc", nativeQuery = true)
  List<Item> findByItemDetailNative(@Param("itemDetail") String itemDetail);
}
