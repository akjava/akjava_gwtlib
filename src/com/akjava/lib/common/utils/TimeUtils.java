package com.akjava.lib.common.utils;


public class TimeUtils {
	public static final int UNIT_YEAR=0;

	public static final int UNIT_DAY=1;
	public static final int UNIT_HOUR=2;
	public static final int UNIT_MINUTE=3;
	public static final int UNIT_SECOND=4;
	public static final int UNIT_MILLISECOND=5;

	public static final int UNIT_MONTH=6;
	
	public static TimeUnit2 getAboutTime(long ms){
		TimeValue timeValue=new TimeValue(ms);
		int unit=UNIT_MILLISECOND;
		int value=0;
		
		int largest=timeValue.getLargestUnit();
		switch(largest){
		case UNIT_YEAR:
			unit=UNIT_YEAR;
			value=timeValue.getYear();
		break;
		case UNIT_DAY:
			int day=timeValue.getDay();
			if(day>=30){
				unit=UNIT_MONTH;
				value=day/30;
			}else{
			unit=UNIT_DAY;
			value=timeValue.getDay();
			}
		break;
		case UNIT_HOUR:
			unit=UNIT_HOUR;
			value=timeValue.getHour();
		break;
		case UNIT_MINUTE:
			unit=UNIT_MINUTE;
			value=timeValue.getMinute();
		break;
		case UNIT_SECOND:
			unit=UNIT_SECOND;
			value=timeValue.getSecond();
		break;
		case UNIT_MILLISECOND:
			value=timeValue.getMillisecond();
		break;
		}
		return new TimeUnit2(unit,value);
	}
	
	public static class TimeUnit2{
		public TimeUnit2(int unit2, int value2) {
			unit=unit2;
			value=value2;
		}
		private int unit;
		public int getUnit() {
			return unit;
		}
		public void setUnit(int unit) {
			this.unit = unit;
		}
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		private int value;
	}
	
	public static class TimeValue{
	
		public int getLargestUnit(){
			if(year>0){
				return UNIT_YEAR;
			}
			if(day>0){
				return UNIT_DAY;
			}
			if(hour>0){
				return UNIT_HOUR;
			}
			if(minute>0){
				return UNIT_MINUTE;
			}
			if(second>0){
				return UNIT_SECOND;
			}
			
			return UNIT_MILLISECOND;
		}
		
		
		private static final long hour_millisecond=(60*60*1000);
		private int millisecond;
		private int second;
		private int minute;
		private int hour;
		private int day;

		private int year;
		
		
		
		public TimeValue(long millisecond){
			
		int minute_millisecond=60*1000;
		int second_millisecond=1000;
		int hour=(int)(millisecond/hour_millisecond);
		long remain=millisecond%hour_millisecond;
		int minute=(int)(remain/minute_millisecond);
		remain=remain%minute_millisecond;
		int second=(int)(remain/second_millisecond);
		remain=remain%second_millisecond;
		
		
		
		if(hour>=24){
			int dv=hour/24;
			hour=hour%24;
			if(dv>=365){
				int yv=dv/365;
				dv=dv%365;
				setYear(yv);
			}
			setDay(dv);
		}
		setHour(hour);
		
		setMinute(minute);
		setSecond(second);
		setMillisecond((int)remain);
		}

		public int getMillisecond() {
			return millisecond;
		}

		public void setMillisecond(int millisecond) {
			this.millisecond = millisecond;
		}

		public int getSecond() {
			return second;
		}

		public void setSecond(int second) {
			this.second = second;
		}

		public int getMinute() {
			return minute;
		}

		public void setMinute(int minute) {
			this.minute = minute;
		}

		public int getHour() {
			return hour;
		}

		public void setHour(int hour) {
			this.hour = hour;
		}

		public int getDay() {
			return day;
		}

		public void setDay(int day) {
			this.day = day;
		}

		public int getYear() {
			return year;
		}

		public void setYear(int year) {
			this.year = year;
		}
		
		public String toString(){
			return year+" "+"."+day+" "+hour+":"+minute+":"+second+"."+millisecond;
		}
	}
}
