package in.fssa.sundaratravels.validator;

import in.fssa.sundaratravels.model.Bus;
import in.fssa.sundaratravels.exception.ValidationException;
import in.fssa.sundaratravels.util.StringUtil;

public class BusValidator {

	public static void validate(Bus bus) throws ValidationException {
		if (bus == null) {
			throw new ValidationException("Bus cannot be null");
		}

		StringUtil.rejectIfInvalidString(bus.getBusNo(), "Bus No");

		if (bus.getDepartureTime() == null) {
			throw new ValidationException("Departure time cannot be empty");
		}

		if (bus.getArrivalTime() == null) {
			throw new ValidationException("Arrival time cannot be empty");
		}

		if (!bus.getBusNo().matches("^[A-Z]{2}\\d{2}[A-Z]{1,2}\\d{1,4}$")) {
			throw new ValidationException("Invalid Bus No");
		}

		if (bus.getCapacity() <= 0) {
			throw new ValidationException("Capacity must be a positive value");
		}
	}
	public static void validate(int id, String name) throws ValidationException{
		if(id < 1){
			throw new ValidationException("Invalid "+name+" Id");
		}
	}
}
