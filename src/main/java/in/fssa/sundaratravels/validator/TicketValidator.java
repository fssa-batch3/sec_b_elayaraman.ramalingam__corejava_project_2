package in.fssa.sundaratravels.validator;

import in.fssa.sundaratravels.exception.ValidationException;
import in.fssa.sundaratravels.model.Ticket;

import java.math.BigDecimal;

public class TicketValidator {

    public static void validate(Ticket ticket) throws ValidationException {
        if (ticket.getBookingId() <= 0) {
            throw new ValidationException("Invalid booking ID");
        }
        if (ticket.getTravelDate() == null) {
            throw new ValidationException("Travel date cannot be null");
        }
        if (ticket.getBookedSeats() <= 0) {
            throw new ValidationException("Invalid booked seats count");
        }
        if (ticket.getTotalPrice().compareTo(BigDecimal.valueOf(0)) <= 0) {
            throw new ValidationException("Invalid total price");
        }
    }
}
