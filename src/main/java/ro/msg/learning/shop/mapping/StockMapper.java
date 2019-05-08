package ro.msg.learning.shop.mapping;

import org.modelmapper.ModelMapper;
import ro.msg.learning.shop.dto.StockDTO;
import ro.msg.learning.shop.model.Stock;
import ro.msg.learning.shop.repository.ILocationRepository;
import ro.msg.learning.shop.repository.IProductRepository;

public class StockMapper implements Mapper<Stock, StockDTO> {
    private ILocationRepository locationRepository;
    private IProductRepository productRepository;

    @Override
    public Stock convertToEntity(StockDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Stock stock = modelMapper.map(dto, Stock.class);
        stock.setLocation(locationRepository.findById(dto.getLocationId()).orElse(null));
        stock.setProduct(productRepository.findById(dto.getProductId()).orElse(null));
        return stock;
    }

    @Override
    public StockDTO convertToDto(Stock entity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, StockDTO.class);
    }
}
