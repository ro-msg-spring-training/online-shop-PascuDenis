package ro.msg.learning.shop.modelDTO;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity(name = "Product")
@Table(name = "Product")
public class Product implements Serializable {
    @Id
    private Integer productId;
    private String name;
    private String description;
    private Long price;
    private Double weight;
    private Integer productCategoryId;
    private Integer supplierId;
    private String imageURL;

    public Product() {
    }

    public Product(Integer productId, String name, String description, Long price, Double weight, Integer productCategory, Integer supplier, String imageURL) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.weight = weight;
        this.productCategoryId = productCategory;
        this.supplierId = supplier;
        this.imageURL = imageURL;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getProductCategory() {
        return productCategoryId;
    }

    public void setProductCategory(Integer productCategory) {
        this.productCategoryId = productCategory;
    }

    public Integer getSupplier() {
        return supplierId;
    }

    public void setSupplier(Integer supplier) {
        this.supplierId = supplier;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
