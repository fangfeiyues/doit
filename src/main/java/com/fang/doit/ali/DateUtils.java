package com.fang.doit.ali;

import com.alibaba.druid.util.StringUtils;
import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author by Feiyue on 2020/8/19 5:34 PM
 */
public class DateUtils {

    public static final String YYYY = "yyyy";
    public static final String YYYYMM = "yyyyMM";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYYMMDDHHMM = "yyyyMMddHHmm";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String YYYYMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
    private final static String FIRST_SEASON = "01";
    private final static String SECOND_SEASON = "02";
    private final static String THIRD_SEASON = "03";
    private final static String FOURTH_SEASON = "04";

    public static Long getCurrentDay() {
        return Long.parseLong(format(System.currentTimeMillis(), YYYYMMDD));
    }

    public static boolean minDateYear(Date curDate) {
        if (curDate == null) {
            return false;
        }
        LocalDateTime dateTime = LocalDateTime.of(1970, Month.JANUARY, 1, 1, 1);
        return dateTime.getYear() > Long.parseLong(format(curDate.getTime(), YYYY));
    }


    /**
     * 月份时间如202102转换为当月第一天的时间戳
     *
     * @param monthTime
     * @return
     */
    public static Long transferDayFromMonth(Long monthTime) {
        SimpleDateFormat format = new SimpleDateFormat(YYYYMM);
        Date date;
        try {
            date = format.parse(String.valueOf(monthTime));
        } catch (ParseException e) {
            return null;
        }

        return date.getTime();
    }

    /**
     * 季度时间如202102转换为期间月份第一天的时间戳
     *
     * @param seasonTime
     * @return 1609430400000
     */
    public static List<Long> transferDayFromSeason(Long seasonTime) {
        String seasonTimeStr = String.valueOf(seasonTime);
        String year = seasonTimeStr.substring(0, 4);
        String season = seasonTimeStr.substring(4);
        switch (season) {
            case FIRST_SEASON:
                return Lists.newArrayList(transferDayFromMonth(year, "01"), transferDayFromMonth(year, "02"), transferDayFromMonth(year, "03"));
            case SECOND_SEASON:
                return Lists.newArrayList(transferDayFromMonth(year, "04"), transferDayFromMonth(year, "05"), transferDayFromMonth(year, "06"));
            case THIRD_SEASON:
                return Lists.newArrayList(transferDayFromMonth(year, "09"), transferDayFromMonth(year, "07"), transferDayFromMonth(year, "08"));
            case FOURTH_SEASON:
                return Lists.newArrayList(transferDayFromMonth(year, "12"), transferDayFromMonth(year, "10"), transferDayFromMonth(year, "11"));
            default:
                return null;
        }
    }

    private static Long transferDayFromMonth(String year, String month) {
        return DateUtils.transferDayFromMonth(Long.parseLong(year + month));
    }

    /**
     * 时间转化为 YYYYMMDD
     *
     * @param date
     * @return
     */
    public static Long getDay(Date date) {
        if (date == null) {
            return null;
        }
        return Long.parseLong(format(date.getTime(), YYYYMMDD));
    }

    public static Long getMonthByDayTime(Long dayTime) {
        SimpleDateFormat format = new SimpleDateFormat(YYYYMMDD);
        Date date;
        try {
            date = format.parse(String.valueOf(dayTime));
        } catch (ParseException e) {
            return null;
        }
        return Long.valueOf(buildMonthTimeValue(date.getTime()));
    }

    public static Long getMonth(Long milli) {
        return Long.parseLong(buildMonthTimeValue(milli));
    }

