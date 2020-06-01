package com.huangpuweb.api.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by wizzer on 2016/6/24.
 */

public class DateUtil {
    private static final Locale DEFAULT_LOCALE = Locale.CHINA;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取当前时间(yyyy-MM-dd)
     *
     * @return yyyy-MM-dd
     */
    public static String getDate() {
        return DateFormatUtils.format(new Date(), "yyyy-MM-dd", DEFAULT_LOCALE);
    }

    /**
     * 获取当前时间(HH:mm:ss)
     *
     * @return HH:mm:ss
     */
    public static String getTime() {
        return DateFormatUtils.format(new Date(), "HH:mm:ss", DEFAULT_LOCALE);
    }

    /**
     * 获取当前时间(yyyy-MM-dd HH:mm:ss)
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getDateTime() {
        return DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss", DEFAULT_LOCALE);
    }

    /**
     * 转换日期格式(yyyy-MM-dd HH:mm:ss)
     *
     * @param date
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String formatDateTime(Date date) {
        if (date == null) return "";
        return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss", DEFAULT_LOCALE);
    }

    /**
     * 转换日期格式(yyyy-MM-dd HH:mm:ss)
     *
     * @param date
     * @param f
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String format(Date date, String f) {
        if (date == null) return "";
        return DateFormatUtils.format(date, f, DEFAULT_LOCALE);
    }

    /**
     * 时间戳日期
     *
     * @param time
     * @return
     */
    public static String getDate(long time) {
        return DateFormatUtils.format(new Date(time * 1000), "yyyy-MM-dd HH:mm:ss", DEFAULT_LOCALE);
    }

    /**
     * 时间戳日期
     *
     * @param time
     * @param f
     * @return
     */
    public static String getDate(long time, String f) {
        return DateFormatUtils.format(new Date(time * 1000), f, DEFAULT_LOCALE);
    }


    /**
     * 获取月份
     * @param date yyyy-MM-dd HH:mm:ss
     * @return HH
     * @throws ParseException
     */
    public static String getMonth(String date) throws ParseException {
        Date date1 = sdf.parse(date);
        String newDate = DateFormatUtils.format(date1, "MM", DEFAULT_LOCALE);
        return newDate;
    }

    /**
     * 获取年份
     * @param date yyyy-MM-dd HH:mm:ss
     * @return HH
     * @throws ParseException
     */
    public static String getYear(String date) throws ParseException {
        Date date1 = sdf.parse(date);
        String newDate = DateFormatUtils.format(date1, "yyyy", DEFAULT_LOCALE);
        return newDate;
    }

    /**
     * 获取小时
     * @param date yyyy-MM-dd HH:mm:ss
     * @return HH
     * @throws ParseException
     */
    public static String getHour(String date) throws ParseException {
        Date date1 = sdf.parse(date);
        String newDate = DateFormatUtils.format(date1, "HH", DEFAULT_LOCALE);
        return newDate;
    }

    /**
     * 获取分钟
     * @param date yyyy-MM-dd HH:mm:ss
     * @return mm
     */
    public static String getMinute(String date) throws ParseException {
        Date date1 = sdf.parse(date);
        String newDate = DateFormatUtils.format(date1, "mm", DEFAULT_LOCALE);
        return newDate;
    }

