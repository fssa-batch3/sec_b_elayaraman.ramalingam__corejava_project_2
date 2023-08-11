package in.fssa.sundaratravels.validator;

import in.fssa.sundaratravels.model.Bus;
import in.fssa.sundaratravels.util.StringUtil;
import in.fssa.sundaratravels.util.PatternUtil;
import in.fssa.sundaratravels.exception.ValidationException;

public class BusValidator {

	public static void validate(Bus bus) throws Exception {
		if (bus == null) {
			throw new ValidationException("Bus cannot be null");
		}

		StringUtil.rejectIfInvalidString(bus.getBusNo(), "Bus No");

		if(bus.getDeparture_time() == null){
			throw new ValidationException("Departure time cannot be empty");
		}

		if(bus.getArrival_time() == null){
			throw new ValidationException("Arrival time cannot be empty");
		}

		if (!bus.getBusNo().matches("^[A-Z]{2}\\d{2}[A-Z]{1,2}\\d{1,4}$")) {
			throw new ValidationException("Invalid Bus No");
		}
	}
}