package ro.msg.learning.shop.configuration;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.repository.ILocationRepository;
import ro.msg.learning.shop.repository.IProductRepository;
import ro.msg.learning.shop.repository.IStockRepository;
import ro.msg.learning.shop.service.strategy.FindLocationStrategy;
import ro.msg.learning.shop.service.strategy.MostAbundantLocation;
import ro.msg.learning.shop.service.strategy.SingleLocation;

@Configuration
@AllArgsConstructor
public class StrategyConfig {
    public enum StrategyTypes {
        SINGLELOCATION, MOSTABUNDANTLOCATION
    }

    private final IProductRepository productRepository;
    private final ILocationRepository locationRepository;
    private final IStockRepository stockRepository;

    @Bean
    public FindLocationStrategy chooseStrategy(@Value("${strategy.type}") StrategyTypes strategy) {
        switch (strategy) {
            case SINGLELOCATION:
                return new SingleLocation(productRepository, locationRepository, stockRepository);
            case MOSTABUNDANTLOCATION:
                return new MostAbundantLocation(stockRepository);
        }
        return null;
    }
}
