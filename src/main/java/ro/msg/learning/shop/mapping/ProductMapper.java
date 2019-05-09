package ro.msg.learning.shop.mapping;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.ProductDTO;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.repository.IProductCategoryRepository;
import ro.msg.learning.shop.repository.ISupplierRepository;

@Component
@AllArgsConstructor
public class ProductMapper implements Mapper<Product, ProductDTO>{
    private IProductCategoryRepository productCategoryRepository;
    private ISupplierRepository supplierRepository;


    @Override
    public Product convertToEntity(ProductDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setWeight(dto.getWeight());

        product.setProductCategory(productCategoryRepository.findById(dto.getProductCategoryId()).orElse(null));
        product.setSupplier(supplierRepository.findById(dto.getSupplierId()).orElse(null));
        return product;
    }

    @Override
    public ProductDTO convertToDto(Product entity) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(entity.getId());
        productDTO.setName(entity.getName());
        productDTO.setDescription(entity.getDescription());
        productDTO.setPrice(entity.getPrice());
        productDTO.setWeight(entity.getWeight());
        productDTO.setSupplier(entity.getSupplier());
        productDTO.setProductCategory(entity.getProductCategory());
        return productDTO;
    }
}