    public static String buildMonthTimeValue(Long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));
        int month = calendar.get(Calendar.MONTH) + 1;
        StringBuilder key = new StringBuilder();
        key.append(calendar.get(Calendar.YEAR));
        if (month < 10) {
            key.append(0);
        }
        key.append(month);
        return key.toString();
    }

    public static Long getDay(Long milli) {
        if (milli == null) {
            return getCurrentDay();
        }
        return Long.parseLong(format(milli, YYYYMMDD));
    }

    /**
     * 获取上个月的最后一天
     *
     * @param date
     * @return
     */
    public static Long getLastDayOfPreMonth(Date date) {
        Calendar c = Calendar.getInstance();
        //设置为指定日期
        c.setTime(date);
        //指定日期月份减去一
        c.add(Calendar.MONTH, -1);
        //指定日期月份减去一后的 最大天数
        c.set(Calendar.DATE, c.getActualMaximum(Calendar.DATE));
        //获取最终的时间
        return c.getTime().getTime();
    }

    public static Integer getCurrentMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 昨天的结束时间
     */
    public static Date endOfYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * format
     *
     * @param timestamp
     * @param tpl
     * @return
     */
    public static String format(Long timestamp, String tpl) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(tpl);
        return simpleDateFormat.format(timestamp);
    }

    /**
     * format
     *
     * @param date
     * @param tpl
     * @return
     */
    public static String format(Date date, String tpl) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(tpl);
        return simpleDateFormat.format(date);
    }

    public static Date parse(String date, String tpl) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(tpl);
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date parseDate(Long milli) {
        return new Date(milli);
    }

    /**
     * 功能：获取本周的开始时间 示例：2013-05-13 00:00:00
     */
    public static Date startOfThisWeek() {// 当周开始时间
        Calendar currentDate = Calendar.getInstance();
        currentDate.setFirstDayOfWeek(Calendar.MONDAY);
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        currentDate.set(Calendar.MILLISECOND, 0);
        currentDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return currentDate.getTime();
    }

    /**
     * 功能：获取本周的结束时间 示例：2013-05-19 23:59:59
     */
    public static Date endOfThisWeek() {// 当周结束时间
        Calendar currentDate = Calendar.getInstance();
        currentDate.setFirstDayOfWeek(Calendar.MONDAY);
        currentDate.set(Calendar.HOUR_OF_DAY, 23);
        currentDate.set(Calendar.MINUTE, 59);
        currentDate.set(Calendar.SECOND, 59);
        currentDate.set(Calendar.MILLISECOND, 999);
        currentDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return currentDate.getTime();
    }

    /**
     * 功能：获取本月的开始时间
     */
    public static Date startOfThisMonth() {
        Calendar currentDate = Calendar.getInstance();
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        currentDate.set(Calendar.MILLISECOND, 0);
        currentDate.set(Calendar.DAY_OF_MONTH, 1);
        return currentDate.getTime();
    }

    /**
     * 本月的最后时间
     *
     * @return
     */
    public static Date endOfThisMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    /**
     * 上月的开始时间
     */
    public static Date startOfLastMonth() {
        Calendar currentDate = Calendar.getInstance();
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        currentDate.set(Calendar.MILLISECOND, 0);
        currentDate.set(Calendar.DAY_OF_MONTH, 1);
        currentDate.set(Calendar.MONTH, 0);
        return currentDate.getTime();
    }

    /**
     * 上月的最后时间
     *
     * @return
     */
    public static Date endOfLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }


    /**
     * 某个月开始时间
     */
    public static Date startOfMonth(Date date) {
        Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(date);
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        currentDate.set(Calendar.MILLISECOND, 0);
        currentDate.set(Calendar.DAY_OF_MONTH, 1);
        return currentDate.getTime();
    }

    /**
     * 某个月最后时间
     *
     * @return
     */
    public static Date endOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    /**
     * 本季度开始时间
     *
     * @return
     */
    public static Date startQuarterTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        try {
            if (currentMonth <= 3) {
                c.set(Calendar.MONTH, 0);
            } else if (currentMonth <= 6) {
                c.set(Calendar.MONTH, 3);
            } else if (currentMonth <= 9) {
                c.set(Calendar.MONTH, 6);
            } else if (currentMonth <= 12) {
                c.set(Calendar.MONTH, 9);
            }
            c.set(Calendar.DATE, 1);
            return c.getTime();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 本季度结束时间
     *
     * @return
     */
    public static Date endQuarterTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int currentMonth = c.get(Calendar.MONTH) + 1;
        try {
            if (currentMonth <= 3) {
                c.set(Calendar.MONTH, 2);
                c.set(Calendar.DATE, 31);
            } else if (currentMonth <= 6) {
                c.set(Calendar.MONTH, 5);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth <= 9) {
                c.set(Calendar.MONTH, 8);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth <= 12) {
                c.set(Calendar.MONTH, 11);
                c.set(Calendar.DATE, 31);
            }
            return c.getTime();
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 上季度开始时间
     *
     * @return
     */
    public static Date getLastQuarterStartTime() {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(Calendar.MONTH, (startCalendar.get(Calendar.MONTH) / 3 - 1) * 3);
        startCalendar.set(Calendar.DAY_OF_MONTH, 1);
        startCalendar.set(Calendar.HOUR_OF_DAY, 0);
        startCalendar.set(Calendar.MINUTE, 0);
        startCalendar.set(Calendar.SECOND, 0);
        startCalendar.set(Calendar.MILLISECOND, 0);
        return startCalendar.getTime();
    }

    /**
     * 上季度结束时间
     *
     * @return
     */
    public static Date getLastQuarterEndTime() {
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(Calendar.MONTH, ((int) endCalendar.get(Calendar.MONTH) / 3 - 1) * 3 + 2);
        endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        endCalendar.set(Calendar.HOUR_OF_DAY, endCalendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        endCalendar.set(Calendar.MINUTE, endCalendar.getActualMaximum(Calendar.MINUTE));
        endCalendar.set(Calendar.SECOND, endCalendar.getActualMaximum(Calendar.SECOND));
        endCalendar.set(Calendar.MILLISECOND, endCalendar.getActualMaximum(Calendar.MILLISECOND));
        return endCalendar.getTime();
    }

    /**
     * 当天的开始时间
     */
    public static Date startOfTodDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 当天的结束时间
     */
    public static Date endOfTodDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static Date startOfLastDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }


    /**
     * 当天的结束时间
     */
    public static Date endOfLastDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static Date pastDaysZeroBeforeNow(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Calendar calendarToday = Calendar.getInstance();
        calendarToday.setTime(calendar.getTime());
        calendarToday.set(Calendar.HOUR_OF_DAY, 0);
        calendarToday.set(Calendar.MINUTE, 0);
        calendarToday.set(Calendar.SECOND, 0);
        calendarToday.set(Calendar.MILLISECOND, 0);
        return calendarToday.getTime();
    }

    public static Date addDays(Date originDate, Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(originDate);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + days);
        return calendar.getTime();
    }


    /**
     * 某年上季度 20203
     *
     * @return
     */
    public static String buildLastSeasonTimeValue(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        int season = month / 3;
        int year = calendar.get(Calendar.YEAR);
        StringBuilder key = new StringBuilder();
        key.append(season == 0 ? year - 1 : year);
        key.append(0);
        key.append(season == 0 ? 4 : season);
        return key.toString();
    }

    public static Long getValue(Long all, Long has, Long base) {
        return new BigDecimal(all)
                .divide(new BigDecimal(base), 2, BigDecimal.ROUND_HALF_DOWN)
                .multiply(new BigDecimal(has))
                .longValue();
    }

    public static List<Long> getDateDuration(Integer cycleType, Long rewardCycle) {
        Date startDate = DateUtils.parse("202104", DateUtils.YYYYMM);
        List<Long> dateList = Lists.newArrayList(startDate.getTime());
        if (Objects.equals(cycleType, 0)) {
            // 该月份的最后一天 如202102就是2021.2的第一天
            Date date = new Date(DateUtils.transferDayFromMonth(rewardCycle));
            dateList = Lists.newArrayList(DateUtils.startOfMonth(date).getTime(), DateUtils.endOfMonth(date).getTime());
        } else {
            // 季度中的每个月份的第一天
            List<Long> seasonList = DateUtils.transferDayFromSeason(rewardCycle);
            for (Long seasonTime : seasonList) {
                dateList.add(DateUtils.startOfMonth(new Date(seasonTime)).getTime());
                dateList.add(DateUtils.endOfMonth(new Date(seasonTime)).getTime());
            }
        }
        // 避免之前的数据影响 以上线后一个月的日期开始
        dateList = dateList.stream()
                .filter(date -> date >= startDate.getTime())
                .collect(Collectors.toList());
        return dateList;
    }


    public static List<Long> getDateDuration() {
        // 该月份的最后一天 如202102就是2021.2的第一天
        Date date = new Date(DateUtils.transferDayFromMonth(DateUtils.getMonth(System.currentTimeMillis())));
        return Lists.newArrayList(DateUtils.startOfMonth(date).getTime(), DateUtils.endOfMonth(date).getTime());
    }

    public static void main(String[] args) {
//        List<Long> dateList = getDateDuration(0, 202105L);
//        LongSummaryStatistics summary = dateList.stream()
//                .map(DateUtils::getDay)
//                .mapToLong(x -> x)
//                .summaryStatistics();
//        System.out.println(summary.getMin() + "," + summary.getMax());

//        System.out.println(StringUtils.reverse());
    }
}
