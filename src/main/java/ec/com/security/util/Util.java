package ec.com.security.util;

import java.util.Calendar;
import java.util.Date;

public class Util {

	Util() {
	}

	public static Date addSecondsToDate(Date date, int seconds) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, seconds);
		return calendar.getTime();
	}

}
