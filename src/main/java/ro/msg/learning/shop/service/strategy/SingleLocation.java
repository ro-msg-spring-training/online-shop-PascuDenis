package ro.msg.learning.shop.service.strategy;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import ro.msg.learning.shop.controller.ProductController;
import ro.msg.learning.shop.dto.*;
import ro.msg.learning.shop.dto.orderinput.OrderInputDTO;
import ro.msg.learning.shop.dto.orderinput.ProductOrderInputDTO;
import ro.msg.learning.shop.exception.*;
import ro.msg.learning.shop.mapping.StockMapper;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.repository.*;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
public class SingleLocation implements FindLocationStrategy {
    //TODO: Use constructor injection

    private static final Logger logger = LogManager.getLogger(ProductController.class.getName());

    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private ILocationRepository locationRepository;
    @Autowired
    private IStockRepository stockRepository;

    private List<StockDTO> getLocationsForOneProduct(Integer productId, Integer quantity) {
        List<StockDTO> stockDTOS = new ArrayList<>();
        for (Stock stock : stockRepository.findStockLocationsForOneProduct(productId, quantity)) {
            stockDTOS.add(new StockMapper(locationRepository, productRepository).convertToDto(stock));
        }
        return stockDTOS;
    }

    //    @Override
//    public List<StockDTO> searchLocation(OrderInputDTO order) {
//        int countLocations = 0;
////        Location theRightLocation = null;
//        List<Location> foundLocations = new ArrayList<>();
//        List<StockDTO> productsStockToReturn = new ArrayList<>();
//        Map<ProductOrderInputDTO, List<Location>> foundLocationsMAP = new HashMap<>();
//
//        // Iterate through the order products and add every stock found in a list
//        for (ProductOrderInputDTO productOrderInputDTO : order.getProductInputList()) {
//            List<StockDTO> foundStocks = getLocationsForOneProduct(productOrderInputDTO.getProductId(), productOrderInputDTO.getQuantity());
//            if (foundStocks.isEmpty()) {
//                throw new StockNotFoundException(productOrderInputDTO.getProductId());
//            }
//            for (StockDTO stock : foundStocks) {
//                foundLocations.add(stock.getLocation());
//            }
//            foundLocationsMAP.put(productOrderInputDTO, foundLocations);
//        }
//
//        //Get the location list from the first product in the map
//        Map.Entry<ProductOrderInputDTO, List<Location>> entry = foundLocationsMAP.entrySet().iterator().next();
//        List<Location> locationsToBeSearched = entry.getValue();
//
//        for (int i = 1; i < locationsToBeSearched.size(); i++) {
//            //Iterate over these location list and check weather each product of the contains the searched location
//            for (Map.Entry<ProductOrderInputDTO, List<Location>> locationEntry : foundLocationsMAP.entrySet()) {
//                // If the location list of a product doesn't contain the searched location throw error else increase a counter
//                if (!locationEntry.getValue().contains(locationsToBeSearched.get(i))) {
//                    throw new LocationNotFoundException("Couldn't find a single location which contains all products from this order!");
//                }
//                countLocations++;
//
//                if (foundLocationsMAP.size() == countLocations) {
////                    theRightLocation = locationsToBeSearched.get(i);
//
//                    //TODO: Minor bug
//                    productsStockToReturn.add(new StockDTO(locationEntry.getKey().getProductId(), locationEntry.getKey().getQuantity(), locationEntry.getValue().get(i)));
//                }
//            }
//        }
////        return theRightLocation;
//        return productsStockToReturn;
//    }
    @Override
    public List<StockDTO> searchLocation(OrderInputDTO order) {
        boolean found = false;
        int countLocations = 0;
        List<StockDTO> productsStockToReturn = new ArrayList<>();
        Map<ProductOrderInputDTO, List<Location>> foundLocationsMAP = new HashMap<>();

        // Iterate through the order products and add every stock found in a list
        for (ProductOrderInputDTO productOrderInputDTO : order.getProductInputList()) {
            List<Location> foundLocations = new ArrayList<>();
            List<Stock> foundStocks = stockRepository.findStockLocationsForOneProduct(productOrderInputDTO.getProductId(), productOrderInputDTO.getQuantity());
            if (foundStocks.isEmpty()) {
                throw new StockNotFoundException(productOrderInputDTO.getProductId());
            }
            for (Stock stock : foundStocks) {
                foundLocations.add(stock.getLocation());
            }
            foundLocationsMAP.put(productOrderInputDTO, foundLocations);
        }

        //Get the location list from the first product in the map
        Map.Entry<ProductOrderInputDTO, List<Location>> entry = foundLocationsMAP.entrySet().iterator().next();
        List<Location> locationsToBeSearched = entry.getValue();

        for (Location l : locationsToBeSearched) {
            //Iterate over these location list and check weather each product of the contains the searched location
            for (Map.Entry<ProductOrderInputDTO, List<Location>> locationEntry : foundLocationsMAP.entrySet()) {
                boolean gef = false;
                // If the location list of a product doesn't contain the searched location throw error else increase a counter
                if (!locationEntry.getValue().contains(l)) {
                    productsStockToReturn.clear();
                    countLocations = 0;
                    found = false;
                    break;
                } else {
                    countLocations++;
                    for (Location stock : locationEntry.getValue()) {
                        if (stock.getId().equals(l.getId())) {
                            productsStockToReturn.add(new StockMapper(locationRepository, productRepository).convertToDto(stockRepository.findById(stock.getId()).get()));
                            gef = true;
                        }
                    }
                    if (foundLocationsMAP.size() == countLocations) {
                        found = true;
                        break;
                    }
                }
                if (gef) break;
            }
            if (found) {
                break;
            }
        }
        if (productsStockToReturn.size() == foundLocationsMAP.size()) {
            return productsStockToReturn;
        } else {
            throw new ProductInStockNotFoundException();
        }
    }
}
