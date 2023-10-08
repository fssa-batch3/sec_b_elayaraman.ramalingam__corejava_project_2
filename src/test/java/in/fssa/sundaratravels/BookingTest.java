package in.fssa.sundaratravels;

import in.fssa.sundaratravels.exception.ServicesException;
import in.fssa.sundaratravels.service.BookingServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

public class BookingTest {

    private BookingServices services;

    @BeforeEach
    public void setup(){services = new BookingServices();}

    @Test
    public void testCreateBookingWithValidInformation() throws ServicesException {
        assertDoesNotThrow(() -> {
            services.bookTicket(5, Date.valueOf("2023-09-19"),15,"Kumar",9344048138l,new int[]{1});
        });
    }

   // @Test
    public void testCancelBookingWithValidInformation() throws ServicesException{
        assertDoesNotThrow(()->{
            services.cancelTicket(5);
        });
    }

    @Test
    public void testCancelBookingWithInvalidInformation() throws ServicesException{
        Exception exception = assertThrows(Exception.class,()->{
            services.cancelTicket(-1);
        });

        String expectedMessage = "Ticket Id must be a positive number";
        String ActualMessage = exception.getMessage();

        assertEquals(expectedMessage,ActualMessage);
    }
}
