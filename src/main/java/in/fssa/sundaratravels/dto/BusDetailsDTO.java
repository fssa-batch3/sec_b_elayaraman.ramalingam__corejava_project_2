package in.fssa.sundaratravels.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;

public class BusDetailsDTO {


	private String BusNo;
	private String fromLocation;
	private String toLocation;
	private BigDecimal basePrice;
    private Time departureTime;
    private Time arrivalTime;
    private HashMap<Integer,Boolean> seats;
    
    public HashMap<Integer,Boolean> getSeats() {
		return seats;
	}

	public void setSeats(HashMap<Integer,Boolean> seats) {
		this.seats = seats;
	}

	public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

	public boolean isAc() {
		return isAc;
	}

	public void setAc(boolean ac) {
		isAc = ac;
	}

	private boolean isAc;

	public String getBusNo() {
		return BusNo;
	}

	public void setBusNo(String busNo) {
		BusNo = busNo;
	}

	public String getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}

	public String getToLocation() {
		return toLocation;
	}

	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}

	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public int getBusid() {
		return busid;
	}

	public void setBusid(int busid) {
		this.busid = busid;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	private int availableSeats;

	private int busid;

	private int bookingId;

	public Date getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}

	private Date travelDate;
}