    /**
     * 获取小时：分钟
     * @param date yyyy-MM-dd HH:mm:ss
     * @return HH:mm
     * @throws ParseException
     */
    public static String getHM(String date) throws ParseException {
        Date date1 = sdf.parse(date);
        String newDate = DateFormatUtils.format(date1, "HH:mm", DEFAULT_LOCALE);
        return newDate;
    }
    /**
     * 获取明天日期 yyyy-MM-dd
     * @return yyyy-MM-dd
     */
    public static String getTomorrow() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(calendar.DATE,1);
        String date = DateFormatUtils.format(calendar.getTime(), "yyyy-MM-dd", DEFAULT_LOCALE);
        return date;
    }
    /**
     * 获取昨天日期 yyyy-MM-dd
     * @return yyyy-MM-dd
     */
    public static String getYesterday() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(calendar.DATE,-1);
        String date = DateFormatUtils.format(calendar.getTime(), "yyyy-MM-dd", DEFAULT_LOCALE);
        return date;
    }

    /**
     * 根据今日日期获取去年同一天日期 yyyy-MM-dd
     * @param todayDateTIme 今日日期
     * @return yyyy-MM-dd
     */
    public static String getLastYearToday(String todayDateTIme) throws ParseException {
        Date date = sdf.parse(todayDateTIme);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.YEAR,-1);
        String format = DateFormatUtils.format(calendar.getTime(), "yyyy-MM-dd ", DEFAULT_LOCALE);
        return format;
    }

    /**
     * 根据当前日期获取上月同一天日期
     * @param todayDateTIme
     * @return yyyy-MM-dd
     */
    public static String getLastMonthToday(String todayDateTIme) throws ParseException {
        Date date = sdf.parse(todayDateTIme);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.MONTH,-1);
        String format = DateFormatUtils.format(calendar.getTime(), "yyyy-MM-dd ", DEFAULT_LOCALE);
        return format;
    }

    /**
     * 获取今天00:00:00
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getTodayZero() {
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.set(Calendar.MINUTE,0);
        ca.set(Calendar.SECOND,0);
        Date time = ca.getTime();
        String zero = DateFormatUtils.format(time, "yyyy-MM-dd HH:mm:ss", DEFAULT_LOCALE);
        return zero;
    }

    /**
     * 获取前N个小时
     * @param ihour 获取第几个小时
     * @return YYYY-MM-dd HH
     */
    public static String getlastHour(int ihour) {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.HOUR_OF_DAY,ca.get(Calendar.HOUR_OF_DAY)-ihour);
        String lastHour = DateFormatUtils.format(ca.getTime(), "yyyy-MM-dd HH", DEFAULT_LOCALE);
        return lastHour;
    }

    /**
     * 获取当天日期的第N个小时第M分钟第K秒 23:59:59
     * @param n 时
     * @param m 分
     * @param k 秒
     * @return yyyy-MMM-dd HH:ss:mm
     */
    public static String getToday23(int n,int m,int k) {
        Calendar ca = Calendar.getInstance();
        //ca.set(ca.get(Calendar.YEAR),ca.get(Calendar.MONTH),ca.get(Calendar.HOUR_OF_DAY),23,59,59);
        ca.set(Calendar.HOUR_OF_DAY, n);
        ca.set(Calendar.MINUTE,m);
        ca.set(Calendar.SECOND,k);
        Date time = ca.getTime();
        String today23 = DateFormatUtils.format(time, "yyyy-MM-dd HH:ss:mm", DEFAULT_LOCALE);
        return today23;
    }

    /**
     * 根据当前时间获取过去第N个小时
     * @param n
     * @return
     */
    public static String getlastHourDate(int n) {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.HOUR_OF_DAY,ca.get(Calendar.HOUR_OF_DAY)-n);
        String lastHourDate = DateFormatUtils.format(ca.getTime(), "yyyy-MM-dd HH:ss:mm", DEFAULT_LOCALE);
        return lastHourDate;
    }

    /**
     * 获取第二天的0点0分0秒
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getTomorrowZero() {
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DAY_OF_MONTH,1);
        ca.set(Calendar.HOUR_OF_DAY,0);
        ca.set(Calendar.MINUTE,0);
        ca.set(Calendar.SECOND,0);
        String fm = DateFormatUtils.format(ca.getTime(), "yyyy-MM-dd HH:mm:ss", DEFAULT_LOCALE);
        return fm;
    }

    /**
     * 根据date（yyyy-MM-dd)获取前N天日期
     * @param date yyyy-MM-dd
     * @param n
     * @return yyyy-MM-dd
     */
    public static String getLastNDayByCurrent(String date, int n) {
        Date _date= null;
        try {
            _date = sdf.parse(date+" 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar ca = Calendar.getInstance();
        ca.setTime(_date);
        ca.add(Calendar.DAY_OF_MONTH,n);
        ca.set(Calendar.HOUR_OF_DAY,0);
        ca.set(Calendar.MINUTE,0);
        ca.set(Calendar.SECOND,0);
        String format = DateFormatUtils.format(ca.getTime(), "yyyy-MM-dd", DEFAULT_LOCALE);
        return format;
    }

    /**
     * 根据date yyyy-MM-dd HH:mm:ss 获取 yyyy-MM-dd
     * @param date  yyyy-MM-dd HH:mm:ss
     * @return yyyy-MM-dd
     */
    public static String getYearMonthDay(String date) {
        Date parse = null;
        if (null!=date){
            try {
                parse = sdf.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else{
            parse=new Date();
        }
        String format = DateFormatUtils.format(parse, "yyyy-MM-dd", DEFAULT_LOCALE);
        return format;
    }
    /**
     * 获取当年的第一天
     * @return
     */
    public static Date getCurrYearFirst(){
        Calendar currCal=Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }


    /**
     * 获取某年第一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }


    /**
     * 获取当前时间的时间戳  yyyyMMddHHmmss
     * @return
     */
    public static String getDateTimeStamp() {
        return DateFormatUtils.format(new Date(),"yyyyMMddHHmmss",DEFAULT_LOCALE);
    }

    /**
     * 根据当前日期获取前N天第x点y分z秒
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getLastNDay(int n,int x,int y,int z) {
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DAY_OF_MONTH,n);
        ca.set(Calendar.HOUR_OF_DAY,x);
        ca.set(Calendar.MINUTE,y);
        ca.set(Calendar.SECOND,z);
        String lastNDay = DateFormatUtils.format(ca.getTime(), "yyyy-MM-dd HH:mm:ss", DEFAULT_LOCALE);
        return lastNDay;
    }

    public static void main(String args[]){
//       System.out.println(DateFormatUtils.format(DateUtil.getCurrYearFirst().getTime(), "yyyy-MM-dd HH:mm:ss", DEFAULT_LOCALE));
    }
}
