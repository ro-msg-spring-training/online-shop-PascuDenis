package ro.msg.learning.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dto.ProductDTO;
import ro.msg.learning.shop.exception.ProductNotFoundException;
import ro.msg.learning.shop.service.ProductService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController implements IController<ProductDTO, Integer>{
    private final ProductService productService;

    @Override
    @GetMapping("/products/{id}")
    public ProductDTO getOne(@PathVariable Integer id) {
        try {
            return productService.findOne(id);
        } catch (ProductNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @GetMapping("/products")
    public List<ProductDTO> getAll() {
        return productService.findAll();
    }

    @Override
    @PostMapping("/products")
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
