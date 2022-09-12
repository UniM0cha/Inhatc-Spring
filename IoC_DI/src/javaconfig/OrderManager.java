package javaconfig;

public class OrderManager {
  private CarMaker maker;
  // private KiaMaker maker;

  public OrderManager(CarMaker maker) {
    // maker = new HyundaiMaker();
    // this.maker = new KiaMaker();
    this.maker = maker;
  }

  public void setMaker(CarMaker maker) {
    this.maker = maker;
  }

  public void order() {
    Money money = new Money(1000);
    System.out.println("판매상 : ((지불))" + money.getAmount());

    Car car = maker.sell(money);

    System.out.println("판매상 : ((인수))" + car.getName());
  }
}
