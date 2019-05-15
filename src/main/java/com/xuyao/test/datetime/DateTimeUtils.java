package com.xuyao.test.datetime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;

public class DateTimeUtils {

    /**
     * date转localDateTime
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * date转localDate
     * @param date
     * @return
     */
    public static LocalDate dateToLocalDate(Date date) {
        return dateToLocalDateTime(date).toLocalDate();
    }

    /**
     * date转localTime
     * @param date
     * @return
     */
    public static LocalTime dateToLocalTime(Date date) {
        return dateToLocalDateTime(date).toLocalTime();
    }

    /**
     * localDateTime转date
     * @param localDateTime
     * @return
     */
    public Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * localDate转date
     * @param localDate
     * @return
     */
    public static Date LocalDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * localTime转date
     * @param localDate
     * @param localTime
     * @return
     */
    public static Date LocalTimeTDate(LocalDate localDate, LocalTime localTime) {
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 用指定格式验证日期是否有效
     * @param dataStr
     * @param format
     * @return
     */
    public static boolean isValid(String dataStr, String format){
        DateFormat dateFormat = new SimpleDateFormat(format);
        return isValid(dataStr, dateFormat);
    }

    public static boolean isValid(String dataStr, DateFormat dateFormat){
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(dataStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

}
