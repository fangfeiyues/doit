package com.fang.doit.ali;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author fangfeiyue
 * @Date 2021/1/31 8:17 下午
 */
public class Test02 {

    private BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    private volatile boolean running = true;

    /**
     * 代码实现生产者和消费者模式。要求保证单机的环境下生产和消费效率
     */
    public void producer(String content) {
        try {
            while (running) {
                queue.offer(content, 2, TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
//            log.info("producer InterruptedException:{}", e);
        }
    }


    public void stop() {
        running = false;
    }

    public void consume() {
        while (running) {
            try {
                String content = queue.take();
                System.out.println("消费内容:" + content);
            } catch (InterruptedException e) {
//                log.info("consume InterruptedException:{}", e);
            }
        }
    }


    public static List<Long> getDateDuration(Integer cycleType, Long rewardCycle) {
        List<Long> dateList = Lists.newArrayList();
        if (Objects.equals(cycleType, 1)) {
            // 该月份的最后一天 如202102就是2021.2的第一天
            Date date = new Date(DateUtils.transferDayFromMonth(rewardCycle));
            dateList = Lists.newArrayList(DateUtils.startOfMonth(date).getTime(), DateUtils.endOfMonth(date).getTime());
        } else {
            // 季度中的每个月份的第一天
            List<Long> seasonList = DateUtils.transferDayFromSeason(rewardCycle);
            for (Long seasonTime : seasonList) {
                dateList.add(DateUtils.startQuarterTime(new Date(seasonTime)).getTime());
                dateList.add(DateUtils.endQuarterTime(new Date(seasonTime)).getTime());
            }
        }
        return dateList;
    }

    public static String buildLastSeasonTimeValue(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        int month = calendar.get(Calendar.MONTH);
        int season = month / 3;
        int year = calendar.get(Calendar.YEAR);
        StringBuilder key = new StringBuilder();
        key.append(season == 0 ? year - 1 : year);
        key.append(0);
        key.append(season == 0 ? 4 : season);
        return key.toString();
    }

    public static Long getLastMonthTimeValue(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        int month = calendar.get(Calendar.MONTH)+1;
        StringBuilder key = new StringBuilder();
        key.append(calendar.get(Calendar.YEAR));
        if (month < 10) {
            key.append(0);
        }
        key.append(month);
        return Long.parseLong(key.toString());
    }


    public static String buildSeasonTimeValue(Long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));
        int month = calendar.get(Calendar.MONTH);
        int season = month / 3 + 1;
        StringBuilder key = new StringBuilder();
        key.append(calendar.get(Calendar.YEAR));
        key.append(0);
        key.append(season);
        return key.toString();
    }


    public static void main(String[] args) {
        Date curDate = new Date(1617259872000L);
//        List<Long> seasonDateList = DateUtils.transferDayFromSeason(Long.parseLong(buildSeasonTimeValue(curDate.getTime())));
//        Set<Long> monthSet = Optional.ofNullable(seasonDateList)
//                .orElse(Lists.newArrayList())
//                .stream()
//                .map(date -> Long.parseLong(DateUtils.buildMonthTimeValue(date)))
//                .filter(seasonMonth -> seasonMonth < Long.parseLong(DateUtils.buildMonthTimeValue(curDate.getTime())))
//                .collect(Collectors.toSet());
//        monthSet.add(getLastMonthTimeValue(curDate));
//        System.out.println(Lists.newArrayList(monthSet));

        System.out.println(getLastMonthTimeValue(curDate));
    }

}
