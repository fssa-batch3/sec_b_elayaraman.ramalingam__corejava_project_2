package in.fssa.sundaratravels;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import in.fssa.sundaratravels.dao.BookingDAO;
import in.fssa.sundaratravels.model.Booking;
import in.fssa.sundaratravels.util.ConnectionUtil;

public class BookingTest {

    @Test
    public void testCreateBookingWithValidInput() {
        BookingDAO dao = new BookingDAO();
        Booking booking = new Booking();
        booking.setBusId(1);
        booking.setScheduleId(1);
        booking.setTravelDate(Date.valueOf("2023-08-20"));
        booking.setBookedSeats(5);

        assertDoesNotThrow(() -> {
            dao.createBooking(booking);
        });
    }

    @Test
    public void testCreateBookingWithInvalidBusId() {
        BookingDAO dao = new BookingDAO();
        Booking booking = new Booking();
        booking.setBusId(-1); // Invalid Bus ID
        booking.setScheduleId(1);
        booking.setTravelDate(Date.valueOf("2023-08-20"));
        booking.setBookedSeats(5);

        Exception exception = assertThrows(Exception.class, () -> {
            dao.createBooking(booking);
        });
        assertEquals("Invalid Bus ID", exception.getMessage());
    }

    @Test
    public void testCreateBookingWithInvalidScheduleId() {
        BookingDAO dao = new BookingDAO();
        Booking booking = new Booking();
        booking.setBusId(1);
        booking.setScheduleId(-1); // Invalid Schedule ID
        booking.setTravelDate(Date.valueOf("2023-08-20"));
        booking.setBookedSeats(5);

        Exception exception = assertThrows(Exception.class, () -> {
            dao.createBooking(booking);
        });
        assertEquals("Invalid Schedule ID", exception.getMessage());
    }

    @Test
    public void testCreateBookingWithNullTravelDate() {
        BookingDAO dao = new BookingDAO();
        Booking booking = new Booking();
        booking.setBusId(1);
        booking.setScheduleId(1);
        booking.setTravelDate(null); // Null Travel Date
        booking.setBookedSeats(5);

        Exception exception = assertThrows(Exception.class, () -> {
            dao.createBooking(booking);
        });
        assertEquals("Travel date cannot be empty or null", exception.getMessage());
    }

    @Test
    public void testCreateBookingWithInvalidBookedSeats() {
        BookingDAO dao = new BookingDAO();
        Booking booking = new Booking();
        booking.setBusId(1);
        booking.setScheduleId(1);
        booking.setTravelDate(Date.valueOf("2023-08-20"));
        booking.setBookedSeats(0); // Invalid Booked Seats

        Exception exception = assertThrows(Exception.class, () -> {
            dao.createBooking(booking);
        });
        assertEquals("Invalid booked seats", exception.getMessage());
    }

    @Test
    public void testCreateBookingWithValidInputAndConnectionClosed() {
        BookingDAO dao = new BookingDAO();
        Booking booking = new Booking();
        booking.setBusId(1);
        booking.setScheduleId(1);
        booking.setTravelDate(Date.valueOf("2023-08-20"));
        booking.setBookedSeats(5);
        // Close connection before creating

        Exception exception = assertThrows(Exception.class, () -> {
            dao.createBooking(booking);
        });
        assertEquals("Error creating booking", exception.getMessage());
    }
}
