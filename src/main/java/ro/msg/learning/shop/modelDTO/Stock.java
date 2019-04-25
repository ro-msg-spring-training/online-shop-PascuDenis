package ro.msg.learning.shop.modelDTO;

import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "Stock")
public class Stock implements Serializable {

    private Integer productId;
    private Integer locationId;
    private Integer quantity;

    public Stock() {
    }

    public Stock(Integer productId, Integer locationId, Integer quantity) {
        this.productId = productId;
        this.locationId = locationId;
        this.quantity = quantity;
    }
}
