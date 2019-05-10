package ro.msg.learning.shop.mapping;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.OrderDTO;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.repository.IAddressRepository;
import ro.msg.learning.shop.repository.ICustomerRepository;
import ro.msg.learning.shop.repository.ILocationRepository;

@Component
@AllArgsConstructor
public class OrderMapper implements Mapper<Order, OrderDTO>{
    private IAddressRepository addressRepository;
    private ICustomerRepository customerRepository;
    private ILocationRepository locationRepository;

    @Override
    public Order convertToEntity(OrderDTO dto) {
        Order order = new Order();
        order.setId(dto.getId());
        order.setLocation(locationRepository.findById(dto.getLocationId()).orElse(null));
        order.setCustomer(customerRepository.findById(dto.getCustomerId()).orElse(null));
        order.setCreatedAt(dto.getCreatedAt());
        order.setAddress(addressRepository.findById(dto.getAddressId()).orElse(null));
        return order;
    }

    @Override
    public OrderDTO convertToDto(Order entity) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(entity.getId());
        orderDTO.setLocation(entity.getLocation());
        orderDTO.setCustomer(entity.getCustomer());
        orderDTO.setCreatedAt(entity.getCreatedAt());
        orderDTO.setAddress(entity.getAddress());
        return orderDTO;
    }
}
