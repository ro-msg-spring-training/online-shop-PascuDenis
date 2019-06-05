package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.model.Location;
import ro.msg.learning.shop.model.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {
  private Integer id;
  private Integer productId;
  private Integer quantity;
  private Integer locationId;

    public StockDTO(Integer productId, Integer quantity, Location location) {
        this.productId = productId;
        this.quantity = quantity;
        this.location = location;
    }

    private Product product;
    private Location location;

}
