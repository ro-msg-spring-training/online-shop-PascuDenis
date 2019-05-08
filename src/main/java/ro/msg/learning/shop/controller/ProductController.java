package ro.msg.learning.shop.controller;

import lombok.AllArgsConstructor;
//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dto.ProductDTO;
import ro.msg.learning.shop.exception.ProductNotFoundException;
import ro.msg.learning.shop.service.ProductService;

import java.util.List;

@RestController
@AllArgsConstructor
//@RequestMapping("/products")
public class ProductController implements IController<ProductDTO, Integer>{

    @Autowired
    private final ProductService productService;
//    private static final Logger logger = LogManager.getLogger(ProductController.class.getName());

    @Override
    @GetMapping("/products/{id}")
    public ProductDTO getOne(@PathVariable Integer id) {
        try {
            return productService.findOne(id);
        } catch (ProductNotFoundException e) {
//            logger.info(e.getMessage());
        }
        return null;
    }

    @Override
    @GetMapping("/products")
    public List<ProductDTO> getAll() {
        return productService.findAll();
    }

    @Override
    @PostMapping("/products/s")
    public ProductDTO save(@RequestBody ProductDTO entity) {
        return productService.save(entity);
    }

    @Override
    @PutMapping("/products/{id}")
    public ProductDTO update(@RequestBody ProductDTO entity) {
        return productService.update(entity);
    }

    @Override
    @DeleteMapping("/products/{id}")
    public void remove(Integer id) {
        productService.remove(id);
    }
}
