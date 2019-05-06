package ro.msg.learning.shop.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@ToString
@EqualsAndHashCode
@Entity(name = "Customer")
@Table(name="Customer")
public class Customer implements Serializable{

    @Id
    @Column(updatable = false)
    private Integer id;

    @NonNull
    @Column(nullable = false)
    //@Column(name = "FirstName")
    private String firstName;

    @NonNull
    @Column(nullable = false)
    //@Column(name = "LastName")
    private String lastName;

    @NonNull
    @Column(nullable = false)
    //@Column(name = "Username")
    private String username;

    @NonNull
    @Column(nullable = false)
    //@Column(name = "Password")
    private String password;

    @NonNull
    @Column(nullable = false)
    //@Column(name = "EmailAddress")
    private String email;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders;

    public Customer(){}

    public Customer(Integer id, String firstName, String lastName, String username, String password, String email, List<Order> orderList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
    }

}
