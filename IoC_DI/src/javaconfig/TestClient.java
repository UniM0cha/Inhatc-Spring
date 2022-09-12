package javaconfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestClient {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(CarConfig.class);
        OrderManager manager = context.getBean("orderManager", OrderManager.class);
        manager.order();

        CarMaker maker = context.getBean("kia", KiaMaker.class);
        manager.setMaker(maker);
        manager.order();
    }
}
