package ro.msg.learning.shop.mapping;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.StockDTO;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.ILocationRepository;
import ro.msg.learning.shop.repository.IProductRepository;

@Component
@AllArgsConstructor
public class StockMapper implements Mapper<Stock, StockDTO> {
    private ILocationRepository locationRepository;
    private IProductRepository productRepository;

    @Override
    public Stock convertToEntity(StockDTO dto) {
        Stock stock = new Stock();
        stock.setId(dto.getId());
        stock.setQuantity(dto.getQuantity());
        stock.setLocation(locationRepository.findById(dto.getLocationId()).orElse(null));
        stock.setProduct(productRepository.findById(dto.getProductId()).orElse(null));
        return stock;
    }

    @Override
    public StockDTO convertToDto(Stock entity) {
        StockDTO stockDTO = new StockDTO();
        stockDTO.setId(entity.getId());
        stockDTO.setQuantity(entity.getQuantity());
        stockDTO.setLocation(entity.getLocation());
        stockDTO.setProduct(entity.getProduct());
        return stockDTO;
    }
}
