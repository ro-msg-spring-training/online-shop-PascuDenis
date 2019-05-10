package ro.msg.learning.shop.mapping;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.ProductCategoryDTO;
import ro.msg.learning.shop.model.ProductCategory;

@Component
@AllArgsConstructor
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
