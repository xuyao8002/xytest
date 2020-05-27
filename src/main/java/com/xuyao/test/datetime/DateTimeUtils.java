package com.xuyao.test.datetime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
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

    /**
     * 返回指定日期对应的最早时间
     * @param date
     * @return yyyy-MM-dd 00:00:00 000
     */
    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 返回指定日期对应的最晚时间
     * @param date
     * @return yyyy-MM-dd 23:59:59 999
     */
    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());;
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 返回指定日期对应的最早时间
     * @param date
     * @return yyyy-MM-dd 00:00:00 000
     */
    public static Date getStartOfDay1(Date date){
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MILLISECOND, 000);
        return cal.getTime();
    }

    /**
     * 返回指定日期对应的最晚时间
     * @param date
     * @return yyyy-MM-dd 23:59:59 999
     */
    public static Date getEndOfDay1(Date date){
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /**
     * 返回指定日期某个字段加上指定值后的值
     * @param date
     * @param field Calendar字段
     * @param value 正加负减
     * @return
     */
    public static Date add(Date date, int field, int value){
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(field, value);
        return cal.getTime();
    }

}
