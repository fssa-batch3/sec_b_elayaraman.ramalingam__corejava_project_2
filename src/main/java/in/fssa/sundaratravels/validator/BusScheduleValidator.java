package in.fssa.sundaratravels.validator;

import in.fssa.sundaratravels.exception.ValidationException;
import in.fssa.sundaratravels.model.BusSchedule;

public class BusScheduleValidator {

    public static void validate(BusSchedule busSchedule) throws ValidationException {
        if (busSchedule == null) {
            throw new ValidationException("BusSchedule cannot be null");
        }

//        if (busSchedule.getId() <= 0) {
//            throw new ValidationException("Invalid Id");
//        }
    }

    public static void validate(int id) throws ValidationException{
        if(id < 1)
            throw new ValidationException("Invalid schedule Id");
    }
}
