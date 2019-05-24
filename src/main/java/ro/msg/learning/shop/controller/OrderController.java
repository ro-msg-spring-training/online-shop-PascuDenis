package ro.msg.learning.shop.controller;

import lombok.AllArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dto.OrderDTO;
import ro.msg.learning.shop.dto.orderinput.OrderInputDTO;
import ro.msg.learning.shop.exception.FailedToCreateOrderProductException;
import ro.msg.learning.shop.exception.FailedToCreateOrderStockException;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.service.OrderService;
import ro.msg.learning.shop.service.UserDetailService;

import java.util.List;

@AllArgsConstructor
@RestController
public class OrderController implements IController<OrderDTO, Integer> {

    private final OrderService orderService;
    private final UserDetailService userDetailService;

    private static final Logger logger = LogManager.getLogger(ProductController.class.getName());

    @Override
    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderDTO> getOne(@PathVariable Integer id) {
        return new ResponseEntity<>(orderService.findOne(id), HttpStatus.OK);
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
    public ResponseEntity<Order> createOrder(@RequestBody OrderInputDTO order) {
        try {
            return new ResponseEntity<>(orderService.createOrder(order), HttpStatus.OK);
        } catch (FailedToCreateOrderStockException e) {
            Order failedOrder = new Order();
            failedOrder.setId(-1);
            return new ResponseEntity<>(failedOrder, HttpStatus.NOT_FOUND);
        } catch (FailedToCreateOrderProductException e) {
            Order failedOrder = new Order();
            failedOrder.setId(-2);
            return new ResponseEntity<>(failedOrder, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user")
    public String loggedIn() {
        return "UserModel has loggeed in";
    }
}
