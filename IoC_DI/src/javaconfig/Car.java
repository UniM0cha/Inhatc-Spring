package javaconfig;

/**
 * 자동차 이름을 관리하는 클래스
 * 
 * @author 이정윤
 */

public class Car {
  private String name; // 자동차 이름

  public Car(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
