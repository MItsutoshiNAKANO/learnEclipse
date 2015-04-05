/**
 * 
 */

import java.sql.Date;

/**
 * @author mitsutoshi
 *
 */
public class SampleBean {
	private int code;
	private Date ymd;
	private int days;
	
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
	public Date getYmd() {
		return ymd;
	}
	/**
	 * @param ymd the ymd to set
	 */
	public void setYmd(Date ymd) {
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
	public void setDays(int days) {
		this.days = days;
	}
	
	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		return code + " " + ymd + " " + days;
	}

}
