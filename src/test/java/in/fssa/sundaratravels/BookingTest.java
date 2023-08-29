package in.fssa.sundaratravels;

import in.fssa.sundaratravels.exception.ValidationException;
import in.fssa.sundaratravels.model.Booking;
import in.fssa.sundaratravels.validator.BookingValidator;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class BookingTest {

    @Test
    void testValidBooking() {
        Booking booking = new Booking();
        booking.setBusId(1);
        booking.setTravelDate(Date.valueOf(LocalDate.now().plusDays(10)));
        booking.setBookedSeats(3);

        assertDoesNotThrow(() -> BookingValidator.validate(booking));
    }

    @Test
    void testInvalidBusId() {
        Booking booking = new Booking();
        booking.setBusId(-1);
        booking.setTravelDate(Date.valueOf(LocalDate.now().plusDays(10)));
        booking.setBookedSeats(3);

        assertThrows(ValidationException.class, () -> BookingValidator.validate(booking));
    }

    @Test
    void testInvalidTravelDateInPast() {
        Booking booking = new Booking();
        booking.setBusId(1);
        booking.setTravelDate(Date.valueOf(LocalDate.now().minusDays(1)));
        booking.setBookedSeats(3);

        assertThrows(ValidationException.class, () -> BookingValidator.validate(booking));
    }

    @Test
    void testInvalidTravelDateInFuture() {
        Booking booking = new Booking();
        booking.setBusId(1);
        booking.setTravelDate(Date.valueOf(LocalDate.now().plusDays(100)));
        booking.setBookedSeats(3);

        assertThrows(ValidationException.class, () -> BookingValidator.validate(booking));
    }

    @Test
    void testInvalidBookedSeats() {
        Booking booking = new Booking();
        booking.setBusId(1);
        booking.setTravelDate(Date.valueOf(LocalDate.now().plusDays(10)));
        booking.setBookedSeats(0);

        assertThrows(ValidationException.class, () -> BookingValidator.validate(booking));
    }
}
