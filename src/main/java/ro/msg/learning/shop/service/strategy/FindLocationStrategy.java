package ro.msg.learning.shop.service.strategy;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.StockDTO;
import ro.msg.learning.shop.dto.orderinput.OrderInputDTO;

import java.util.List;


@Component
public interface FindLocationStrategy {
    List<StockDTO > searchLocation(OrderInputDTO order);
}
