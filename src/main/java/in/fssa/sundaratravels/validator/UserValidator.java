package in.fssa.sundaratravels.validator;

import in.fssa.sundaratravels.exception.ValidationException;
import in.fssa.sundaratravels.model.User;
import in.fssa.sundaratravels.util.StringUtil;

public class UserValidator {


    public static void validateUser(User user) throws ValidationException {
        if (user == null) {
            throw new ValidationException("User cannot be null");
        }

        StringUtil.rejectIfInvalidString(user.getUserName(), "User Name");
        StringUtil.rejectIfInvalidString(user.getPassword(), "Password");


        if (user.getDob() == null) {
            throw new ValidationException("Date of Birth (dob) cannot be empty");
        }
        if ( user.getPhone() < 1000000000L || user.getPhone() > 9999999999L) {
            throw new ValidationException("Invalid Phone Number");
        }
    }

}
