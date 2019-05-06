package ro.msg.learning.shop.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Data
@Entity(name = "Order")
@Table(name = "Order_")
@NoArgsConstructor
public class Order implements Serializable {

    @Id
    @Column(updatable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "locationId", referencedColumnName = "id")
    private Location location;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "addressId", referencedColumnName = "id")
    private Address address;

    @Column(name = "CreateAt")
    private Date createdAt;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails;
}
