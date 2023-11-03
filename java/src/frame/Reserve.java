package frame;

import java.sql.Time;
import java.util.Date;

public class Reserve {
	private Date fromDate;
	private Date toDate;
	private Time fromTime;
	private Time toTime;
	private int userId;

	public Reserve(Date fromDate, Date toDate, Time fromTime, Time toTime, int id) {
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.fromTime = fromTime;
		this.toTime = toTime;
		this.userId=id;
	}

	@Override
	public String toString() {
		return "Reserve [fromDate=" + fromDate + ", toDate=" + toDate + ", fromTime=" + fromTime + ", toTime=" + toTime
				+ "id: "+userId+"]";
	}

	public Date getFromDate() {
		return fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public Time getFromTime() {
		return fromTime;
	}

	public Time getToTime() {
		return toTime;
	}

	public int getUserId() {
		return userId;
	}
	
	

}
