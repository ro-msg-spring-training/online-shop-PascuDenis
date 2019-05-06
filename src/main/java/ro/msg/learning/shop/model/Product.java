package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@ToString
@EqualsAndHashCode
@Entity(name = "Product")
@Table(name = "Product")
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    @Id
    @Column(updatable = false)
    private Integer id;

    @NonNull
    @Column(nullable = false)
    private String name;

    @NonNull
    @Column(nullable = false)
    private String description;

    @NonNull
    @Column(nullable = false)
    private Long price;

    @NonNull
    @Column(nullable = false)
    private Double weight;

    @ManyToOne
    @JoinColumn(name = "productCategoryId", referencedColumnName = "id")
    private ProductCategory productCategory;

    @ManyToOne
    @JoinColumn(name = "supplierId", referencedColumnName = "id")
    private Supplier supplier;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<OrderDetail> orders;

    @NonNull
    @Column(nullable = false)
    private String imageURL;

    public Product(String name, String description, Long price, double weight, ProductCategory productCategory, Supplier supplier) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.weight = weight;
        this.productCategory = productCategory;
        this.supplier = supplier;
    }
}

