package ro.msg.learning.shop.service.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import ro.msg.learning.shop.dto.*;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.service.LocationService;
import ro.msg.learning.shop.service.ProductService;
import ro.msg.learning.shop.service.StockService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class SingleLocation implements FindLocationStrategy {
    @Autowired
    private ProductService productService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private StockService stockService;

    @Override
    public List<StockDTO> searchLocation(OrderInputDTO orderInputDTO) {
        //TODO Buggy method :/
        List<StockDTO> stockList = new ArrayList<>();
        List<LocationDTO> locationList = locationService.findAll();
        List<ProductOrderInputDTO> productOrderInputDTOS = orderInputDTO.getProductInputList();
        List<ProductDTO> productDTOS = new ArrayList<>();

        for (ProductOrderInputDTO product : productOrderInputDTOS){
            productDTOS.add(productService.findOne(product.getProductId()));
        }

        boolean hasAll = true;

//        for (LocationDTO location : locationList) {
//            for (ProductDTO product : productDTOS) {
//                if (!stockService.getStockWithRequiredQuantityForOneProduct(product, product.ge).getLocation().equals(location)) {
//                    hasAll = false;
//                }
//            }
//        }
//
//        if(hasAll) {
//            for (LocationDTO location : locationList) {
//                for (Map.Entry<ProductDTO, Integer> product : products.entrySet()) {
//                    if (stockService.getStockWithRequiredQuantityForOneProduct(product.getKey(), product.getValue()).getLocation().equals(location)) {
//                        StockDTO stock = new StockDTO();
//                        stock.setProductId(products.entrySet().iterator().next().getKey().getId());
//                        stock.setLocationId(location.getId());
//                        stock.setQuantity(products.entrySet().iterator().next().getValue());
//                        stockList.add(stock);
//                    }
//                }
//            }
//        }
        return stockList;
    }
}
