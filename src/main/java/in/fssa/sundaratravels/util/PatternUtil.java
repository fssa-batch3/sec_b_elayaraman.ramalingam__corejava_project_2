package in.fssa.sundaratravels.util;

import in.fssa.sundaratravels.exception.ValidationException;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PatternUtil {

    public static void validateTime(Time time, String type) throws ValidationException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            sdf.setLenient(false);
            Time parsedTime = new Time(sdf.parse(time.toString()).getTime());
            // Additional checks on parsedTime if needed
        } catch (ParseException | IllegalArgumentException e) {
            throw new ValidationException("Invalid time format for " + type);
        }
    }

}
