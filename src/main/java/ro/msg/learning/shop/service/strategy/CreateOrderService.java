package ro.msg.learning.shop.service.strategy;

public class CreateOrderService {
    private FindLocationStrategy strategy;

    public CreateOrderService(FindLocationStrategy strategy) {
        this.strategy = strategy;
    }
}
