package in.fssa.sundaratravels;

import static in.fssa.sundaratravels.util.BusNumberGenerator.generateRandomBusNumber;
import static org.junit.jupiter.api.Assertions.*;
import in.fssa.sundaratravels.util.BusNumberGenerator;

import java.sql.Time;

import in.fssa.sundaratravels.exception.ValidationException;
import in.fssa.sundaratravels.validator.BusValidator;
import org.junit.jupiter.api.Test;

import in.fssa.sundaratravels.service.*;
import in.fssa.sundaratravels.model.*;

public class BusTest {

	BusServices services = new BusServices();

	@Test
	public void testCreateBusWithValidInput() {

		String Busno = generateRandomBusNumber();
		System.out.println(Busno);
		Bus bus = new Bus();

		bus.setBusNo(Busno);
		bus.setDeparture_time(Time.valueOf("08:00:00"));
		bus.setArrival_time(Time.valueOf("11:00:00"));
		bus.setIs_ac(true);
		bus.setRoute_id(1);
		bus.setCapacity(42);

		assertDoesNotThrow(() -> {
			services.createBus(bus);
		});
	}

	@Test
	public void testCreateBusWithNull() {
		Exception exception = assertThrows(Exception.class, () -> {
			services.createBus(null);
		});

		String expectedMessage = "Bus cannot be null";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	public void testCreateBusWithInvalidDepartureTime() {
		Bus bus = new Bus();

		bus.setBusNo("TN77F1934");
		bus.setDeparture_time(null); // Invalid time
		bus.setArrival_time(Time.valueOf("15:00:00"));
		bus.setIs_ac(true);
		bus.setRoute_id(1);

		Exception exception = assertThrows(
				Exception.class, () -> services.createBus(bus)
		);

		String expectedMessage = "Departure time cannot be empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	public void testCreateBusWithInvalidArrivalTime() {
		Bus bus = new Bus();

		bus.setBusNo("TN77F1934");
		bus.setDeparture_time(Time.valueOf("15:00:00"));
		bus.setArrival_time(null); // Invalid time
		bus.setIs_ac(true);
		bus.setRoute_id(1);

		Exception exception = assertThrows(
				Exception.class, () -> services.createBus(bus)
		);

		String expectedMessage = "Arrival time cannot be empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}
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
