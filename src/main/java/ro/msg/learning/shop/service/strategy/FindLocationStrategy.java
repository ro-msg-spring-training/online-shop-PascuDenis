package ro.msg.learning.shop.service.strategy;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.ProductDTO;
import ro.msg.learning.shop.model.Stock;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Component
public interface FindLocationStrategy {
    List<Stock> searchLocation(LocalDateTime timestamp, Integer address, Map<ProductDTO, Integer> products);
}
