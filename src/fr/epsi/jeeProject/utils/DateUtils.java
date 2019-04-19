package fr.epsi.jeeProject.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	public Date getDateDuJour() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		dateFormat.format(date);
		return date;
	}
	
	
}
