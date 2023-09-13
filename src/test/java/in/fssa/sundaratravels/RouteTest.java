package in.fssa.sundaratravels;

import in.fssa.sundaratravels.exception.ServicesException;
import in.fssa.sundaratravels.model.Route;
import in.fssa.sundaratravels.service.RouteServices;
import static in.fssa.sundaratravels.util.RandomStringGenerator.generateRandomString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

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
        route.setFromLocation("");
        route.setToLocation("Kalakadu");
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
        route.setFromLocation("fjkvf");
        route.setToLocation("");
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
        route.setFromLocation(generateRandomString());
        route.setToLocation("Destination");
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
        route.setFromLocation("Location");
        route.setToLocation("Location");
        route.setBasePrice(BigDecimal.valueOf(100));

        Exception exception = assertThrows(Exception.class, () -> {
            services.createRoute(route);
        });
        assertEquals("Both cannot be same", exception.getMessage());
    }


    @Test
    public void testEmptyFromLocation() {
        Route route = new Route();
        route.setFromLocation("");
        route.setToLocation("Destination");
        route.setBasePrice(BigDecimal.valueOf(100));

        Exception exception = assertThrows(Exception.class, () -> {
            services.createRoute(route);
        });
        assertEquals("From location cannot be empty or null", exception.getMessage());
    }

    @Test
    public void testEmptyToLocation() {
        Route route = new Route();
        route.setFromLocation("Source");
        route.setToLocation("");
        route.setBasePrice(BigDecimal.valueOf(100));

        Exception exception = assertThrows(Exception.class, () -> {
            services.createRoute(route);
        });
        assertEquals("To location cannot be empty or null", exception.getMessage());
    }

    @Test
    public void testNegativeBasePrice() {
        Route route = new Route();
        route.setFromLocation("Source");
        route.setToLocation("Destination");
        route.setBasePrice(BigDecimal.valueOf(-100));

        Exception exception = assertThrows(Exception.class, () -> {
            services.createRoute(route);
        });
        assertEquals("Base price must be a positive value", exception.getMessage());
    }

    @Test
    public void testZeroBasePrice() {
        Route route = new Route();
        route.setFromLocation("Source");
        route.setToLocation("Destination");
        route.setBasePrice(BigDecimal.ZERO);

        Exception exception = assertThrows(Exception.class, () -> {
            services.createRoute(route);
        });
        assertEquals("Base price must be a positive value", exception.getMessage());
    }

    @Test
    public void testLongFromLocation() {
        Route route = new Route();
        route.setFromLocation("A very long source location name exceeding the limit of characters qwertyuiopqwertyuiopqwertyuiopqwertyuiop");
        route.setToLocation("Destination");
        route.setBasePrice(BigDecimal.valueOf(100));

        ServicesException exception = assertThrows(ServicesException.class, () -> {
            services.createRoute(route);
        });
        assertEquals("From location exceeds maximum length", exception.getMessage());
    }

    @Test
    public void testLongToLocation() {
        Route route = new Route();
        route.setFromLocation("Source");
        route.setToLocation("A very long destination location name exceeding the limit of character qwertyuiopqwertyuiopqwertyuiopqwertyuiop");
        route.setBasePrice(BigDecimal.valueOf(100));

        ServicesException exception = assertThrows(ServicesException.class, () -> {
            services.createRoute(route);
        });
        assertEquals("To location exceeds maximum length", exception.getMessage());
    }

    @Test
    public void testGetByFromLocationSuccess() {
        assertDoesNotThrow(() -> {
            List<Route> routes = services.getByFromLocation("Salem");
            assertFalse(routes.isEmpty());
        });
    }

    @Test
    public void testGetByFromLocationNotFound() {
        assertThrows(Exception.class, () -> {
            List<Route> routes = services.getByFromLocation("Chennai");
            assertTrue(routes.isEmpty());
        });
    }

    @Test
    public void testGetByToLocationSuccess() {
        assertDoesNotThrow(() -> {
            List<Route> routes = services.getByToLocation("Chennai");
            assertFalse(routes.isEmpty());
        });
    }

    @Test
    public void testGetByToLocationNotFound() {
        assertThrows(Exception.class, () -> {
            List<Route> routes = services.getByToLocation("Nonexistent");
            assertTrue(routes.isEmpty());
        });
    }
    
    public void testRouteStatusChangeSuccess() {
    	assertDoesNotThrow(()->{
    		services.switchRouteStatus(1);
    	});
    }

    @Test
    public void testGetByFromLocationAndToLocationSuccess() {
        assertDoesNotThrow(() -> {
            Route route = services.getByFromLocationAndTolocation("Salem", "Chennai");
            assertNotNull(route);
        });
    }
    @Test
    public void testUpdateRoutePriceNonexistentRoute() {
        Exception exception = assertThrows(Exception.class, () -> {
            services.updateRoutePrice(-1, BigDecimal.valueOf(150));
        });

        assertEquals("Route with ID -1 does not exist.", exception.getMessage());
    }
    @Test
    public void testUpdateRoutePriceSuccess() throws ServicesException {
        assertDoesNotThrow(() -> {
            services.updateRoutePrice(1, BigDecimal.valueOf(150));
        });

        Route route = services.getRouteById(1);

        assertEquals(BigDecimal.valueOf(150.00).setScale(2), route.getBasePrice());
    }

    @Test
    public void testGetByFromLocationAndToLocationNotFound() {
        assertThrows(Exception.class, () -> {
            Route route = services.getByFromLocationAndTolocation("Nonexistent", "Nonexistent");
            assertNull(route);
        });
    }
}
