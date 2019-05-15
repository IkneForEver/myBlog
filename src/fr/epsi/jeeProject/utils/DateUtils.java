package fr.epsi.jeeProject.utils;


import java.util.Calendar;
import java.sql.Date;

public class DateUtils {
	
	public Date getDateDuJour() {
		Date date = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
		return date;
	}
	
	
}
