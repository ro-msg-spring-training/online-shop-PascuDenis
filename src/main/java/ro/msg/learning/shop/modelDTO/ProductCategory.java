package ro.msg.learning.shop.modelDTO;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity(name = "ProductCategory")
@Table(name = "ProductCategory")
public class ProductCategory implements Serializable {
    @Id
    private Integer productCategoryId;
    private String name;
    private String description;

    public ProductCategory() {
    }

    public ProductCategory(Integer productCategoryId, String name, String description) {
        this.productCategoryId = productCategoryId;
        this.name = name;
        this.description = description;
    }

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
