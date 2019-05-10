package ro.msg.learning.shop.service.strategy;

import ro.msg.learning.shop.dto.ProductDTO;
import ro.msg.learning.shop.model.Stock;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class MostAbundantLocation implements FindLocationStrategy {
    @Override
    public List<Stock> searchLocation(LocalDateTime timestamp, Integer address, Map<ProductDTO, Integer> products) {
        //TODO
        return null;
    }
}
