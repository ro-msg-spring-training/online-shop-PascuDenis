package ro.msg.learning.shop.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.service.strategy.FindLocationStrategy;
import ro.msg.learning.shop.service.strategy.MostAbundantLocation;
import ro.msg.learning.shop.service.strategy.SingleLocation;

@Configuration
public class StrategyConfig {

    @Value("${strategy.type}")
    private String strategy;

    @Bean
    public FindLocationStrategy chooseStrategy(){
        switch (strategy){
            case "SingleLocation":
                return new SingleLocation();
            case "MostAbundantLocation":
                return new MostAbundantLocation();
        }
        return null;
    }
}
