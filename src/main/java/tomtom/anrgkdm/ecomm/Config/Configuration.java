package tomtom.anrgkdm.ecomm.Config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import tomtom.anrgkdm.ecomm.rest.Controller;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public CircuitBreaker defaultCircuitBreaker() {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .slidingWindow(10, 4, CircuitBreakerConfig.SlidingWindowType.TIME_BASED)
                .waitDurationInOpenState(Duration.ofMillis(6000))
                .permittedNumberOfCallsInHalfOpenState(3)
                .recordExceptions(IOException.class, TimeoutException.class, DataIntegrityViolationException.class)
                .build();

        CircuitBreakerRegistry circuitBreakerRegistry =
                CircuitBreakerRegistry.of(circuitBreakerConfig);

        return circuitBreakerRegistry.circuitBreaker("productInventoryBreaker");
    }


}
