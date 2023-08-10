package in.fssa.sundaratravels;

import in.fssa.sundaratravels.model.Route;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import in.fssa.sundaratravels.service.RouteServices;

public class CreateRoute {

    RouteServices services = new RouteServices();

    @Test
    public void testCreateRouteWithValidInput() throws Exception {
        Route route = new Route();
        route.setFrom_location("DrumStick Island");
        route.setTo_location("Marineford");

        assertDoesNotThrow(() -> {
            services.createRoute(route);
        });

    }

    @Test
    public void testCreateRouteWithInvalidInput() throws Exception {
        Exception exception = assertThrows(Exception.class, () -> {
            services.createRoute(null);
        });
        String exceptedMessage = "Route is null";
        String actualMessage = exception.getMessage();

        assertTrue(exceptedMessage.equals(actualMessage));
    }

    @Test
    public void testCreateRouteWithInvalidFromLocation() throws Exception {
        Route route = new Route();
        route.setFrom_location(null);
        route.setTo_location("Kalakadu");

        Exception exception = assertThrows(Exception.class, () -> {
            services.createRoute(route);
        });
        String exceptedMessage = "From location cannot be empty or null";
        String actualMessage = exception.getMessage();

        assertTrue(exceptedMessage.equals(actualMessage));

    }
     @Test
     public void testCreateRouteWithEmptyFromLocation() throws Exception {
         Route route = new Route();
         route.setFrom_location("");
         route.setTo_location("Kalakadu");

         Exception exception = assertThrows(Exception.class, () -> {
             services.createRoute(route);
         });
         String exceptedMessage = "From location cannot be empty or null";
         String actualMessage = exception.getMessage();

         assertTrue(exceptedMessage.equals(actualMessage));

     }
    @Test
    public void testCreateRouteWithInvalidToLocation() throws Exception {
        Route route = new Route();
        route.setFrom_location("null");
        route.setTo_location(null);

        Exception exception = assertThrows(Exception.class, () -> {
            services.createRoute(route);
        });
        String exceptedMessage = "To location cannot be empty or null";
        String actualMessage = exception.getMessage();

        assertTrue(exceptedMessage.equals(actualMessage));

    }
     @Test
     public void testCreateRouteWithEmptyToLocation() throws Exception {
         Route route = new Route();
         route.setFrom_location("fjkvf");
         route.setTo_location("");

         Exception exception = assertThrows(Exception.class, () -> {
             services.createRoute(route);
         });
         String exceptedMessage = "To location cannot be empty or null";
         String actualMessage = exception.getMessage();

         assertTrue(exceptedMessage.equals(actualMessage));

     }
     @Test
    public void testCreateRouteWithSameFromAndToLocation() throws Exception {
        Route route = new Route();
        route.setFrom_location("fjkvf");
        route.setTo_location("fjkvf");

        Exception exception = assertThrows(Exception.class, () -> {
            services.createRoute(route);
        });
        String exceptedMessage = "Both cannot be same";
        String actualMessage = exception.getMessage();

        assertTrue(exceptedMessage.equals(actualMessage));

    }


}
