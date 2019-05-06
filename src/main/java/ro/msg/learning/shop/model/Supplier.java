package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "Supplier")
@Table(name = "Supplier")
public class Supplier implements Serializable {
    @Id
    @Column(updatable = false)
    private Integer id;

    @NonNull
    @Column(nullable = false)
    private String name;

    @NonNull
    @OneToMany(mappedBy = "supplier")
    private Set<Product> products;


    public Supplier(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}