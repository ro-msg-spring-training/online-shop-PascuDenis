package ro.msg.learning.shop.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dto.OrderDTO;
import ro.msg.learning.shop.dto.orderinput.OrderInputDTO;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.service.OrderService;
import ro.msg.learning.shop.service.StockService;

import java.util.List;

@RestController
public class OrderController implements IController<OrderDTO, Integer> {

    private final StockService stockService;
    private final OrderService orderService;

    @Autowired
    public OrderController(StockService stockService, OrderService orderService) {
        this.stockService = stockService;
        this.orderService = orderService;
    }

    private static final Logger logger = LogManager.getLogger(ProductController.class.getName());

//    @Autowired
//    public OrderController(StockService stockService, OrderService orderService){
//        this.stockService = stockService;
//        this.orderService = orderService;
//    }

    @Override
    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderDTO> getOne(@PathVariable Integer id) {
        return new ResponseEntity<>(orderService.findOne(id), HttpStatus.OK) ;
    }

    @Override
    @GetMapping("/orders")
    public List<OrderDTO> getAll() {
        return orderService.findAll();
    }

    @Override
    public OrderDTO save(@RequestBody OrderDTO entity) {
        return null;
    }

    @PostMapping("/orders/s")
    public OrderDTO save(@RequestBody OrderInputDTO orderInputDTO) {
        return null;
    }

    @Override
    public OrderDTO update(OrderDTO entity) {
        return null;
    }

    @Override
    public void remove(Integer id) {

    }

    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestBody OrderInputDTO order){
//        order.setTimestamp(LocalDateTime.now().withNano(0));
        return new ResponseEntity<>(orderService.createOrder(order), HttpStatus.OK);
    }

}
