package ro.msg.learning.shop.service.strategy;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.service.createorder.OrderObj;

import java.util.List;

@Component
public interface LocationStrategy {
    List<Stock> searchLocation(OrderObj order);
}
