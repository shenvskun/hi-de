package sk.tool.idno;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetAgeFromIdNo {
	public static void main(String[] args) {
		System.out.println(getAgeByIDNumber("431002197711241069"));
	}
	
	public static int getAgeByIDNumber(String idNumber) { 
		int invalidAge = -1;
		String dateStr; 
		if (idNumber.length() == 15) { 
			dateStr = "19" + idNumber.substring(6, 12); 
		} else if (idNumber.length() == 18) { 
			dateStr = idNumber.substring(6, 14); 
		} else {//默认是合法身份证号,但不排除有意外发生 
			return invalidAge; 
		} 

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd"); 
		try { 
			Date birthday = simpleDateFormat.parse(dateStr); 
		return getAgeByDate(birthday); 
		} catch (ParseException e) { 
			return invalidAge; 
		} 
	} 
	
	/** 
	*根据生日计算年龄 
	* @param dateStr 这样格式的生日 1990-01-01 
	*/ 
	public static int getAgeByDateString(String dateStr) { 
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		try { 
			Date birthday = simpleDateFormat.parse(dateStr); 
			return getAgeByDate(birthday); 
		} catch (ParseException e) { 
			return -1; 
		} 
	} 

	public static int getAgeByDate(Date birthday) { 
		int invalidAge = -1;
		Calendar calendar = Calendar.getInstance(); 
		//calendar.before()有的点bug 
		if (calendar.getTimeInMillis() - birthday.getTime() < 0L) { 
			return invalidAge; 
		} 
	
		int yearNow = calendar.get(Calendar.YEAR); 
		int monthNow = calendar.get(Calendar.MONTH); 
		int dayOfMonthNow = calendar.get(Calendar.DAY_OF_MONTH); 
		calendar.setTime(birthday); 
	
		int yearBirthday = calendar.get(Calendar.YEAR); 
		int monthBirthday = calendar.get(Calendar.MONTH); 
		int dayOfMonthBirthday = calendar.get(Calendar.DAY_OF_MONTH); 
		int age = yearNow - yearBirthday; 
	
		if ((monthNow == monthBirthday && dayOfMonthNow < dayOfMonthBirthday) || monthNow < monthBirthday) { 
			age--; 
		} 
		return age; 
	} 
}
