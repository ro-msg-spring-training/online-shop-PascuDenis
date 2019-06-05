package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ro.msg.learning.shop.model.ProductCategory;
import ro.msg.learning.shop.model.Supplier;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDTO {

  private Integer id;
  private String name;
  private String description;
  private BigDecimal price;
  private Double weight;
  private Integer productCategoryId;
  private Integer supplierId;
  private String imageUrl;
}
