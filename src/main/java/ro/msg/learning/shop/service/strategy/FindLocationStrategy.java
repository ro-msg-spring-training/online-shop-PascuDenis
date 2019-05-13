package ro.msg.learning.shop.service.strategy;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.OrderInputDTO;
import ro.msg.learning.shop.dto.ProductDTO;
import ro.msg.learning.shop.dto.StockDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Component
public interface FindLocationStrategy {
    List<StockDTO> searchLocation(OrderInputDTO orderInputDTO);
}
