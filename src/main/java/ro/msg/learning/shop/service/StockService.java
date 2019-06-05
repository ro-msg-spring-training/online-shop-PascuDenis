package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.dto.StockDTO;
import ro.msg.learning.shop.exception.StockNotFoundException;
import ro.msg.learning.shop.mapping.StockMapper;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StockService implements IService<StockDTO, Integer> {
    private final ILocationRepository locationRepository;
    private final IProductRepository productRepository;
    private final IStockRepository stockRepository;

    @Override
    @Transactional
    public StockDTO findOne(Integer id) {
        Stock stock = stockRepository.findById(id).orElseThrow(() -> new StockNotFoundException(id));
        return new StockMapper(locationRepository, productRepository).convertToDto(stock);
    }

    @Override
    @Transactional
    public List<StockDTO> findAll() {
      StockMapper mapper = new StockMapper(locationRepository, productRepository);
        List<Stock> stockList = (List<Stock>) stockRepository.findAll();
        List<StockDTO> stockReturnList = new ArrayList<>();
        for (Stock stock : stockList){
            stockReturnList.add(mapper.convertToDto(stock));
        }
        return stockReturnList;
//        return StreamSupport.stream(stockRepository.findAll().spliterator(), false).map(mapper::convertToDto).
//                collect(Collectors.toList());
    }

    @Override
    @Transactional
    public StockDTO save(StockDTO entity) {
        Stock stockToSave = new StockMapper(locationRepository, productRepository).convertToEntity(entity);
        Stock stock = stockRepository.save(stockToSave);
        return new StockMapper(locationRepository, productRepository).convertToDto(stock);
    }

    @Override
    @Transactional
    public StockDTO update(StockDTO entity) {
        Stock stockToUpdate = stockRepository.findById(entity.getId()).orElseThrow(() -> new StockNotFoundException(entity.getId()));

        if (entity.getQuantity() != null && !entity.getQuantity().equals(stockToUpdate.getQuantity())) {
            stockToUpdate.setQuantity(entity.getQuantity());
        }

        if (entity.getLocationId() != null) {
            Optional<Location> location = locationRepository.findById(entity.getLocationId());
            location.ifPresent(stockToUpdate::setLocation);
        }

        if (entity.getProductId() != null) {
            Optional<Product> product = productRepository.findById(entity.getProductId());
            product.ifPresent(stockToUpdate::setProduct);
        }

        Stock updatedStock = stockRepository.save(stockToUpdate);
        return new StockMapper(locationRepository, productRepository).convertToDto(updatedStock);
    }

    @Override
    @Transactional
    public void remove(Integer id) {
        stockRepository.deleteById(id);
    }

    @Transactional
    public void findAllLocations(Integer productId, Integer quantity){
        List<Stock> stocks = stockRepository.findStockLocationsForOneProduct(productId, quantity);
        for(Stock s : stocks){
            System.out.println(s);
        }
    }

    public void findLocationWithMaxQuantity(Integer productId, Integer quantity){
        System.out.println(stockRepository.getLocationWithMaximumQuantityForOneProduct(productId, quantity));
    }
//    @Transactional
//    public boolean getStockWithMaxQuantityForOneProduct(Integer stockId, Integer productId, Integer quantity) {
////        for(StockDTO stock : stocks) {
////            if (stock.getProductId().equals(product.getId()) && stock.getQuantity() >= quantity) {
////                return stock;
////            }
////        }
//
//        StockDTO foundStocks = findOne(stockId);
//
//        for (StockDTO stock : stocks) {
//            if (stock.getProductId().equals(productId) && stock.getQuantity() >= quantity) {
//                maxQuantity = stock.getQuantity();
//                foundStocks = stock;
//            }
//        }
//
//        if (maxQuantity == -1)
//            throw new ProductInStockNotFoundException(productId);
//        return foundStocks;
//    }


}
