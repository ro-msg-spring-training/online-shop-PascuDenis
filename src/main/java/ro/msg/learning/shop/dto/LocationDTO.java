package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.model.Address;

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
}
