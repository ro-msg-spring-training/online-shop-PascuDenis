package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.configuration.StrategyConfig;
import ro.msg.learning.shop.dto.OrderDTO;
import ro.msg.learning.shop.dto.OrderInputDTO;
import ro.msg.learning.shop.dto.ProductDTO;
import ro.msg.learning.shop.dto.StockDTO;
import ro.msg.learning.shop.mapping.OrderMapper;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.repository.IAddressRepository;
import ro.msg.learning.shop.repository.ICustomerRepository;
import ro.msg.learning.shop.repository.ILocationRepository;
import ro.msg.learning.shop.repository.IOrderRepository;
import ro.msg.learning.shop.service.strategy.FindLocationStrategy;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class OrderService implements IService<OrderDTO, Integer> {
    private final IAddressRepository addressRepository;
    private final ICustomerRepository customerRepository;
    private final ILocationRepository locationRepository;
    private final IOrderRepository orderRepository;

    @Autowired
    private FindLocationStrategy strategy;

    @Autowired
    private StrategyConfig config;

    @Override
    @Transactional
    public OrderDTO findOne(Integer id) {
        return null;
    }

    @Override
    @Transactional
    public List<OrderDTO> findAll() {
        OrderMapper mapper = new OrderMapper(addressRepository, customerRepository, locationRepository);
        return StreamSupport.stream(orderRepository.findAll().spliterator(), false).map(mapper::convertToDto).
                collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderDTO save(OrderDTO entity) {
        return null;
    }

    @Override
    @Transactional
    public OrderDTO update(OrderDTO entity) {
        return null;
    }

    @Override
    @Transactional
    public void remove(Integer id) {

    }

    public OrderDTO createOrder(OrderInputDTO orderInputDTO) {

        strategy = config.chooseStrategy();
//        List<StockDTO> stockList =  strategy.searchLocation(order.getCreatedAt(), order.getAddressId(), products);
//        return order;
        return null;
    }
}
