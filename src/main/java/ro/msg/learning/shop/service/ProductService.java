package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IService<ProductDTO, Integer> {

    private final IProductRepository productRepository;
    private final ISupplierRepository supplierRepository;
    private final IProductCategoryRepository productCategoryRepository;

    @Autowired
    public ProductService(IProductRepository productRepository, ISupplierRepository supplierRepository, IProductCategoryRepository productCategoryRepository) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
        this.productCategoryRepository = productCategoryRepository;
    }

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
        List<Product> productList = (List<Product>) productRepository.findAll();
        List<ProductDTO> productReturnList = new ArrayList<>();
        for (Product product : productList){
            productReturnList.add(mapper.convertToDto(product));
        }
        return productReturnList;
//        return StreamSupport.stream(productRepository.findAll().spliterator(), false).map(mapper::convertToDto).
//                collect(Collectors.toList());
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

        if (entity.getName() != null && !entity.getName().equals(productToUpdate.getName())) {
            productToUpdate.setName(entity.getName());
        }

        if (!entity.getDescription().equals(productToUpdate.getDescription())) {
            productToUpdate.setDescription(entity.getDescription());
        }

        if (entity.getPrice() != null && !entity.getPrice().equals(productToUpdate.getPrice())) {
            productToUpdate.setPrice(entity.getPrice());
        }

        if (entity.getWeight() != null && !entity.getWeight().equals(productToUpdate.getWeight())) {
            productToUpdate.setWeight(entity.getWeight());
        }

        if (entity.getProductCategoryId() != null) {
            Optional<ProductCategory> productCategory = productCategoryRepository.findById(entity.getProductCategoryId());
            productCategory.ifPresent(productToUpdate::setProductCategory);
        }

        if (entity.getSupplierId() != null) {
            Optional<Supplier> suplier = supplierRepository.findById(entity.getSupplierId());
            suplier.ifPresent(productToUpdate::setSupplier);
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