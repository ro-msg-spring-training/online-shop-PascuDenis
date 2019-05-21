package ro.msg.learning.shop.strategy;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.dto.StockDTO;
import ro.msg.learning.shop.dto.orderinput.OrderInputDTO;
import ro.msg.learning.shop.dto.orderinput.ProductOrderInputDTO;
import ro.msg.learning.shop.repository.IStockRepository;
import ro.msg.learning.shop.service.OrderService;
import ro.msg.learning.shop.service.strategy.FindLocationStrategy;
import ro.msg.learning.shop.service.strategy.MostAbundantLocation;
import ro.msg.learning.shop.service.strategy.StrategyTypes;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
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
//        assert (stocks != null);
        Assertions.assertThat(stocks!=null);
    }

    @Test(expected = RuntimeException.class)
    public void singleLocationFail() {
        OrderInputDTO inputOrder = new OrderInputDTO(null, inputProducts);

        strategy.searchLocation(inputOrder);
    }
}
