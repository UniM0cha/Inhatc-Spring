package interfaces;

public class TestClient {
    public static void main(String[] args) throws Exception {
        CarMaker maker = new KiaMaker();
        OrderManager manager = new OrderManager(maker);
        // OrderManager manager = new OrderManager();
        manager.order();
    }
}
