package ro.msg.learning.shop.service;

import lombok.AllArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.dto.OrderDTO;
import ro.msg.learning.shop.dto.StockDTO;
import ro.msg.learning.shop.dto.orderinput.OrderInputDTO;
import ro.msg.learning.shop.dto.orderinput.ProductOrderInputDTO;
import ro.msg.learning.shop.exception.FailedToCreateOrderProductException;
import ro.msg.learning.shop.exception.FailedToCreateOrderStockException;
import ro.msg.learning.shop.exception.ProductNotFoundException;
import ro.msg.learning.shop.exception.StockNotFoundException;
import ro.msg.learning.shop.mapping.OrderMapper;
import ro.msg.learning.shop.model.*;
import ro.msg.learning.shop.repository.*;
import ro.msg.learning.shop.service.strategy.FindLocationStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService implements IService<OrderDTO, Integer> {

    private final IAddressRepository addressRepository;
    private final ICustomerRepository customerRepository;
    private final ILocationRepository locationRepository;
    private final IOrderRepository orderRepository;
    private final IStockRepository stockRepository;

    private FindLocationStrategy strategy;

    private static final Logger logger = LogManager.getLogger(OrderService.class.getName());

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
        for (Order order : orderList) {
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
        for (Stock stock : stockRepository.findAll()) {
            logger.info(stock);
        }

        int updatedStock = 0;
        Integer productId = -1;
        List<StockDTO> searchedProducts = new ArrayList<>();
        try {
            searchedProducts = strategy.searchLocation(inputOrder);
        } catch (StockNotFoundException e) {
            System.out.println(e);
            throw new FailedToCreateOrderStockException();
        } catch (ProductNotFoundException e){
            System.out.println(e);
            throw new FailedToCreateOrderProductException();
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

            for (ProductOrderInputDTO product : inputOrder.getProductInputList()) {
                for (StockDTO stock : searchedProducts) {
                    if (stock.getProductId() != null) {
                        productId = stock.getProductId();
                    } else if (stock.getProduct().getId() != null) {
                        productId = stock.getProduct().getId();
                    }

                    System.out.println("    " + product.getProductId());
                    if (productId == product.getProductId()) {
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
            System.out.println("Salut " + e.toString());
        }

        System.out.println("\n" + order);
        orderRepository.save(order);
        return order;
    }
}
