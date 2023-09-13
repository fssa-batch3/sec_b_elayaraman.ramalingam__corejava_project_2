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
import in.fssa.sundaratravels.util.NumUtil;

import java.math.BigDecimal;
import java.sql.Date;

public class BookingServices {

    RouteDAO routeDAO = new RouteDAO();

    BusDAO busDAO = new BusDAO();

    BookingDAO bookingDAO = new BookingDAO();
    TicketDAO ticketDAO = new TicketDAO();

    public void bookTicket(int busId, Date travelDate, int bookedSeats, String passengerName, long phoneNumber) throws ServicesException, ServicesException {
        try {
            Booking booking = null;
            int bookingId = 0;
            booking = bookingDAO.getBookingByBusAndDate(busId, travelDate);

            if (booking == null) {
                booking = new Booking();
                booking.setTravelDate(travelDate);
                booking.setBusId(busId);
                booking.setBookedSeats(bookedSeats);
                BookingValidator.validate(booking);
                bookingId = bookingDAO.createBooking(booking);
            } else {
                bookingId = booking.getId();
                bookingDAO.updateBooking(bookingId, booking.getBookedSeats() + bookedSeats);
            }
            Bus bus = busDAO.getBus(busId);
            Route route = routeDAO.getRouteById(bus.getRouteId());
            BigDecimal totalPrice = route.getBasePrice().multiply(BigDecimal.valueOf(bookedSeats));
            Ticket ticket = new Ticket();
            ticket.setBookingId(bookingId);
            ticket.setBookedSeats(bookedSeats);
            ticket.setTravelDate(travelDate);
            ticket.setPassengerName(passengerName);
            ticket.setPhoneNumber(phoneNumber);
            ticket.setTotalPrice(totalPrice);
            TicketValidator.validate(ticket);
            ticketDAO.createTicket(ticket);
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new ServicesException(e.getMessage());
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
    }

    public void cancelTicket(int ticketId){

        try{
            NumUtil.rejectIfInvalidNum(ticketId,"Ticket Id");
            Ticket ticket = ticketDAO.getTicketById(ticketId);
            int bookingId = ticket.getBookingId();
            Booking booking = bookingDAO.getBookingById(bookingId);
            bookingDAO.updateBooking(bookingId,booking.getBookedSeats()-ticket.getBookedSeats());
            ticketDAO.cancelTicket(ticketId);
        } catch (ValidationException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

    }

    public void getAllTickets() throws PersistenceException {
        try{
            ticketDAO.getAllTickets();
        }catch (PersistenceException e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }





}
