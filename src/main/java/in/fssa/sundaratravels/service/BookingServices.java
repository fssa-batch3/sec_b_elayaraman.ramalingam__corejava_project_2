package in.fssa.sundaratravels.service;

import in.fssa.sundaratravels.dao.BookingDAO;
import in.fssa.sundaratravels.dao.BusDAO;
import in.fssa.sundaratravels.dao.RouteDAO;
import in.fssa.sundaratravels.dao.TicketDAO;
import in.fssa.sundaratravels.exception.PersistenceException;
import in.fssa.sundaratravels.exception.ServicesException;
import in.fssa.sundaratravels.exception.ValidationException;
import in.fssa.sundaratravels.model.Booking;
import in.fssa.sundaratravels.model.Bus;
import in.fssa.sundaratravels.model.Route;
import in.fssa.sundaratravels.model.Ticket;
import in.fssa.sundaratravels.validator.BookingValidator;
import in.fssa.sundaratravels.validator.TicketValidator;

import java.math.BigDecimal;
import java.sql.Date;

public class BookingServices {

    RouteDAO routeDAO = new RouteDAO();

    BusDAO busDAO = new BusDAO();

    BookingDAO bookingDAO = new BookingDAO();
    TicketDAO ticketDAO = new TicketDAO();

    public void createTicket(int busId, Date travelDate, int bookedSeats, String passengerName, long phoneNumber) throws ServicesException, ServicesException {
        try {
            Booking booking = null;
            int bookingId = 0;
            booking = bookingDAO.getBookingByBusAndDate(booking.getBusId(), booking.getTravelDate());

            if (booking == null) {
                booking.setTravelDate(travelDate);
                booking.setBusId(busId);
                booking.setBookedSeats(bookedSeats);
                BookingValidator.validate(booking);
                bookingId = bookingDAO.createBooking(booking);
            }
            bookingId = booking.getId();
            Bus bus = busDAO.getBus(busId);
            Route route = routeDAO.getRouteById(bus.getRouteId());
            BigDecimal basePrice = route.getBasePrice();
            BigDecimal totalPrice = basePrice.multiply(BigDecimal.valueOf(bookedSeats));
            Ticket ticket = new Ticket();
            ticket.setBookingId(bookingId);
            ticket.setBookedSeats(bookedSeats);
            ticket.setPassengerName(passengerName);
            ticket.setPhoneNumber(phoneNumber);
            ticket.setTotalPrice(totalPrice);
            ticketDAO.createTicket(bookingId, travelDate, bookedSeats, passengerName, phoneNumber, totalPrice);
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new ServicesException(e.getMessage());
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
    }


}
