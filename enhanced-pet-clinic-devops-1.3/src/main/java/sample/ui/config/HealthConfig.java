package sample.ui.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HealthConfig {

    @Bean
    public HealthIndicator myCustomIndicator() {
        return new HealthIndicator() {
            @Override
            public Health health() {
                return Health.up().withDetail("Hello", "World!").build();
            }
        };
    }
}
