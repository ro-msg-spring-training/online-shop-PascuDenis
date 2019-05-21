package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.dto.OrderDTO;
import ro.msg.learning.shop.dto.StockDTO;
import ro.msg.learning.shop.dto.orderinput.OrderInputDTO;
import ro.msg.learning.shop.dto.orderinput.ProductOrderInputDTO;
import ro.msg.learning.shop.exception.StockNotFoundException;
import ro.msg.learning.shop.mapping.OrderMapper;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.repository.*;
import ro.msg.learning.shop.service.strategy.FindLocationStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements IService<OrderDTO, Integer> {

    private final IAddressRepository addressRepository;
    private final ICustomerRepository customerRepository;
    private final ILocationRepository locationRepository;
    private final IOrderRepository orderRepository;
    private final IStockRepository stockRepository;

    @Autowired
    public OrderService(IAddressRepository addressRepository, ICustomerRepository customerRepository, ILocationRepository locationRepository, IOrderRepository orderRepository, IStockRepository stockRepository, FindLocationStrategy strategy) {
        this.addressRepository = addressRepository;
        this.customerRepository = customerRepository;
        this.locationRepository = locationRepository;
        this.orderRepository = orderRepository;
        this.stockRepository = stockRepository;
        this.strategy = strategy;
    }

    private FindLocationStrategy strategy;

    @Override
    @Transactional
    public OrderDTO findOne(Integer id) {
        return new OrderMapper(addressRepository, customerRepository, locationRepository).convertToDto(orderRepository.findById(id).get());
    }

    @Override
    @Transactional
    public List<OrderDTO> findAll() {
        OrderMapper mapper = new OrderMapper(addressRepository, customerRepository, locationRepository);
        List<Order> orderList = (List<Order>) orderRepository.findAll();
        List<OrderDTO> orderReturnList = new ArrayList<>();
        for (Order order : orderList){
            orderReturnList.add(mapper.convertToDto(order));
        }
        return orderReturnList;
//        return StreamSupport.stream(orderRepository.findAll().spliterator(), false).map(mapper::convertToDto).
//                collect(Collectors.toList());
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
        int updatedStock = 0;
        List<StockDTO> searchedProducts = new ArrayList<>();
        try {
            searchedProducts = strategy.searchLocation(inputOrder);
        } catch (StockNotFoundException e) {
            System.out.println(e);
        }

        for (StockDTO s : searchedProducts) {
            System.out.println(s);
        }
        Address orderAddress = inputOrder.getAddress();
        Order order = null;
        try {
            order = new Order(
                    LocalDateTime.now().withNano(0),
                    searchedProducts.get(0).getLocation(),
                    customerRepository.findById(1).get(),
                    orderAddress
            );
            System.out.println("\n" + order);
            orderRepository.save(order);

            for (ProductOrderInputDTO product : inputOrder.getProductInputList()) {
                for (StockDTO stock : searchedProducts) {
                    if (stock.getProductId().equals(product.getProductId())) {
                        int initialStock = stock.getQuantity();
                        Integer stockIdAfterProductIdAndLocationId = stockRepository.findStockIdAfterProductIdAndLocationId(product.getProductId(), stock.getLocation().getId());
                        Stock foundStock = stockRepository.findById(stockIdAfterProductIdAndLocationId).get();
                        System.out.println(foundStock);
                        foundStock.setQuantity(foundStock.getQuantity() - initialStock);
                        stockRepository.save(foundStock);
                        System.out.println("initial Stock" + initialStock);
                    }
                }
            }
            System.out.println(updatedStock);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.toString());
        }

        return order;
    }
}
