package ro.msg.learning.shop.strategy;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.ShopApplication;
import ro.msg.learning.shop.dto.StockDTO;
import ro.msg.learning.shop.dto.orderinput.OrderInputDTO;
import ro.msg.learning.shop.dto.orderinput.ProductOrderInputDTO;
import ro.msg.learning.shop.exception.StockNotFoundException;
import ro.msg.learning.shop.repository.IStockRepository;
import ro.msg.learning.shop.service.strategy.MostAbundantLocation;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShopApplication.class)
@ActiveProfiles("test")
public class MostAbundantLocationTest {
    @Mock
    private IStockRepository stockRepository;
    @InjectMocks
    private MostAbundantLocation strategy;

    private ProductOrderInputDTO inputProduct1;
    private ProductOrderInputDTO inputProduct2;
    private ProductOrderInputDTO inputProduct3;

    private List<ProductOrderInputDTO> inputProducts;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        inputProduct1 = new ProductOrderInputDTO(1, 20);
        inputProduct2 = new ProductOrderInputDTO(2, 20);
        inputProduct3 = new ProductOrderInputDTO(3, 20);
        inputProducts = new ArrayList<>();

        inputProducts.add(inputProduct1);
        inputProducts.add(inputProduct2);
        inputProducts.add(inputProduct3);
    }

    @Test
    public void singleLocationSuccess() {
        OrderInputDTO inputOrder = new OrderInputDTO(null, inputProducts);

        List<StockDTO> stocks = strategy.searchLocation(inputOrder);
        Assertions.assertThat(stocks).isNotEqualTo(null);
        System.out.println("Successfully created an order with most abundant location strategy");
    }

    @Test
    public void singleLocationFail() {
        OrderInputDTO inputOrder = new OrderInputDTO(null, inputProducts);
        try {
            strategy.searchLocation(inputOrder);
        } catch (StockNotFoundException e){
            System.out.println("Failed to create an order with most abundant location strategy");
        } catch (RuntimeException e){
            System.out.println("Some other error occurred");
        }
    }
}
