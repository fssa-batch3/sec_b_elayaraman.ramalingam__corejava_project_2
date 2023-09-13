package in.fssa.sundaratravels.util;

import in.fssa.sundaratravels.exception.ValidationException;

public class NumUtil {

    public static boolean isValidNum(byte num) {
        return num > 0;
    }

    public static boolean isValidNum(short num) {
        return num > 0;
    }

    public static boolean isValidNum(int num) {
        return num > 0;
    }

    public static boolean isValidNum(long num) {
        return num > 0;
    }

    public static boolean isValidNum(float num) {
        return num > 0;
    }

    public static boolean isValidNum(double num) {
        return num > 0;
    }

    public static boolean rejectIfInvalidNum(byte num, String inputName) throws ValidationException {
        if (!isValidNum(num)) {
            throw new ValidationException(inputName.concat(" must be a positive number"));
        }
        return true;
    }

    public static boolean rejectIfInvalidNum(short num, String inputName) throws ValidationException {
        if (!isValidNum(num)) {
            throw new ValidationException(inputName.concat(" must be a positive number"));
        }
        return true;
    }

    public static boolean rejectIfInvalidNum(int num, String inputName) throws ValidationException {
        if (!isValidNum(num)) {
            throw new ValidationException(inputName.concat(" must be a positive number"));
        }
        return true;
    }

    public static boolean rejectIfInvalidNum(long num, String inputName) throws ValidationException {
        if (!isValidNum(num)) {
            throw new ValidationException(inputName.concat(" must be a positive number"));
        }
        return true;
    }

    public static boolean rejectIfInvalidNum(float num, String inputName) throws ValidationException {
        if (!isValidNum(num)) {
            throw new ValidationException(inputName.concat(" must be a positive number"));
        }
        return true;
    }

    public static boolean rejectIfInvalidNum(double num, String inputName) throws ValidationException {
        if (!isValidNum(num)) {
            throw new ValidationException(inputName.concat(" must be a positive number"));
        }
        return true;
    }
}
