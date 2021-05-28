package booksys.application.domain;

import java.sql.Date;
import java.sql.Time;

public class Review {
	private int rid;
	private String user;
	private Date date;
	private Time time;
	private String content;
	private int point;

	public Review(int r, String u, Date d, Time t, String c, int p) {
		rid = r;
		user = u;
		date = d;
		time = t;
		content = c;
		point = p;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
}