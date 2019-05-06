package ro.msg.learning.shop.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@Table(name = "OrderDetail")
public class OrderDetail implements Serializable {

    @Id
    @Column(updatable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "oderId", referencedColumnName = "id")
    private Order order;

    @ManyToOne(optional = false)
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

}
