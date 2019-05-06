package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.ProductDTO;
import ro.msg.learning.shop.repository.IProductCategoryRepository;
import ro.msg.learning.shop.repository.IProductRepository;
import ro.msg.learning.shop.repository.ISupplierRepository;

@Service
@AllArgsConstructor
public class ProductService {

    private IProductRepository productRepository;
    private ISupplierRepository supplierRepository;
    private IProductCategoryRepository productCategoryRepository;

    public void createProduct(ProductDTO productDTO){


    }
}