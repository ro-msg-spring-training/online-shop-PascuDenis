package ro.msg.learning.shop.modelDTO;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity(name = "Order")
@Table(name = "Order_")
public class Order implements Serializable {
    @Id
    private Integer oderId;
    private Integer locationId;
    private Integer customerId;
    private Date createdAt;
    private String addressCountry;
    private String addressCity;
    private String addressCounty;
    private String addressStreet;

    public Order() {
    }

    public Order(Integer oderId, Integer locationId, Integer customerId, Date createdAt, String addressCountry, String addressCity, String addressCounty, String addressStreet) {
        this.oderId = oderId;
        this.locationId = locationId;
        this.customerId = customerId;
        this.createdAt = createdAt;
        this.addressCountry = addressCountry;
        this.addressCity = addressCity;
        this.addressCounty = addressCounty;
        this.addressStreet = addressStreet;
    }
}
