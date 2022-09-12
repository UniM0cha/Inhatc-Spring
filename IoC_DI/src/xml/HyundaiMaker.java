package xml;

public class HyundaiMaker implements CarMaker {

  // 돈을 받으면 자동차를 넘겨주는 함수
  @Override
  public Car sell(Money money) {
    System.out.println("현대자동차 : <<입금>> " + money.getAmount());
    Car car = new Car("소나타");
    System.out.println("현대자동차 : <<생산>> " + car.getName());
    System.out.println("현대자동차 : <<판매>> " + car.getName());

    return car;
  }
}
