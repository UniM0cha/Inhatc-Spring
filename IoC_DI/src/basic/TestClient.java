package basic;

public class TestClient {
    public static void main(String[] args) throws Exception {
        HyundaiMaker maker = new HyundaiMaker();
        OrderManager manager = new OrderManager(maker);
        // OrderManager manager = new OrderManager();
        manager.order();
    }
}
