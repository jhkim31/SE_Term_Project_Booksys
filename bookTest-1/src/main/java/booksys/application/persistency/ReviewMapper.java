package booksys.application.persistency;

import java.sql.Date;
import java.sql.Time;
import java.util.Vector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import booksys.application.domain.Booking;
import booksys.application.domain.Customer;
import booksys.application.domain.Menu;
import booksys.application.domain.Reservation;
import booksys.application.domain.Table;
import booksys.application.domain.Review;
import booksys.storage.Database;

public class ReviewMapper {

	private static ReviewMapper uniqueInstance;

	public static ReviewMapper getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new ReviewMapper();
		}
		return uniqueInstance;
	}

	public Vector getReviewList() {
		Vector v = new Vector();
		try {
			Statement stmt = Database.getInstance().getConnection().createStatement();
			ResultSet rset = stmt.executeQuery("SELECT * FROM Review");
			while (rset.next()) {
				int rid = rset.getInt(1);
				String user = rset.getString(2);
				Date date = rset.getDate(3);
				Time time = rset.getTime(4);
				String content = rset.getString(5);
				int point = rset.getInt(6);
				PersistentReview r = new PersistentReview(rid, user, date, time, content, point);
				v.add(r);
			}
			rset.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return v;
	}

	public PersistentReview addReview(int rid, String user, Date date, Time time, String content, int point) {
		int oid = Database.getInstance().getId();
		performUpdate("INSERT INTO Review (userId, date, time, content, point) VALUES ('" + user
				+ "', '" + date + "', '" + time + "', '" + content + "', '" + point + "')");

		return new PersistentReview(rid, user, date, time, content, point);
	}

	public void deleteReview(int o) {
		performUpdate("DELETE FROM " + "Review" + " WHERE oid = '" + o + "'");
	}

	private void performUpdate(String sql) {
		try {
			Statement stmt = Database.getInstance().getConnection().createStatement();
			int updateCount = stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}