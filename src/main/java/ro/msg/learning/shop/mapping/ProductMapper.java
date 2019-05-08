package ro.msg.learning.shop.mapping;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.ProductDTO;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.model.Supplier;
import ro.msg.learning.shop.repository.IProductCategoryRepository;
import ro.msg.learning.shop.repository.ISupplierRepository;

@Component
@AllArgsConstructor
public class ProductMapper implements Mapper<Product, ProductDTO>{
    private IProductCategoryRepository productCategoryRepository;
    private ISupplierRepository supplierRepository;


    @Override
    public Product convertToEntity(ProductDTO dto) {
//        ModelMapper modelMapper = new ModelMapper();
//        Product product = modelMapper.map(dto, Product.class);
//        product.setProductCategory(productCategoryRepository.findById(dto.getSupplierId()).orElse(null));
//        product.setSupplier(supplierRepository.findById(dto.getSupplierId()).orElse(null));
////        product.setProductCategory(new ProductCategory());
////        product.setSupplier(new Supplier());
//        return product;
        Product product = new Product();
        product.setId(10);
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setWeight(dto.getWeight());
//        product.setProductCategory(productCategoryRepository.findById(dto.getProductCategoryId()).orElse(new ProductCategory("zero", "zero")));
//        product.setSupplier(supplierRepository.findById(dto.getSupplierId()).orElse(null));
        product.setProductCategory(new ProductCategory("zero", "zero"));
        product.setSupplier(new Supplier(1, "zero"));


        return product;
    }

    @Override
    public ProductDTO convertToDto(Product entity) {
//        ModelMapper modelMapper = new ModelMapper();
//        return modelMapper.map(entity, ProductDTO.class);

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
