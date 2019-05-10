package ro.msg.learning.shop.service.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import ro.msg.learning.shop.dto.ProductDTO;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.service.StockService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class SingleLocation implements FindLocationStrategy {
    @Autowired
    private StockService stockService;

    @Override
    public List<Stock> searchLocation(LocalDateTime timestamp, Integer address, Map<ProductDTO, Integer> products) {
        Location location = new Location();
        for (Map.Entry<ProductDTO, Integer> product : products.entrySet()){
            location = stockService.getStockWithRequiredQuantityForOneProduct(product.getKey(), product.getValue()).getLocation();
            //TODO
        }
        return null;
    }
}
