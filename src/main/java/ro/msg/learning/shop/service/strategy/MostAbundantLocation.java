package ro.msg.learning.shop.service.strategy;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.dto.*;
import ro.msg.learning.shop.dto.orderinput.OrderInputDTO;
import ro.msg.learning.shop.dto.orderinput.ProductOrderInputDTO;
import ro.msg.learning.shop.exception.StockNotFoundException;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.repository.IStockRepository;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class MostAbundantLocation implements FindLocationStrategy {
    //TODO: use constructor injection

    @Autowired
    private IStockRepository stockRepository;


    private Location locationsWithMaxQuantityForOneProduct(ProductOrderInputDTO product) {
        Location productLocation = stockRepository.getLocationWithMaximumQuantityForOneProduct(product.getProductId(), product.getQuantity());
        if (productLocation == null) {
            throw new StockNotFoundException(product.getProductId());
        }
        return productLocation;
    }

    @Transactional
    @Override
    public List<StockDTO> searchLocation(OrderInputDTO order) {
        List<StockDTO> productsStockToReturn = new ArrayList<>();

        for(ProductOrderInputDTO product : order.getProductInputList()){
            StockDTO foundStock = new StockDTO(product.getProductId(), product.getQuantity(), locationsWithMaxQuantityForOneProduct(product).getId());
            productsStockToReturn.add(foundStock);
        }
        return productsStockToReturn;
    }
}
