package in.fssa.sundaratravels.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Ticket {
    private int id;
    private int bookingId;
    private Date travelDate;
    private int bookedSeats;
    private BigDecimal totalPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "id=" + id +
                ", bookingId=" + bookingId +
                ", travelDate=" + travelDate +
                ", bookedSeats=" + bookedSeats +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
