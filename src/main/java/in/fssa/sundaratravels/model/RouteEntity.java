package in.fssa.sundaratravels.model;

public abstract class RouteEntity {
	
	public int getRouteId() {
		return RouteId;
	}
	public void setRouteId(int routeId) {
		RouteId = routeId;
	}
	public String getFrom_location() {
		return from_location;
	}
	public void setFrom_location(String from_location) {
		this.from_location = from_location;
	}
	public String getTo_location() {
		return to_location;
	}
	public void setTo_location(String to_location) {
		this.to_location = to_location;
	}
	@Override
	public String toString() {
		return "[RouteId=" + RouteId + ", from_location=" + from_location + ", to_location=" + to_location
				+ "]\n";
	}
	private int RouteId;
	private String from_location;
	private String to_location;
	
	

}