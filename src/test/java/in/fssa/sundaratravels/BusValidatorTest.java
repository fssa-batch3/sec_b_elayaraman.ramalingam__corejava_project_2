package in.fssa.sundaratravels;

import static org.junit.jupiter.api.Assertions.*;

import in.fssa.sundaratravels.exception.ValidationException;
import org.junit.jupiter.api.Test;

import in.fssa.sundaratravels.validator.BusValidator;
import in.fssa.sundaratravels.model.Bus;

import java.sql.Time;

public class BusValidatorTest {


    @Test
    public void testInvalidBusNo() {
        Bus bus = new Bus();
        bus.setBusNo("InvalidBusNo");
        bus.setDeparture_time(Time.valueOf("08:00:00"));
        bus.setArrival_time(Time.valueOf("11:00:00"));
        bus.setIs_ac(true);
        bus.setRoute_id(1);
        bus.setCapacity(42);

        Exception exception = assertThrows(Exception.class, () -> {
            BusValidator.validate(bus);
        });

        String expectedMessage = "Invalid Bus No";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testMissingDepartureTime() {
        Bus bus = new Bus();
        bus.setBusNo("TN99ER1731");
        bus.setDeparture_time(null);
        bus.setArrival_time(Time.valueOf("01:11:11"));   // Replace with a valid time
        bus.setIs_ac(true);
        bus.setRoute_id(1);
        bus.setCapacity(42);

        Exception exception = assertThrows(Exception.class, () -> {
            BusValidator.validate(bus);
        });

        String expectedMessage = "Departure time cannot be empty";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testMissingArrivalTime() {
        Bus bus = new Bus();
        bus.setBusNo("TN99ER1731");
        bus.setDeparture_time(Time.valueOf("01:11:11")); // Replace with a valid time
        bus.setArrival_time(null);
        bus.setIs_ac(true);
        bus.setRoute_id(1);
        bus.setCapacity(42);

        Exception exception = assertThrows(Exception.class, () -> {
            BusValidator.validate(bus);
        });

        String expectedMessage = "Arrival time cannot be empty";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testNegativeCapacity() {
        Bus bus = new Bus();
        bus.setBusNo("TN99ER1731");
        bus.setDeparture_time(Time.valueOf("08:00:00"));
        bus.setArrival_time(Time.valueOf("11:00:00"));
        bus.setIs_ac(true);
        bus.setRoute_id(1);
        bus.setCapacity(-42); // Negative capacity

        Exception exception = assertThrows(ValidationException.class, () -> {
            BusValidator.validate(bus);
        });
        assertEquals("Capacity must be a positive value", exception.getMessage());
    }

    @Test
    public void testZeroCapacity() {
        Bus bus = new Bus();
        bus.setBusNo("TN99ER1731");
        bus.setDeparture_time(Time.valueOf("08:00:00"));
        bus.setArrival_time(Time.valueOf("11:00:00"));
        bus.setIs_ac(true);
        bus.setRoute_id(1);
        bus.setCapacity(0); // Zero capacity

        Exception exception = assertThrows(ValidationException.class, () -> {
            BusValidator.validate(bus);
        });
        assertEquals("Capacity must be a positive value", exception.getMessage());
    }
}
