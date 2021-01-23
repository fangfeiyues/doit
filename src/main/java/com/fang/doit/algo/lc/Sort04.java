package com.fang.doit.algo.lc;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 排序
 *
 * @author fangfeiyue
 * @Date 2020/12/2 10:16 上午
 */
public class Sort04 {

    /**
     * 147：给你链表的头结点head请将其按升序排列并返回排序后的链表。O(nlogn)时间复杂度和常数级空间复杂度
     *
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        // 1.暴力求解交换两个链表节点，快排思想节点不变交换value值


        return null;
    }

    public static Long getMonth(Long dayTime) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = format.parse(String.valueOf(dayTime));
        return Long.valueOf(buildMonthTimeValue(date.getTime()));
    }

    public static String buildMonthTimeValue(Long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));
        int month = calendar.get(Calendar.MONTH) + 1;
        calendar.set(Calendar.MONTH, month);
        month = calendar.get(Calendar.MONTH);
        StringBuilder key = new StringBuilder();
        key.append(calendar.get(Calendar.YEAR));
        if (month < 10) {
            key.append(0);
        }
        key.append(calendar.get(Calendar.MONTH));
        return key.toString();
    }

    public static void main(String[] args) {
        try {
            System.out.println(Sort04.getMonth(20210611L));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
