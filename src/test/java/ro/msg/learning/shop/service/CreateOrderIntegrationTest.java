package ro.msg.learning.shop.service;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.ShopApplication;
import ro.msg.learning.shop.dto.OrderDTO;
import ro.msg.learning.shop.dto.orderinput.OrderInputDTO;
import ro.msg.learning.shop.dto.orderinput.ProductOrderInputDTO;
import ro.msg.learning.shop.exception.StockNotFoundException;
import ro.msg.learning.shop.model.Order;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShopApplication.class)
@Configuration
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

        for (OrderDTO o : orderService.findAll()){
            System.out.println(o);
        }
        Order order = orderService.createOrder(inputOrder);
        Assertions.assertThat(order).isNotEqualTo(null);
        System.out.println("Successfully created an order");

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
        } catch (StockNotFoundException e) {
            System.out.println("Failed to create an order due to missing stocks");
        } catch (RuntimeException e){
            System.out.println("Some other error occurred");
        }
    }
}
