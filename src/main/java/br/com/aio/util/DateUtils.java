package br.com.aio.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class DateUtils {
	
	public static String dateToString(Date date){
		Objects.requireNonNull(date, "Data não pode ser nula");	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(date);
	}
	
	public static String monthAndYearToString(Date date){
		Objects.requireNonNull(date, "Data não pode ser nula");
		SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
		return sdf.format(date);
	}
	
	public static Date createNewDate(String date){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			return sdf.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static String dateToString(LocalDate date){
		Objects.requireNonNull(date, "Data não pode ser nula");	
		return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	
	public static LocalDate toLocalDate(Date date){
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	public static int getCurrentYear() {
		return LocalDate.now().getYear();
	}

}
