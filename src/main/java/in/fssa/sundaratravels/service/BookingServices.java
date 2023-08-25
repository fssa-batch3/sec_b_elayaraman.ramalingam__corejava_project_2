package in.fssa.sundaratravels.service;

import java.util.List;

import in.fssa.sundaratravels.model.Booking;
import in.fssa.sundaratravels.dao.BookingDAO;
import in.fssa.sundaratravels.validator.BookingValidator;
import in.fssa.sundaratravels.exception.ValidationException;

public class BookingServices {

    private BookingDAO bookingDAO = new BookingDAO();

    public void createBooking(Booking booking) throws ValidationException, Exception {
        BookingValidator.validate(booking);
        bookingDAO.createBooking(booking);
    }

    public Booking getBookingById(int id) throws Exception {
        return bookingDAO.getBookingById(id);
    }

    public List<Booking> getAllBookings() throws Exception {
        return bookingDAO.getAllBookings();
    }

    public List<Booking> getBookingsByDate(String travelDate) throws Exception {
        return bookingDAO.getBookingsByDate(travelDate);
    }

    public List<Booking> getBookingsByBusId(int busId) throws Exception {
        return bookingDAO.getBookingsByBusId(busId);
    }

    public Booking getBookingWithBusIdAndDate(int busId, String travelDate) throws Exception {
        return bookingDAO.getBookingWithBusIdAndDate(busId, travelDate);
    }

    public void updateBooking(Booking booking) throws ValidationException, Exception {
        BookingValidator.validate(booking);
        bookingDAO.updateBooking(booking);
    }

    public void deleteBooking(int id) throws Exception {
        bookingDAO.deleteBooking(id);
    }

    public Booking getBookingByBusNo(String busNo) throws Exception {
        return bookingDAO.getBookingByBusNo(busNo);
    }
}
