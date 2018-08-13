package modele.baseObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MilkDate extends MilkVar {
	
	private static final int MilkFormat = SimpleDateFormat.MEDIUM;

	public static GregorianCalendar getGregorianCalendar(String sDate,int style) throws ParseException {
		DateFormat dateFormat = DateFormat.getDateInstance(style);
		Date tempDate;
		GregorianCalendar newGC=new GregorianCalendar();
		tempDate = dateFormat.parse(sDate);
		newGC.setTime(tempDate);
        return newGC;
	}
	
	public static GregorianCalendar getGregorianCalendar(String sDate,String style) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat(style);
		Date tempDate;
		GregorianCalendar newGC=new GregorianCalendar();
		tempDate = dateFormat.parse(sDate);
		newGC.setTime(tempDate);
        return newGC;
	}

	public static String getDateAsString(GregorianCalendar dateVoulu,int style){
		DateFormat dateFormat = DateFormat.getDateInstance(style);
		return dateFormat.format(dateVoulu.getTime());
	}
	
	public static String getDateAsString(GregorianCalendar dateVoulu,String style){
		DateFormat dateFormat = new SimpleDateFormat(style);
		return dateFormat.format(dateVoulu.getTime());
	}
	
	// Fields
	
	private GregorianCalendar milkdate;

	// Constructors
	
	/**
	 * Create a new Milkdate from the actual date + 40 year. 
	 * */
	public MilkDate() {
		super();
		milkdate = new GregorianCalendar();
		milkdate.add(Calendar.YEAR, 40);
	}
	
	/**
	 * Create a new Milkdate with specified GregorianCalendar. 
	 * */
	public MilkDate(GregorianCalendar milkdate) {
		super();
		this.milkdate = milkdate;
	}
	
	/**
	 * Create a new Milkdate from the actual date + 40 year - specified age in year. 
	 * ex actual date is 20/06/2000 and age is 10, milkdate will be 2000+40-10 = 2030
	 * */
	public MilkDate(double age) {
		this();
		Long ageMillisecond = Math.round(age*8766*3600000);
		milkdate.setTimeInMillis(milkdate.getTimeInMillis()-ageMillisecond); 
	}

	/**
	 * Create a new Milkdate from an other.
	 * */
	public MilkDate(MilkDate original) {
		super(original);
		this.milkdate = new GregorianCalendar();
		this.milkdate.setTimeInMillis(original.getMilkdate().getTimeInMillis());
	}

	// field methods
	
	public GregorianCalendar getMilkdate() {
		return milkdate;
	}

	public void setMilkdate(GregorianCalendar milkdate) {
		this.milkdate = milkdate;
	}
	
	// toString & toXml methods

	@Override
	public String toString() {
		return "" +getDateAsString(milkdate,MilkFormat) + "";
	}
}
