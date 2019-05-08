package ro.msg.learning.shop;

//import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ro.msg.learning.shop.controller.ProductController;
import ro.msg.learning.shop.controller.SupplierController;
import ro.msg.learning.shop.dto.ProductDTO;
import ro.msg.learning.shop.mapping.ProductMapper;
import ro.msg.learning.shop.model.Address;
import ro.msg.learning.shop.model.Product;
import ro.msg.learning.shop.model.Supplier;
import ro.msg.learning.shop.repository.IAddressRepository;
import ro.msg.learning.shop.repository.IProductCategoryRepository;
import ro.msg.learning.shop.repository.IProductRepository;
import ro.msg.learning.shop.repository.ISupplierRepository;

import javax.annotation.Resource;
import java.math.BigDecimal;

@SpringBootApplication
public class ShopApplication {
//	private static final Logger logger = Logger.getLogger(ShopApplication.class.getName());

	@Autowired
	private static ProductController productController;
//	@Autowired
//	private static SupplierController supplierController;

	public static void main(String[] args) {


		SpringApplication.run(ShopApplication.class, args);

//		ProductDTO productDTO = new ProductDTO(40, "ProductName", "ProductDesc", BigDecimal.valueOf(20.20), 10.10, 1, 1);
//		productController.save(productDTO);

//				new ProductDTO(40, "ProductName", "ProductDesc", BigDecimal.valueOf(20.20), 10.10, 1, 1)
//		productController.getAll().forEach(o -> {
//			System.out.println(o.toString());
//		});


		System.out.println("merge");
	}

	@Autowired
	public void setProductController(ProductController productController){
		ShopApplication.productController = productController;
	}

//	@Autowired
//	public void setSupplierController(SupplierController supplierController){
//		ShopApplication.supplierController = supplierController;
//	}


}

