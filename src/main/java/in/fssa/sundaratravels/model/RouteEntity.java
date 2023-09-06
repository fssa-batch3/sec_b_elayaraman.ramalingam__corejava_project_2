package in.fssa.sundaratravels.model;

import java.math.BigDecimal;

public abstract class RouteEntity {
	
	public int getRouteId() {
		return routeId;
	}
	public void setRouteId(int routeId) {
		this.routeId = routeId;
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
	@Override
	public String toString() {
		return "[RouteId=" + routeId + ", fromLocation=" + fromLocation + ", to_location=" + toLocation
				+"Base Price=" + basePrice
		+"]\n";
	}
	private int routeId;
	private String fromLocation;
	private String toLocation;
	private BigDecimal basePrice;

	public boolean isActive(boolean isActive) {
		return this.isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	private boolean isActive;
	

}
