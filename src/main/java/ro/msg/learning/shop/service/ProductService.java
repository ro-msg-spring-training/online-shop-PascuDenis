package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.dto.ProductDTO;
import ro.msg.learning.shop.exception.ProductNotFoundException;
import ro.msg.learning.shop.mapping.ProductMapper;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.model.Supplier;
import ro.msg.learning.shop.repository.IProductCategoryRepository;
import ro.msg.learning.shop.repository.IProductRepository;
import ro.msg.learning.shop.repository.ISupplierRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class ProductService implements IService<ProductDTO, Integer> {

    private IProductRepository productRepository;
    private ISupplierRepository supplierRepository;
    private IProductCategoryRepository productCategoryRepository;

    @Override
    @Transactional
    public ProductDTO findOne(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        return new ProductMapper(productCategoryRepository, supplierRepository).convertToDto(product);
    }

    @Override
    @Transactional
    public List<ProductDTO> findAll() {
        ProductMapper mapper = new ProductMapper(productCategoryRepository, supplierRepository);
        return StreamSupport.stream(productRepository.findAll().spliterator(), false).map(mapper::convertToDto).
                collect(Collectors.toList());
}

    @Override
    @Transactional
    public ProductDTO save(ProductDTO entity) {
        Product productToSave = new ProductMapper(productCategoryRepository, supplierRepository).convertToEntity(entity);
        Product product = productRepository.save(productToSave);
        return new ProductMapper(productCategoryRepository, supplierRepository).convertToDto(product);
    }

    @Override
    @Transactional
    public ProductDTO update(ProductDTO entity) {
        Product productToUpdate = productRepository.findById(entity.getId()).orElseThrow(() -> new ProductNotFoundException(entity.getId()));

        if (entity.getName() != null && !entity.getName().equals(productToUpdate.getName())){
            productToUpdate.setName(entity.getName());
        }

        if (!entity.getDescription().equals(productToUpdate.getDescription())){
            productToUpdate.setDescription(entity.getDescription());
        }

        if (entity.getPrice() != null && !entity.getPrice().equals(productToUpdate.getPrice())){
            productToUpdate.setPrice(entity.getPrice());
        }

        if (entity.getWeight() != null && !entity.getWeight().equals(productToUpdate.getWeight())){
            productToUpdate.setWeight(entity.getWeight());
        }

        if (entity.getProductCategoryId()!= null) {
            Optional<ProductCategory> productCategory = productCategoryRepository.findById(entity.getProductCategoryId());
            productCategory.ifPresent(productToUpdate::setProductCategory);
        }

        if (entity.getSupplierId()!= null) {
            Optional<Supplier> productCategory = supplierRepository.findById(entity.getSupplierId());
            productCategory.ifPresent(productToUpdate::setSupplier);
        }

        Product updatedProduct = productRepository.save(productToUpdate);
        return new ProductMapper(productCategoryRepository, supplierRepository).convertToDto(updatedProduct);
    }

    @Override
    @Transactional
    public void remove(Integer id) {
        productRepository.deleteById(id);
    }
}