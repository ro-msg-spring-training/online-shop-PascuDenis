package ro.msg.learning.shop.controller;

import lombok.AllArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dto.OrderDTO;
import ro.msg.learning.shop.dto.OrderInputDTO;
import ro.msg.learning.shop.exception.OrderNotFoundException;
import ro.msg.learning.shop.service.OrderService;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrderController implements IController<OrderDTO, Integer> {

    @Autowired
    private final OrderService orderService;
    private static final Logger logger = LogManager.getLogger(ProductController.class.getName());


    @Override
    public OrderDTO getOne(Integer id) {
        return null;
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
        return orderService.createOrder(orderInputDTO);
    }

    @Override
    public OrderDTO update(OrderDTO entity) {
        return null;
    }

    @Override
    public void remove(Integer id) {

    }
}
