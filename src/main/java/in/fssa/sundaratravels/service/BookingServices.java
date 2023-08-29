package in.fssa.sundaratravels.service;

import com.google.protobuf.ServiceException;
import in.fssa.sundaratravels.dao.BookingDAO;
import in.fssa.sundaratravels.exception.PersistenceException;
import in.fssa.sundaratravels.exception.ValidationException;
import in.fssa.sundaratravels.model.Booking;
import in.fssa.sundaratravels.validator.BookingValidator;

import java.sql.Date;
import java.util.List;

public class BookingServices {

    private BookingDAO bookingDAO = new BookingDAO();

    public void createBooking(Booking booking) throws ServiceException {
        try {
            BookingValidator.validate(booking);
            bookingDAO.createBooking(booking);
        } catch (PersistenceException | ValidationException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Booking> getAllBookings() throws ServiceException {
        try {
            return bookingDAO.getAllBookings();
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Booking> getBookingsByDate(Date travelDate) throws ServiceException {
        try {
            return bookingDAO.getBookingsByDate(travelDate);
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Booking> getBookingsByBusId(int busId) throws ServiceException {
        try {
            return bookingDAO.getBookingsByBusId(busId);
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Booking> getBookingsByDateAndBusId(Date travelDate, int busId) throws ServiceException {
        try {
            return bookingDAO.getBookingsByDateAndBusId(travelDate, busId);
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }
}
