/**
 * 
 */
package jp.ne.zaq.rinku.bkbin005;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;

/**
 * @author mitsutoshi
 */
public class SampleBean {
	private int code;
	private Calendar ymd;
	private int days;
	private String YYYYMM;
		
	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}
	/**
	 * @return the ymd
	 */
	public Calendar getYmd() {
		return ymd;
	}
	/**
	 * @param ymd the ymd to set
	 */
	public void setYmd(Calendar ymd) {
		this.ymd = ymd;
	}
	/**
	 * @return the days
	 */
	public int getDays() {
		return days;
	}
	/**
	 * @param days the days to set
	 */
	public void setDays(int days) throws NumberFormatException {
		if (days < -1 || days > 31) {
			throw new NumberFormatException(days + " is wrong days");
		}
		this.days = days;
	}

	/**
	 * @return the YYYYMM
	 */
	public String getYYYYMM() {
		int y =  ymd.get(Calendar.YEAR);
		int m =  ymd.get(Calendar.MONTH) + 1;
		return y + "" + m;
	}

	public void setYYYYMM(String yyyymm) throws NumberFormatException  {
    	if (yyyymm.length() != 6) {
    		throw new NumberFormatException(yyyymm + " is invalid");
    	}
    	String yyyy = yyyymm.substring(0, 4);
    	String mm = yyyymm.substring(4);
    	int y, m;
    	y = Integer.parseInt(yyyy);
    	m = Integer.parseInt(mm);
    	if (m < 1 || m > 12) {
    		throw new NumberFormatException(mm + " is invalid month");
    	}
    	ymd = new GregorianCalendar(y, m - 1, 1);
	}

	public Date getDate() {
		return ymd.getTime();
	}

	public void setDate(Date d) {
		ymd = new GregorianCalendar();
		ymd.setTime(d);
	}

	public java.sql.Date getSqlDate() {
		return null; // TODO
	}
	
	public void setSqlDate(java.sql.Date d) {
		ymd = new GregorianCalendar();
		ymd.setTime(d);
	}
	
	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		return code + " " + ymd + " " + days;
	}

}
