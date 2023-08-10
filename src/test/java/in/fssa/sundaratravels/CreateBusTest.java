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
		
		 bus.setBusNo("BUS139");
	        bus.setDeparture_time(Time.valueOf("08:00:00"));
	        bus.setArrival_time(Time.valueOf("11:00:00"));
	        bus.setSchedule_id(1);
	        bus.setIs_ac(true);
	        bus.setIs_active(true);
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

}
