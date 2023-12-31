package in.fssa.sundaratravels.validator;

import in.fssa.sundaratravels.model.Route;
import in.fssa.sundaratravels.exception.ValidationException;
import in.fssa.sundaratravels.util.StringUtil;

import java.math.BigDecimal;

public class RouteValidator {

    public static void validate(Route route) throws ValidationException {
        if (route == null) {
            throw new ValidationException("Route is null");
        }

        StringUtil.rejectIfInvalidString(route.getFromLocation(),"From location");
        StringUtil.rejectIfInvalidString(route.getToLocation(),"To location");

        if (route.getFromLocation().equalsIgnoreCase(route.getToLocation())) {
            throw new ValidationException("Both cannot be same");
        }

        if (route.getBasePrice() == null || route.getBasePrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidationException("Base price must be a positive value");
        }

        if (route.getBasePrice().scale() > 2) {
            throw new ValidationException("Base price must have at most two decimal places");
        }

        if (route.getFromLocation().length() > 100) {
            throw new ValidationException("From location exceeds maximum length");
        }

        if (route.getToLocation().length() > 100) {
            throw new ValidationException("To location exceeds maximum length");
        }
    }
}
