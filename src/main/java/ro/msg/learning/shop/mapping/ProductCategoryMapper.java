package ro.msg.learning.shop.mapping;

import org.modelmapper.ModelMapper;
import ro.msg.learning.shop.dto.ProductCategoryDTO;
import ro.msg.learning.shop.model.ProductCategory;

public class ProductCategoryMapper implements Mapper<ProductCategory, ProductCategoryDTO> {

    @Override
    public ProductCategory convertToEntity(ProductCategoryDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, ProductCategory.class);
    }

    @Override
    public ProductCategoryDTO convertToDto(ProductCategory entity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, ProductCategoryDTO.class);
    }
}
