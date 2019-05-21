package ro.msg.learning.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ro.msg.learning.shop.dto.ProductCategoryDTO;
import ro.msg.learning.shop.service.ProductCategoryService;

import java.util.List;

//@RestController
@AllArgsConstructor
//@RequestMapping("/productCategory")
public class ProductCategoryController implements IController<ProductCategoryDTO, Integer> {

    @Autowired
    private final ProductCategoryService productCatrgoryService;

    @Override
    @GetMapping("/productCategory/{id}")
    public ResponseEntity<ProductCategoryDTO> getOne(@PathVariable Integer id) {
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
