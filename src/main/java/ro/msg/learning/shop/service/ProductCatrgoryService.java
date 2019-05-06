package ro.msg.learning.shop.service;

import ro.msg.learning.shop.dto.ProductCategoryDTO;
import ro.msg.learning.shop.mapping.ProductCategoryMapper;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.repository.IProductCategoryRepository;

import java.util.List;

public class ProductCatrgoryService implements IService<ProductCategoryDTO, Integer> {
    private IProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategoryDTO findOne(Integer id) {
        ProductCategory productCategory = productCategoryRepository.findById(id).orElse(null);
        return new ProductCategoryMapper().convertToDto(productCategory);
    }

    @Override
    public List<ProductCategoryDTO> findAll() {
        return (List)productCategoryRepository.findAll();
    }

    @Override
    public ProductCategoryDTO save(ProductCategoryDTO entity) {
        ProductCategory productCategory = productCategoryRepository.save(new ProductCategoryMapper().convertToEntity(entity));
        return new ProductCategoryMapper().convertToDto(productCategory);
    }

    @Override
    public ProductCategoryDTO update(ProductCategoryDTO entity) {
        //TODO
        return null;
    }

    @Override
    public void remove(Integer id) {
        productCategoryRepository.deleteById(id);
    }
}
