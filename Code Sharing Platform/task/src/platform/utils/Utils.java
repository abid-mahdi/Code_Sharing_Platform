package platform.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

    public static LocalDateTime stringToDate(String str) {
        return LocalDateTime.parse(str, FORMATTER);
    }

    public static int differenceBetweenDates(LocalDateTime start, LocalDateTime end) {
        return (int) Duration.between(start, end).toSeconds();
    }

    public static String getCurrentDateTime() {
        LocalDateTime today = LocalDateTime.now();
        return today.format(FORMATTER);
    }

}
