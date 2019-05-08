package ro.msg.learning.shop.service.strategy;

public class CreateOrderService {
    private LocationStrategy strategy;

    public CreateOrderService(LocationStrategy strategy) {
        this.strategy = strategy;
    }
}
