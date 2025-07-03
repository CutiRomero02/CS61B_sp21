package gitlet;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.text.SimpleDateFormat;

public class Time {

    public static String getTimestamp() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss 'UTC,' EEEE, d MMMM yyyy", Locale.ENGLISH);
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatter.format(date);
    }

    public static String getUnixEpoch() {
        return "00:00:00 UTC, Thursday, 1 January 1970";
    }
}
