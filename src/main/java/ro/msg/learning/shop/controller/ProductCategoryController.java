package ro.msg.learning.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dto.ProductCategoryDTO;
import ro.msg.learning.shop.exception.ProductCartegoryNotFoundException;
import ro.msg.learning.shop.service.ProductCatrgoryService;

import java.util.List;

//@RestController
@AllArgsConstructor
//@RequestMapping("/productCategory")
public class ProductCategoryController implements IController<ProductCategoryDTO, Integer> {
    private final ProductCatrgoryService productCatrgoryService;

    @Override
    @GetMapping("/productCategory/{id}")
    public ProductCategoryDTO getOne(@PathVariable Integer id) {
        try {
            return productCatrgoryService.findOne(id);
        } catch (ProductCartegoryNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @GetMapping("/productCategory")
    public List<ProductCategoryDTO> getAll() {
        return productCatrgoryService.findAll();
    }
    @Override
    public ProductCategoryDTO save(ProductCategoryDTO entity) {
        return null;
    }

    @Override
    public ProductCategoryDTO update(ProductCategoryDTO entity) {
        return null;
    }

    @Override
    public void remove(Integer id) {

    }
}
