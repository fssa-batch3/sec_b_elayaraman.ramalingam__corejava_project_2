package in.fssa.sundaratravels;

import static org.junit.jupiter.api.Assertions.*;

import in.fssa.sundaratravels.exception.ServicesException;
import in.fssa.sundaratravels.model.Bus;
import in.fssa.sundaratravels.service.BusScheduleServices;

import org.junit.jupiter.api.Test;
import in.fssa.sundaratravels.validator.BusScheduleValidator;
import in.fssa.sundaratravels.exception.ValidationException;
import in.fssa.sundaratravels.model.BusSchedule;

import java.util.List;

public class BusScheduleTest {



    final BusScheduleServices services = new BusScheduleServices();

    @Test
    public void testValidBusSchedule() throws ServicesException {
        List<BusSchedule> list = services.getAllBusSchedules();
        int num = list.size();

        BusSchedule busSchedule = new BusSchedule();
        busSchedule.setId(num+1);

        assertDoesNotThrow(() -> {
            services.createBusSchedule(busSchedule);
        });
    }

    @Test
    public void testNullBusSchedule() {
        Exception exception = assertThrows(ValidationException.class, () -> {
            BusScheduleValidator.validate(null);
        });
        assertEquals("BusSchedule cannot be null", exception.getMessage());
    }

}
