package ro.msg.learning.shop.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.service.strategy.FindLocationStrategy;
import ro.msg.learning.shop.service.strategy.MostAbundantLocation;
import ro.msg.learning.shop.service.strategy.SingleLocation;
import ro.msg.learning.shop.service.strategy.StrategyTypes;

@Configuration
public class StrategyConfig {
    @Bean
    public FindLocationStrategy chooseStrategy(@Value("${strategy.type}") StrategyTypes strategy) {
        switch (strategy) {
            case SINGLELOCATION:
                return new SingleLocation();
            case MOSTABUNDANTLOCATION:
                return new MostAbundantLocation();
        }
        return null;
    }
}
