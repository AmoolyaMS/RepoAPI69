package com.vtigercrm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtils 
{
	SimpleDateFormat sim;

	public int getRandomNum() {
		Random r = new Random();
		int randomnum = r.nextInt(1000);
		return randomnum;
	}

	public String getsystemDateyyyymmdd() {
		Date dateobj = new Date();
		sim = new SimpleDateFormat("yyyy-MM-dd");
		String date = sim.format(dateobj);
		return date;
	}

	public String getrequiredDateyyyymmdd(int days) {
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String date = sim.format(cal.getTime());
		return date;

	}
}
