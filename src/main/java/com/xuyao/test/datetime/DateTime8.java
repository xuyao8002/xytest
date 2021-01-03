package com.xuyao.test.datetime;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateTime8 {

    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        System.out.println(localDateTime.minus(2, ChronoUnit.DAYS));

        LocalDateTime old = LocalDateTime.of(2017, 11, 3, 10, 11);
        System.out.println(old + ", " + old.plus(180, ChronoUnit.DAYS));

        System.out.println(old.withYear(2018));

        //        // 获得当前日期
//        LocalDate now = LocalDate.now();
//        System.out.println(now.toString());
//// 日期加上1天
//        LocalDate localDate2 = now.plusDays(1);
//        System.out.println(localDate2.toString());
//// 日期加上一周
//        LocalDate localDate3 = now.plusWeeks(1);
//        System.out.println(localDate3);
//// 计算当前年的第52天是几月几号
//        System.out.println("今年的第52天 = " + now.withDayOfYear(52));
//// 字符串转日期
//        DateTimeFormatter strToDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        TemporalAccessor dateTemporal = strToDateFormatter.parse("2017-01-01");
//        LocalDate date = LocalDate.from(dateTemporal);
//        System.out.println(date);
//// 格式化日期
//        DateTimeFormatter dateToStrFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
//        String dateStr = dateToStrFormatter.format(now);
//        System.out.println(dateStr);

//        LocalTime localTime = LocalTime.now();
//        System.out.println("local time = " + localTime);
//        System.out.println("plus 12 hours = " + localTime.plusHours(12));

//        // 获得当前日期时间
//        LocalDateTime now = LocalDateTime.now();
//        System.out.println(now);
//// 字符串转日期时间。
//        DateTimeFormatter strToDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        TemporalAccessor dateTemporal = strToDateFormatter.parse("2017-01-01 12:12:13");
//        LocalDate date = LocalDate.from(dateTemporal);
//        System.out.println(date);
//// 格式化日期时间
//        DateTimeFormatter dateToStrFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
//        String dateStr = dateToStrFormatter.format(now);
//        System.out.println(dateStr);

//        LocalDate startDate = LocalDate.now();
//        LocalDate endDate = startDate.plusDays(1);
//
//        Period period = Period.between(startDate, endDate);
//        System.out.println("间隔的天数" + period.getDays());
//        System.out.println("间隔的月数:" + period.getMonths());
//        System.out.println("间隔的年数:" + period.getYears());
//// 直接使用日期类中的方法计算日期间隔
//        long days = startDate.until(endDate, ChronoUnit.DAYS);
//        System.out.println("间隔的天数:" + days);
//        long weeks = startDate.until(endDate, ChronoUnit.WEEKS);
//        System.out.println("间隔的周数:" + weeks);

//        LocalDateTime start = LocalDateTime.now();
//        LocalDateTime end = start.plusDays(1);
//        Duration duration = Duration.between(start, end);
//        System.out.println("间隔的秒数:" + duration.get(ChronoUnit.SECONDS));
//        System.out.println("间隔的纳秒数:" + duration.get(ChronoUnit.NANOS));


    }
    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());;
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime getStartOfDay(LocalDate localDate) {
        return localDate.atTime(LocalTime.MIN);
    }

    public static LocalDateTime getEndOfDay(LocalDate localDate) {
        return localDate.atTime(LocalTime.MAX);
    }

}
