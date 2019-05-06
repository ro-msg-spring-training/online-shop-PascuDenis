package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString
@EqualsAndHashCode
@Entity
@NoArgsConstructor
@Table(name = "Stock")
public class Stock implements Serializable {

    @Id
    @Column(updatable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private Product product;

    @ManyToOne(optional = false)
    @JoinColumn(name = "locationId", referencedColumnName = "id")
    private Location location;

    @NonNull
    @Column(nullable = false)
    private Integer quantity;

}
