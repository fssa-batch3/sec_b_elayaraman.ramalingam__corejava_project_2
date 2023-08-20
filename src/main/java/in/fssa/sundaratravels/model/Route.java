package in.fssa.sundaratravels.model;

import java.math.BigDecimal;

public class Route extends RouteEntity {

    private BigDecimal basePrice;

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    @Override
    public String toString() {
        return super.toString() + "Base Price=" + basePrice + "\n";
    }

}
