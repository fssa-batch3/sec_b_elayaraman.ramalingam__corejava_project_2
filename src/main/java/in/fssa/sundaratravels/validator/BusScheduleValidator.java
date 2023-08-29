package in.fssa.sundaratravels.validator;

import in.fssa.sundaratravels.exception.ValidationException;
import in.fssa.sundaratravels.model.BusSchedule;

public class BusScheduleValidator {

    public static void validate(BusSchedule busSchedule) throws ValidationException {
        if (busSchedule == null) {
            throw new ValidationException("BusSchedule cannot be null");
        }

        if (busSchedule.getBusId() <= 0) {
            throw new ValidationException("Invalid Bus Id");
        }
    }

    public static void validate(int id) throws ValidationException{
        if(id < 1)
            throw new ValidationException("Invalid schedule Id");
    }
}
