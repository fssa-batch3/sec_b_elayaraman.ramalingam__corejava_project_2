package in.fssa.sundaratravels.validator;

import in.fssa.sundaratravels.exception.ValidationException;
import in.fssa.sundaratravels.model.Booking;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

public class BookingValidator {

    public static void validate(Booking booking) throws ValidationException {
        if (booking.getBusId() <= 0) {
            throw new ValidationException("Invalid bus ID");
        }
        if (booking.getTravelDate() == null) {
            throw new ValidationException("Travel date cannot be null");
        }

        Date currentDate = new Date(Calendar.getInstance().getTimeInMillis());
        if (booking.getTravelDate().before(currentDate)) {
            throw new ValidationException("Travel date cannot be in the past");
        }

        LocalDate maxAllowedDate = LocalDate.now().plusDays(60);
        LocalDate travelLocalDate = convertToLocalDate(booking.getTravelDate());
        if (travelLocalDate.isAfter(maxAllowedDate)) {
            throw new ValidationException("Travel date should be within the next 60 days");
        }

        if (booking.getBookedSeats() <= 0) {
            throw new ValidationException("Invalid booked seats count");
        }
    }

    private static LocalDate convertToLocalDate(Date dateToConvert) {
        return dateToConvert.toLocalDate();
    }
}
