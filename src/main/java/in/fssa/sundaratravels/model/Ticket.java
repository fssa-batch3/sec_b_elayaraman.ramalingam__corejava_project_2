package in.fssa.sundaratravels.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Ticket {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public Date getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(Date travelDate) {
        this.travelDate = travelDate;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private int busId;
    private Date travelDate;
    private int seatCount;
    private String name;
    private String phoneNumber;

    // Constructors, getters, setters

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", busId=" + busId +
                ", travelDate=" + travelDate +
                ", seatCount=" + seatCount +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
