package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.dto.ProductCategoryDTO;
import ro.msg.learning.shop.exception.ProductCartegoryNotFoundException;
import ro.msg.learning.shop.mapping.ProductCategoryMapper;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.repository.IProductCategoryRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductCatrgoryService implements IService<ProductCategoryDTO, Integer> {
    private IProductCategoryRepository productCategoryRepository;

    @Override
    @Transactional
    public ProductCategoryDTO findOne(Integer id) {
        ProductCategory productCategory = productCategoryRepository.findById(id).orElseThrow(() -> new ProductCartegoryNotFoundException(id));
        return new ProductCategoryMapper().convertToDto(productCategory);
    }

    @Override
    @Transactional
    public List<ProductCategoryDTO> findAll() {
        return (List)productCategoryRepository.findAll();
    }

    @Override
    @Transactional
    public ProductCategoryDTO save(ProductCategoryDTO entity) {
        ProductCategory productCategory = productCategoryRepository.save(new ProductCategoryMapper().convertToEntity(entity));
        return new ProductCategoryMapper().convertToDto(productCategory);
    }

    @Override
    @Transactional
    public ProductCategoryDTO update(ProductCategoryDTO entity) {
        //TODO
        return null;
    }

    @Override
    @Transactional
    public void remove(Integer id) {
        productCategoryRepository.deleteById(id);
    }
}
