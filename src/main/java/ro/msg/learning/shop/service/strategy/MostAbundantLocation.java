package ro.msg.learning.shop.service.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.dto.*;
import ro.msg.learning.shop.mapping.LocationMapper;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.service.LocationService;
import ro.msg.learning.shop.service.ProductService;
import ro.msg.learning.shop.service.StockService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MostAbundantLocation implements FindLocationStrategy {
    @Autowired
    private ProductService productService;
    @Autowired
    private StockService stockService;

    @Override
    @Transactional
    public List<StockDTO> searchLocation(OrderInputDTO orderInputDTO) {
        //TODO: potential buggy method
        List<StockDTO> foundStockList = stockService.findAll();

//        for (StockDTO stock : stocks) {
//            for (Map.Entry<ProductDTO, Integer> product : products.entrySet()) {
//                if (stock.getProductId().equals(product.getKey().getId()) && (stock.getQuantity() >= product.getValue())){
//                    stocks.add(stock);
//                }
//            }
//        }
        List<ProductOrderInputDTO> productOrderInputDTOS = orderInputDTO.getProductInputList();
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (ProductOrderInputDTO product : productOrderInputDTOS){
            productDTOS.add(productService.findOne(product.getProductId()));
        }

        for (ProductDTO product : productDTOS) {
            foundStockList.add(getLocationWithMaxQuantityForOneProduct(product));
        }
        return foundStockList;
    }

    @Transactional
    private StockDTO getLocationWithMaxQuantityForOneProduct(ProductDTO product) {
        int max = 0;
        StockDTO maxStock = null;

        for (StockDTO stock : stockService.findAll()) {
            if (stock.getProductId().equals(product.getId()) && stock.getQuantity() > max) {
                max = stock.getQuantity();
                maxStock = stock;
            }
        }
        return maxStock;
    }
}
