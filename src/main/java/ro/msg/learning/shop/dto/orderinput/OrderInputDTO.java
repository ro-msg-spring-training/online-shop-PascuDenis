package ro.msg.learning.shop.dto.orderinput;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ro.msg.learning.shop.model.Address;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderInputDTO {
//    private LocalDateTime timestamp;
    private Address address;
      private List<ProductOrderInputDTO> productInputList;
}
