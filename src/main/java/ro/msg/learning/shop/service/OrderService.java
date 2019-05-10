package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.OrderDTO;
import ro.msg.learning.shop.mapping.OrderMapper;
import ro.msg.learning.shop.repository.IAddressRepository;
import ro.msg.learning.shop.repository.ICustomerRepository;
import ro.msg.learning.shop.repository.ILocationRepository;
import ro.msg.learning.shop.repository.IOrderRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class OrderService implements IService<OrderDTO, Integer> {
    private final IAddressRepository addressRepository;
    private final ICustomerRepository customerRepository;
    private final ILocationRepository locationRepository;
    private final IOrderRepository orderRepository;

    @Override
    public OrderDTO findOne(Integer id) {
        return null;
    }

    @Override
    public List<OrderDTO> findAll() {
        OrderMapper mapper = new OrderMapper(addressRepository, customerRepository, locationRepository);
        return StreamSupport.stream(orderRepository.findAll().spliterator(), false).map(mapper::convertToDto).
                collect(Collectors.toList());
    }

    @Override
    public OrderDTO save(OrderDTO entity) {
        return null;
    }

    @Override
    public OrderDTO update(OrderDTO entity) {
        return null;
    }

    @Override
    public void remove(Integer id) {

    }

    public OrderDTO createOrder(){
        //TODO
        return null;
    }
}
