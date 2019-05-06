package ro.msg.learning.shop.mapping;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.ProductDTO;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.repository.IProductCategoryRepository;
import ro.msg.learning.shop.repository.ISupplierRepository;

@Component
@AllArgsConstructor
public class ProductMapper extends Mapper<Product, ProductDTO>{
    private IProductCategoryRepository productCategoryRepository;
    private ISupplierRepository supplierRepository;


    @Override
    public Product convertToEntity(ProductDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Product product = modelMapper.map(dto, Product.class);
        product.setProductCategory(productCategoryRepository.findById(dto.getSupplierId()).orElse(null));
        product.setSupplier(supplierRepository.findById(dto.getSupplierId()).orElse(null));
        return product;
    }

    @Override
    public ProductDTO convertToDto(Product entity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, ProductDTO.class);
    }
}
