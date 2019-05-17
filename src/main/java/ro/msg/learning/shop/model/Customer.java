package ro.msg.learning.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@ToString
@EqualsAndHashCode
@Entity(name = "Customer")
@Table(name="Customer")
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, name = "CustomerId")
    private Integer id;

    @NonNull
    @Column(nullable = false, name = "FirstName")
    //@Column(name = "FirstName")
    private String firstName;

    @NonNull
    @Column(nullable = false, name = "LastName")
    //@Column(name = "LastName")
    private String lastName;

    @NonNull
    @Column(nullable = false, name = "Username")
    //@Column(name = "Username")
    private String username;

    @NonNull
    @Column(nullable = false, name = "Password")
    //@Column(name = "Password")
    private String password;

    @NonNull
    @Column(nullable = false, name = "EmailAddress")
    //@Column(name = "EmailAddress")
    private String email;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Order> orders;

    public Customer(String firstName, String lastName, String username, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
    }

}
