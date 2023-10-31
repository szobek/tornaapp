package frame;

import java.sql.Time;
import java.util.Date;

public class Reserve {
	private Date fromDate;
	private Date toDate;
	private Time fromTime;
	private Time toTime;

	public Reserve(Date fromDate, Date toDate, Time fromTime, Time toTime) {
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.fromTime = fromTime;
		this.toTime = toTime;
	}

	@Override
	public String toString() {
		return "Reserve [fromDate=" + fromDate + ", toDate=" + toDate + ", fromTime=" + fromTime + ", toTime=" + toTime
				+ "]";
	}

}
