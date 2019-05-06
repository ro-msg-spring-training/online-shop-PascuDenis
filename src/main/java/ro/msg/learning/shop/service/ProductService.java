package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.dto.ProductDTO;
import ro.msg.learning.shop.mapping.ProductMapper;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.repository.IProductCategoryRepository;
import ro.msg.learning.shop.repository.IProductRepository;
import ro.msg.learning.shop.repository.ISupplierRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService implements IService<ProductDTO, Integer> {

    private IProductRepository productRepository;
    private ISupplierRepository supplierRepository;
    private IProductCategoryRepository productCategoryRepository;

    @Override
    @Transactional
    public ProductDTO findOne(Integer id) {
        Product product = productRepository.findById(id).orElse(null);
        return new ProductMapper(productCategoryRepository, supplierRepository).convertToDto(product);
    }

    @Override
    @Transactional
    public List<ProductDTO> findAll() {
        return (List)productRepository.findAll();
    }

    @Override
    @Transactional
    public ProductDTO save(ProductDTO entity) {
        Product product = productRepository.save(new ProductMapper(productCategoryRepository, supplierRepository).convertToEntity(entity));
        return new ProductMapper(productCategoryRepository, supplierRepository).convertToDto(product);
    }

    @Override
    @Transactional
    public ProductDTO update(ProductDTO entity) {
        //TODO
        return null;
    }

    @Override
    @Transactional
    public void remove(Integer id) {
        productRepository.deleteById(id);
    }
}