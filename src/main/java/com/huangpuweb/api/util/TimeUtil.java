package com.huangpuweb.api.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

	
	/**
	 *    根据生日计算年龄
	 * @param birthday
	 * @return
	 */
	public static int getAgeByBirth(Date birthday) {
		int age = 0;
		try {
			Calendar now = Calendar.getInstance();
			now.setTime(new Date());// 当前时间

			Calendar birth = Calendar.getInstance();
			birth.setTime(birthday);

			if (birth.after(now)) {// 如果传入的时间，在当前时间的后面，返回0岁
				age = 0;
			} else {
				age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
				if (now.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR)) {
					age += 1;
				}
			}
			return age;
		} catch (Exception e) {// 兼容性更强,异常后返回数据
			return 0;
		}
	}

	
	
	public static void main(String args[]) throws ParseException {
		
		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        Date bithday = format.parse("1999-08-23 17:20:20");
	        int age = getAgeByBirth(bithday);
	        System.out.println(age);
		
		
	}
}
