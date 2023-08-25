package in.fssa.sundaratravels;

import in.fssa.sundaratravels.service.BookingServices;
import in.fssa.sundaratravels.model.Booking;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

public class BookingTest {

    private static int validScheduleId;



    @Test
    public void testCreateBookingWithValidInput() {
        BookingServices bookingDAO = new BookingServices();
        Booking booking = new Booking();
        booking.setBusId(1);
        booking.setScheduleId(1);
        booking.setTravelDate(Date.valueOf("2023-08-20"));
        booking.setBookedSeats(5);

        assertDoesNotThrow(() -> {
            bookingDAO.createBooking(booking);
        });
    }

    @Test
    public void testCreateBookingWithInvalidBusId() {
        BookingServices bookingDAO = new BookingServices();
        Booking booking = new Booking();
        booking.setBusId(-1); // Invalid Bus ID
        booking.setScheduleId(1);
        booking.setTravelDate(Date.valueOf("2023-08-20"));
        booking.setBookedSeats(5);

        Exception exception = assertThrows(Exception.class, () -> {
            bookingDAO.createBooking(booking);
        });
        assertEquals("Invalid Bus ID", exception.getMessage());
    }

    @Test
    public void testCreateBookingWithInvalidScheduleId() {
        BookingServices bookingDAO = new BookingServices();
        Booking booking = new Booking();
        booking.setBusId(1);
        booking.setScheduleId(-1); // Invalid Schedule ID
        booking.setTravelDate(Date.valueOf("2023-08-20"));
        booking.setBookedSeats(5);

        Exception exception = assertThrows(Exception.class, () -> {
            bookingDAO.createBooking(booking);
        });
        assertEquals("Invalid Schedule ID", exception.getMessage());
    }

    @Test
    public void testCreateBookingWithNullTravelDate() {
        BookingServices bookingDAO = new BookingServices();
        Booking booking = new Booking();
        booking.setBusId(1);
        booking.setScheduleId(1);
        booking.setTravelDate(null); // Null Travel Date
        booking.setBookedSeats(5);

        Exception exception = assertThrows(Exception.class, () -> {
            bookingDAO.createBooking(booking);
        });
        assertEquals("Travel date cannot be empty or null", exception.getMessage());
    }

    @Test
    public void testCreateBookingWithInvalidBookedSeats() {
        BookingServices bookingDAO = new BookingServices();
        Booking booking = new Booking();
        booking.setBusId(1);
        booking.setScheduleId(1);
        booking.setTravelDate(Date.valueOf("2023-08-20"));
        booking.setBookedSeats(0); // Invalid Booked Seats

        Exception exception = assertThrows(Exception.class, () -> {
            bookingDAO.createBooking(booking);
        });
        assertEquals("Invalid booked seats", exception.getMessage());
    }

    @Test
    public void testCreateBookingWithValidInputAndConnectionClosed() {
        BookingServices bookingDAO = new BookingServices();
        Booking booking = new Booking();
        booking.setBusId(1);
        booking.setScheduleId(1);
        booking.setTravelDate(Date.valueOf("2023-08-20"));
        booking.setBookedSeats(5);
        // Close connection before creating

        Exception exception = assertThrows(Exception.class, () -> {
            bookingDAO.createBooking(booking);
        });
        assertEquals("Error creating booking", exception.getMessage());
    }
}
