package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateHourUtils {
    public static String formattedTime() {
        long timeInMillis = System.currentTimeMillis();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(timeInMillis);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss ");

        return dateFormat.format(cal1.getTime());
    }
}
