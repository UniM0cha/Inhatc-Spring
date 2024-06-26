package javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CarConfig {

  @Bean(name = "hyundai")
  public CarMaker hyundaiCar() {
    CarMaker maker = new HyundaiMaker();
    return maker;
  }

  @Bean(name = "kia")
  public CarMaker kiaCar() {
    CarMaker maker = new KiaMaker();
    return maker;
  }

  @Bean(name = "orderManager")
  public OrderManager orderManager() {
    OrderManager manager = new OrderManager(hyundaiCar());
    return manager;
  }
}
