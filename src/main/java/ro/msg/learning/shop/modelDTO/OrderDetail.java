package ro.msg.learning.shop.modelDTO;

import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "OrderDetail")
public class OrderDetail implements Serializable {
    private Integer orderId;
    private Integer productId;
    private Integer quantity;

    public OrderDetail() {
    }

    public OrderDetail(Integer orderId, Integer productId, Integer quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }
}
