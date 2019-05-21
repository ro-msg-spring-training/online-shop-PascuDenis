package ro.msg.learning.shop.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import ro.msg.learning.shop.configuration.SomeOrderConfig;
import ro.msg.learning.shop.dto.OrderDTO;
import ro.msg.learning.shop.dto.orderinput.OrderInputDTO;
import ro.msg.learning.shop.dto.orderinput.ProductOrderInputDTO;
import ro.msg.learning.shop.exception.StockNotFoundException;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.repository.IOrderRepository;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
//@WebAppConfiguration
@Configuration
//@ComponentScan("ro.msg.learning.shop.repository")
public class CreateOrderIntegrationTest {

    @Mock
    private OrderService orderService;

    @Before
    public void init() throws Exception {
    }

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void createOrderSucessfully() {
        ProductOrderInputDTO inputProduct1 = new ProductOrderInputDTO(1, 20);
        ProductOrderInputDTO inputProduct2 = new ProductOrderInputDTO(2, 20);
        ProductOrderInputDTO inputProduct3 = new ProductOrderInputDTO(3, 20);

        List<ProductOrderInputDTO> inputProducts = new ArrayList<>();
        inputProducts.add(inputProduct1);
        inputProducts.add(inputProduct2);
        inputProducts.add(inputProduct3);


        OrderInputDTO inputOrder = new OrderInputDTO(null, inputProducts);

//        for (OrderDTO o : orderService.findAll()){
//            System.out.println(o);
//        }
        Order order = orderService.createOrder(inputOrder);
        assert (order != null);
    }

    @Test
    public void createOrderFailure() {
        try {
            ProductOrderInputDTO inputProduct1 = new ProductOrderInputDTO(1, 20);
            ProductOrderInputDTO inputProduct2 = new ProductOrderInputDTO(2, 500);
            ProductOrderInputDTO inputProduct3 = new ProductOrderInputDTO(3, 20);

            List<ProductOrderInputDTO> inputProducts = new ArrayList<>();
            inputProducts.add(inputProduct1);
            inputProducts.add(inputProduct2);
            inputProducts.add(inputProduct3);


            OrderInputDTO inputOrder = new OrderInputDTO(null, inputProducts);

            for (OrderDTO o : orderService.findAll()) {
                System.out.println(o);
            }
            Order order = orderService.createOrder(inputOrder);
            assert (order != null);
        } catch (StockNotFoundException | AssertionError e) {
            System.out.println(e);
        }
    }
}
