package in.fssa.sundaratravels.dto;

import java.sql.Date;

public class BookingDTO {
	public BookingDTO() {}
    private String busNumber;
    private Date travelDate;
    private String fromLocation;
    private String toLocation;
    private int seatCount;
    private int ticketId;
    private boolean cancelable;
    private boolean status;

    public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isCancelable() {
		return cancelable;
	}

	public void setCancelable(boolean cancelable) {
		this.cancelable = cancelable;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public BookingDTO(String busNumber, Date travelDate, String fromLocation, String toLocation, int seatCount,int ticketId,boolean cancelable) {
        this.busNumber = busNumber;
        this.travelDate = travelDate;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.seatCount = seatCount;
        this.ticketId = ticketId;
        this.cancelable = cancelable;       
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public Date getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(Date travelDate) {
        this.travelDate = travelDate;
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

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }
}
