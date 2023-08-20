package in.fssa.sundaratravels.util;

import in.fssa.sundaratravels.exception.ValidationException;

public class StringUtil {

    public static boolean rejectIfInvalidString(String input, String inputName) throws ValidationException {

        if (input == null || "".equals(input.trim())) {
            throw new ValidationException(inputName.concat(" cannot be empty or null"));
        }

        return false;
    }

    public static boolean isValidString(String input) {
        return input != null || !"".equals(input);
    }

    public static boolean isInvalidString(String input) {
        return !StringUtil.isValidString(input);
    }

}
