package in.fssa.sundaratravels.validator;

import in.fssa.sundaratravels.model.Booking;
import in.fssa.sundaratravels.exception.ValidationException;

public class BookingValidator {

    public static void validate(Booking booking) throws ValidationException {
        if (booking == null) {
            throw new ValidationException("Booking is null");
        }

        if (booking.getBusId() <= 0) {
            throw new ValidationException("Invalid Bus ID");
        }

        if (booking.getScheduleId() <= 0) {
            throw new ValidationException("Invalid Schedule ID");
        }

        if (booking.getTravelDate() == null) {
            throw new ValidationException("Travel date cannot be empty or null");
        }

        if (booking.getBookedSeats() <= 0) {
            throw new ValidationException("Invalid booked seats");
        }
    }
}
