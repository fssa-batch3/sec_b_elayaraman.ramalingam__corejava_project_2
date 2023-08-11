package in.fssa.sundaratravels;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Time;

import org.junit.jupiter.api.Test;

import in.fssa.sundaratravels.service.*;
import in.fssa.sundaratravels.model.*;

public class CreateBusTest {
	
	BusServices services = new BusServices();
	
	@Test
	public void testCreateBusWithValidInput() {
		Bus bus = new Bus();
		
		 bus.setBusNo("TN99ER1731");
	        bus.setDeparture_time(Time.valueOf("08:00:00"));
	        bus.setArrival_time(Time.valueOf("11:00:00"));
	        bus.setSchedule_id(1);
	        bus.setIs_ac(true);
	        bus.setRoute_id(1);
	        
	        assertDoesNotThrow (()->{
                    services.createBus(bus);
            });
	}
	
	@Test
	public void testCreateBusWithNull(){
		
		Exception exception = assertThrows(Exception.class,()->{
			services.createBus(null);
		});
		
		String exceptedMessage = "Bus cannot be null";
		String actualMessage = exception.getMessage();

        assertEquals(exceptedMessage, actualMessage);
	}

	@Test
	public void testCreateBusWithInvalidDepartureTime() {
		Bus bus = new Bus();

		bus.setBusNo("TN77F1934");
		bus.setDeparture_time(null); // Invalid time
		bus.setArrival_time(Time.valueOf("15:00:00"));
		bus.setSchedule_id(1);
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
		bus.setDeparture_time(Time.valueOf("15:00:00")); // Invalid time
		bus.setArrival_time(null);
		bus.setSchedule_id(1);
		bus.setIs_ac(true);
		bus.setRoute_id(1);

		Exception exception = assertThrows(
				Exception.class, () -> services.createBus(bus)
		);

		String expectedMessage = "Arrival time cannot be empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

}
