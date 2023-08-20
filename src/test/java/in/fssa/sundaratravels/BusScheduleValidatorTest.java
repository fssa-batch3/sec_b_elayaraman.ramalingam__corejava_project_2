package in.fssa.sundaratravels;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import in.fssa.sundaratravels.validator.BusScheduleValidator;
import in.fssa.sundaratravels.exception.ValidationException;
import in.fssa.sundaratravels.model.BusSchedule;

public class BusScheduleValidatorTest {

    @Test
    public void testValidBusSchedule() {
        BusSchedule busSchedule = new BusSchedule();
        busSchedule.setBusId(1);

        assertDoesNotThrow(() -> {
            BusScheduleValidator.validate(busSchedule);
        });
    }

    @Test
    public void testNullBusSchedule() {
        Exception exception = assertThrows(ValidationException.class, () -> {
            BusScheduleValidator.validate(null);
        });
        assertEquals("BusSchedule cannot be null", exception.getMessage());
    }

    @Test
    public void testInvalidBusId() {
        BusSchedule busSchedule = new BusSchedule();
        busSchedule.setBusId(0); // Invalid bus id

        Exception exception = assertThrows(ValidationException.class, () -> {
            BusScheduleValidator.validate(busSchedule);
        });
        assertEquals("Invalid Bus Id", exception.getMessage());
    }
}
