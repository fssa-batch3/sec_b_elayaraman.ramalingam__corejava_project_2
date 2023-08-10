package in.fssa.sundaratravels.validator;

import in.fssa.sundaratravels.model.Route;
import in.fssa.sundaratravels.util.StringUtil;

public class RouteValidator {

    public static void validate(Route route) throws Exception {
        
        if (route == null) {
            throw new Exception("Route is null");
        }
        StringUtil.rejectIfInvalidString(route.getFrom_location(), "From location");
        StringUtil.rejectIfInvalidString(route.getTo_location(), "To location");

    }
    
}
