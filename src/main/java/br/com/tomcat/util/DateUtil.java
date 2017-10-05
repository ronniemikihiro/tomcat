package br.com.tomcat.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ronnie-msl on 02/10/17.
 */
public class DateUtil {

    public static LocalDate toLocalDate(final Date data) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(data.getTime()), ZoneId.systemDefault()).toLocalDate();
    }

    public static String dateFormatPT(final LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String dateHourFormatPT(final LocalDateTime dateHour) {
        return dateHour.format(DateTimeFormatter.ofPattern("dd/MM/yy - HH:mm"));
    }
}
