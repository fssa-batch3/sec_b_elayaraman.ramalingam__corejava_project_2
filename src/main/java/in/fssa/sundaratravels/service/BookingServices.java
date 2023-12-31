package in.fssa.sundaratravels.service;

import in.fssa.sundaratravels.dao.BookingDAO;
import in.fssa.sundaratravels.dao.BusDAO;
import in.fssa.sundaratravels.dao.RouteDAO;
import in.fssa.sundaratravels.dao.TicketDAO;
import in.fssa.sundaratravels.model.Booking;
import in.fssa.sundaratravels.model.Bus;
import in.fssa.sundaratravels.model.Route;
import in.fssa.sundaratravels.model.Ticket;
import in.fssa.sundaratravels.util.NumUtil;
import in.fssa.sundaratravels.validator.BookingValidator;
import in.fssa.sundaratravels.validator.*;
import in.fssa.sundaratravels.exception.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

public class BookingServices {

    RouteDAO routeDAO = new RouteDAO();
    BusDAO busDAO = new BusDAO();
    BookingDAO bookingDAO = new BookingDAO();
    TicketDAO ticketDAO = new TicketDAO();

    public int bookTicket(int busId, Date travelDate, int bookedSeats, String passengerName, long phoneNumber,int[] seats) throws ServicesException, ServicesException {
        int bookingId = 0;
        try {
            Booking booking = null;
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
                BookingValidator.validate(booking, bookedSeats);
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
            ticket.setSeats(seats);
            TicketValidator.validate(ticket);
            ticketDAO.createTicket(ticket);
        } catch (ValidationException | PersistenceException e) {
            e.printStackTrace();
            throw new ServicesException(e.getMessage());
        }
        return bookingId;
    }

    public void cancelTicket(int ticketId) throws ServicesException {

        try{
            NumUtil.rejectIfInvalidNum(ticketId,"Ticket Id");
            Ticket ticket = ticketDAO.getTicketById(ticketId);
            int bookingId = ticket.getBookingId();
            Booking booking = bookingDAO.getBookingById(bookingId);
            ticketDAO.cancelTicket(ticketId);
            bookingDAO.updateBooking(bookingId,booking.getBookedSeats()-ticket.getBookedSeats());
            
        } catch (ValidationException | PersistenceException e) {
            e.printStackTrace();
            throw new ServicesException(e.getMessage());
        }

    }
    
    public List<Booking> getAllBookings() throws  ServicesException {
        try{
            return bookingDAO.getAllBookings();
        }catch (PersistenceException e){
            e.printStackTrace();
            throw new ServicesException(e.getMessage());
        }
    }

    public List<Ticket> getAllTickets() throws  ServicesException {
        try{
            return ticketDAO.getAllTickets();
        }catch (PersistenceException e){
            e.printStackTrace();
            throw new ServicesException(e.getMessage());
        }
    }

    public List<Ticket> getTicketByBookingId(int id) throws ServicesException{
        try {
            NumUtil.rejectIfInvalidNum(id,"booking id ");
            return ticketDAO.getTicketsByBookingId(id);
        } catch (PersistenceException | ValidationException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Booking getBooking(int id)throws ServicesException{
    	try {
    		NumUtil.isValidNum(id);
    		return bookingDAO.getBookingById(id);
    	}catch(PersistenceException e) {
    		throw new ServicesException(e.getMessage());
    	}
    }

    public Booking getBookingByBusAndDate(int busId, Date travelDate) throws ServicesException {
        try {
            NumUtil.rejectIfInvalidNum(busId, "Bus Id");
            return bookingDAO.getBookingByBusAndDate(busId, travelDate);
        } catch (ValidationException | PersistenceException e) {
            e.printStackTrace();
            throw new ServicesException(e.getMessage());
        }
    }

    public List<Ticket> getTicketByPhoneNumber(long phonenumber) throws ServicesException {
        try {
            NumUtil.rejectIfInvalidPhoneNumber(phonenumber);
            return TicketDAO.getTicketByPhoneNumber(phonenumber);
        } catch (ValidationException | PersistenceException e) {
            e.printStackTrace();
            throw new ServicesException(e.getMessage());
        }
    }





}
