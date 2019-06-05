package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.dto.ProductCategoryDTO;
import ro.msg.learning.shop.exception.ProductCartegoryNotFoundException;
import ro.msg.learning.shop.mapping.ProductCategoryMapper;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.repository.IProductCategoryRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class ProductCategoryService implements IService<ProductCategoryDTO, Integer> {
    private final IProductCategoryRepository productCategoryRepository;

    @Override
    @Transactional
    public ProductCategoryDTO findOne(Integer id) {
        ProductCategory productCategory = productCategoryRepository.findById(id).orElseThrow(() -> new ProductCartegoryNotFoundException(id));
        return new ProductCategoryMapper().convertToDto(productCategory);
    }

    @Override
    @Transactional
    public List<ProductCategoryDTO> findAll() {

        ProductCategoryMapper mapper = new ProductCategoryMapper();
        return StreamSupport.stream(productCategoryRepository.findAll().spliterator(), false).map(mapper::convertToDto).
                collect(Collectors.toList());
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

        ProductCategory productCategoryToUpdate = productCategoryRepository.findById(entity.getId()).orElseThrow(() -> new ProductCartegoryNotFoundException(entity.getId()));

        if (entity.getName() != null && !entity.getName().equals(productCategoryToUpdate.getName())){
            productCategoryToUpdate.setName(entity.getName());
        }

        if (!entity.getDescription().equals(productCategoryToUpdate.getDescription())){
            productCategoryToUpdate.setDescription(entity.getDescription());
        }


        ProductCategory updatedProductCategory = productCategoryRepository.save(productCategoryToUpdate);
        return new ProductCategoryMapper().convertToDto(updatedProductCategory);
    }

    @Override
    @Transactional
    public void remove(Integer id) {
        productCategoryRepository.deleteById(id);
    }
}
