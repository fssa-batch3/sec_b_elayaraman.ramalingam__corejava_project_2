package in.fssa.sundaratravels;

import in.fssa.sundaratravels.model.Route;
import in.fssa.sundaratravels.service.RouteServices;
import in.fssa.sundaratravels.exception.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class RouteTest {

    private RouteServices services;

    @BeforeEach
    public void setup() {
        services = new RouteServices();
    }

    @Test
    public void testCreateRouteWithNullInput() {
        Exception exception = assertThrows(Exception.class, () -> {
            services.createRoute(null);
        });
        String expectedMessage = "Route is null";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testCreateRouteWithEmptyFromLocation() {
        Route route = new Route();
        route.setFrom_location("");
        route.setTo_location("Kalakadu");
        route.setBasePrice(BigDecimal.valueOf(100.50));

        Exception exception = assertThrows(Exception.class, () -> {
            services.createRoute(route);
        });
        String expectedMessage = "From location cannot be empty or null";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testCreateRouteWithEmptyToLocation() {
        Route route = new Route();
        route.setFrom_location("fjkvf");
        route.setTo_location("");
        route.setBasePrice(BigDecimal.valueOf(100.50));

        Exception exception = assertThrows(Exception.class, () -> {
            services.createRoute(route);
        });
        String expectedMessage = "To location cannot be empty or null";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testValidRoute() {
        Route route = new Route();
        route.setFrom_location("Sour145");
        route.setTo_location("Destination");
        route.setBasePrice(BigDecimal.valueOf(100));

        assertDoesNotThrow(() -> {
            services.createRoute(route);
        });
    }

    @Test
    public void testNullRoute() {
        Exception exception = assertThrows(Exception.class, () -> {
            services.createRoute(null);
        });
        assertEquals("Route is null", exception.getMessage());
    }

    @Test
    public void testSameFromAndToLocations() {
        Route route = new Route();
        route.setFrom_location("Location");
        route.setTo_location("Location");
        route.setBasePrice(BigDecimal.valueOf(100));

        Exception exception = assertThrows(Exception.class, () -> {
            services.createRoute(route);
        });
        assertEquals("Both cannot be same", exception.getMessage());
    }

    @Test
    public void testValidBasePrice() {
        Route route = new Route();
        route.setFrom_location("Suuurce121");
        route.setTo_location("Destination");
        route.setBasePrice(BigDecimal.valueOf(100));

        assertDoesNotThrow(() -> {
            services.createRoute(route);
        });
    }

    @Test
    public void testEmptyFromLocation() {
        Route route = new Route();
        route.setFrom_location("");
        route.setTo_location("Destination");
        route.setBasePrice(BigDecimal.valueOf(100));

        Exception exception = assertThrows(Exception.class, () -> {
            services.createRoute(route);
        });
        assertEquals("From location cannot be empty or null", exception.getMessage());
    }

    @Test
    public void testEmptyToLocation() {
        Route route = new Route();
        route.setFrom_location("Source");
        route.setTo_location("");
        route.setBasePrice(BigDecimal.valueOf(100));

        Exception exception = assertThrows(Exception.class, () -> {
            services.createRoute(route);
        });
        assertEquals("To location cannot be empty or null", exception.getMessage());
    }

    @Test
    public void testNegativeBasePrice() {
        Route route = new Route();
        route.setFrom_location("Source");
        route.setTo_location("Destination");
        route.setBasePrice(BigDecimal.valueOf(-100));

        Exception exception = assertThrows(Exception.class, () -> {
            services.createRoute(route);
        });
        assertEquals("Base price must be a positive value", exception.getMessage());
    }

    @Test
    public void testZeroBasePrice() {
        Route route = new Route();
        route.setFrom_location("Source");
        route.setTo_location("Destination");
        route.setBasePrice(BigDecimal.ZERO);

        Exception exception = assertThrows(Exception.class, () -> {
            services.createRoute(route);
        });
        assertEquals("Base price must be a positive value", exception.getMessage());
    }

    @Test
    public void testValidBasePriceWithDecimals() {
        Route route = new Route();
        route.setFrom_location("Soureee1");
        route.setTo_location("Destination");
        route.setBasePrice(BigDecimal.valueOf(99.99));

        assertDoesNotThrow(() -> {
            services.createRoute(route);
        });
    }

    @Test
    public void testLongFromLocation() {
        Route route = new Route();
        route.setFrom_location("A very long source location name exceeding the limit of characters qwertyuiopqwertyuiopqwertyuiopqwertyuiop");
        route.setTo_location("Destination");
        route.setBasePrice(BigDecimal.valueOf(100));

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            services.createRoute(route);
        });
        assertEquals("From location exceeds maximum length", exception.getMessage());
    }

    @Test
    public void testLongToLocation() {
        Route route = new Route();
        route.setFrom_location("Source");
        route.setTo_location("A very long destination location name exceeding the limit of character qwertyuiopqwertyuiopqwertyuiopqwertyuiop");
        route.setBasePrice(BigDecimal.valueOf(100));

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            services.createRoute(route);
        });
        assertEquals("To location exceeds maximum length", exception.getMessage());
    }
}
