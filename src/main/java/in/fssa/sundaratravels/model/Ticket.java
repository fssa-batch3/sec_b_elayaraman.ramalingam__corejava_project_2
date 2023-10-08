package in.fssa.sundaratravels.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class Ticket {
    private int ticketId;
    private int bookingId;
    private Date travelDate;
    private int bookedSeats;
    private BigDecimal totalPrice;

    public int[] getSeats() {
        return seats;
    }

    public void setSeats(int[] seats) {
        this.seats = seats;
    }

    private int[] seats;

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private String passengerName;
    private long phoneNumber;

    private boolean isActive;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int id) {
        this.ticketId = id;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Date getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(Date travelDate) {
        this.travelDate = travelDate;
    }

    public int getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(int bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    // Constructors, getters, setters

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + ticketId +
                ", bookingId=" + bookingId +
                ", travelDate=" + travelDate +
                ", bookedSeats=" + bookedSeats +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
