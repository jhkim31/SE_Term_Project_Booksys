package booksys.application.persistency;
import java.sql.Date;
import java.sql.Time;

import booksys.application.domain.Review;


public class PersistentReview extends Review {
	private int rid;

	public PersistentReview(int r, String u, Date date, Time time, String c, int p) {
		super(r, u, date, time, c, p);
		rid = r;	
	}
	
	int getId() {
		return rid;
	}
}