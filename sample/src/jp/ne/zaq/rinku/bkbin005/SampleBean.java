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
	private String YYYYMM;
	private int days;
	private DbState state;
	
	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		return code + " " + YYYYMM + " " + days + " " + state + " " + ymd;
	}
	
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
		
		int y =  ymd.get(Calendar.YEAR);
		int m =  ymd.get(Calendar.MONTH) + 1;
		YYYYMM = y + "" + ((m > 9) ? (m) : ("0" + m));
	}

	/**
	 * @return the YYYYMM
	 */
	public String getYYYYMM() {
		return YYYYMM;
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
    	this.ymd = new GregorianCalendar(y, m - 1, 1);
    	this.YYYYMM = yyyymm;
	}

	public Date getDate() {
		return ymd.getTime();
	}

	public java.sql.Date getSqlDate() {
		return new java.sql.Date(ymd.getTimeInMillis());
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
	 * @return the state
	 */
	public DbState getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(DbState state) {
		this.state = state;
	}

}
