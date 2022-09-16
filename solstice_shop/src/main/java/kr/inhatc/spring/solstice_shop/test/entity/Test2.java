package kr.inhatc.spring.solstice_shop.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Test2 {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // MySQL을 사용한다면 IDENTITY를 사용하는게 좋음
  private Long id;
  private int age;

  @Column(name = "name", nullable = false, length = 20)
  private String name;
  private String info;
}
