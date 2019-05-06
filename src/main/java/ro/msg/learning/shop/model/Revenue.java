package ro.msg.learning.shop.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@ToString
@EqualsAndHashCode
@Entity(name = "Revenue")
@Table(name = "Revenue")
@NoArgsConstructor
@AllArgsConstructor

public class Revenue implements Serializable {

    @Id
    @Column(updatable = false)
    private Integer id;
//    @ManyToOne()
//    @JoinColumn(name = "LocationID", insertable = false, updatable = false)

    @ManyToOne
    @JoinColumn(name = "locationId", referencedColumnName = "id")
    private Location location;

    @NonNull
    @Column(name="Date_", nullable = false)
    private Date date;

    @NonNull
    @Column(name="Sum_" , nullable = false)
    private Long sum;

    public Revenue(Location location, @NonNull Date date, @NonNull Long sum) {
        this.location = location;
        this.date = date;
        this.sum = sum;
    }
}
