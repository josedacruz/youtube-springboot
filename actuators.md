# Actuators

## Examples of some  Actuator endpoints (adap the domain and port to your needs)

- http://localhost:8080/actuator/health
- http://localhost:8080/actuator/info
- http://localhost:8080/actuator/metrics
- http://localhost:8080/actuator/metrics/jvm.memory.used
- http://localhost:8080/actuator/loggers
- http://localhost:8080/actuator/threaddump


## Example of a custom health indicator
```java
@Component
public class MyHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        boolean isServiceUp = checkCustomService();
        return isServiceUp ? Health.up().build() : Health.down().withDetail("Error", "The Finances Server isn't working").build();
    }

    private boolean checkCustomService() {
        return true; // Simulate health check
    }
}

```