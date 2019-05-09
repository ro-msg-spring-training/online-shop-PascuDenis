package ro.msg.learning.shop.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Data
@Entity(name = "Order")
@Table(name = "Orders")
@NoArgsConstructor
public class Order implements Serializable {

    @Id
    @Column(updatable = false, name = "OrdersId")
    private Integer id;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ShippedFrom", referencedColumnName = "LocationId")
    private Location location;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customerId", referencedColumnName = "CustomerId")
    private Customer customer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "addressId", referencedColumnName = "AddressId")
    private Address address;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails;
}
