package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.model.Address;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.model.Revenue;
import ro.msg.learning.shop.model.Stock;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDTO {
    private Integer id;
    private String name;
    private Integer addressId;

    public LocationDTO(Integer id, String name, Integer addressId) {
        this.id = id;
        this.name = name;
        this.addressId = addressId;
    }

    private Address address;
    private List<Order> orders;
    private List<Stock> stocks;
    private List<Revenue> revenues;
}
