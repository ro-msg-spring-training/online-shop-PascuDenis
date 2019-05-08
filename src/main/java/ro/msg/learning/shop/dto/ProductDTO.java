package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.model.Supplier;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private Double weight;
    private Integer productCategoryId;
    private Integer supplierId;

    public ProductDTO(Integer id, String name, String description, BigDecimal price, Double weight, Integer productCategoryId, Integer supplierId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.weight = weight;
        this.productCategoryId = productCategoryId;
        this.supplierId = supplierId;
    }

    private ProductCategory productCategory;
    private Supplier supplier;
}
