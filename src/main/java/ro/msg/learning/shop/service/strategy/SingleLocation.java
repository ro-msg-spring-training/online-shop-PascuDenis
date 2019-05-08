package ro.msg.learning.shop.service.strategy;

import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.service.createorder.OrderObj;

import java.util.List;

public class SingleLocation implements LocationStrategy {
    @Override
    public List<Stock> searchLocation(OrderObj order    ) {
        return null;
    }
}
