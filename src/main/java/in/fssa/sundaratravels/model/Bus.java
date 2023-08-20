package in.fssa.sundaratravels.model;

import java.sql.Time;

public class Bus {
    
    public String getBusNo() {
		return BusNo;
	}
	public void setBusNo(String busNo) {
		BusNo = busNo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isIs_active() {
		return is_active;
	}
	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}
	public int getSchedule_id() {
		return schedule_id;
	}
	public void setSchedule_id(int schedule_id) {
		this.schedule_id = schedule_id;
	}
	public int getRoute_id() {
		return route_id;
	}
	public void setRoute_id(int route_id) {
		this.route_id = route_id;
	}
	public Time getDeparture_time() {
		return departure_time;
	}
	public void setDeparture_time(Time departure_time) {
		this.departure_time = departure_time;
	}
	public Time getArrival_time() {
		return arrival_time;
	}
	public void setArrival_time(Time arrival_time) {
		this.arrival_time = arrival_time;
	}
	public boolean isIs_ac() {
		return is_ac;
	}
	public void setIs_ac(boolean is_ac) {
		this.is_ac = is_ac;
	}
	private String BusNo;
    private int id;
    private boolean is_active;
    private int schedule_id;
    private int route_id;
    private Time departure_time;
    private Time arrival_time;
    private boolean is_ac;

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	private int capacity;
    
}
