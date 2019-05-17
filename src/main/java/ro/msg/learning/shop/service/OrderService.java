package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.dto.OrderDTO;
import ro.msg.learning.shop.dto.StockDTO;
import ro.msg.learning.shop.dto.orderinput.OrderInputDTO;
import ro.msg.learning.shop.dto.orderinput.ProductOrderInputDTO;
import ro.msg.learning.shop.mapping.OrderMapper;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.repository.*;
import ro.msg.learning.shop.service.strategy.FindLocationStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class OrderService implements IService<OrderDTO, Integer> {
    //TODO: constructor injection

    private final IAddressRepository addressRepository;
    private final ICustomerRepository customerRepository;
    private final ILocationRepository locationRepository;
    private final IOrderRepository orderRepository;
    private final IOrderDetailRepository orderDetailRepository;
    private final IProductCategoryRepository productCategoryRepository;
    private final ISupplierRepository supplierRepository;

    @Autowired
    private FindLocationStrategy strategy;
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

    @Transactional
    public Order createOrder(OrderInputDTO inputOrder) {
        List<StockDTO> searchedProducts = new ArrayList<>();
        try {
            searchedProducts = strategy.searchLocation(inputOrder);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(StockDTO s : searchedProducts){
            System.out.println(s);
        }
//        LocalDateTime orderTimestamp = inputOrder.getTimestamp();
        Address orderAddress = inputOrder.getAddress();
        Order order = new Order(
                LocalDateTime.now().withNano(0),
                searchedProducts.get(0).getLocation(),
                customerRepository.findById(1).get(),
                orderAddress
        );
        System.out.println("\n" +order);
        orderRepository.save(order);

        for (ProductOrderInputDTO product : inputOrder.getProductInputList()) {
            for (StockDTO stock : searchedProducts) {
                if (stock.getProductId().equals(product.getProductId())) {
                    stock.setQuantity(stock.getQuantity() - product.getQuantity());
                }
            }
        }
        return order;
    }
}
