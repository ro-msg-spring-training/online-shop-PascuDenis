package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Entity(name = "Location")
@Table(name = "Location")
public class Location implements Serializable {
    @Id
    @Column(updatable = false)
    private Integer id;

    @NonNull
    @Column(nullable = false)
    private String name;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "addressId", referencedColumnName = "id")
    private Address address;

    public Location(@NonNull String name, @NonNull Address address) {
        this.name = name;
        this.address = address;
    }
}
