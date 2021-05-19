package booksys.api;

import booksys.application.domain.*;

import java.sql.Date;
import java.sql.Time;

import java.util.*;

public class BookingApi {
	private BookingSystem bs;

	public BookingApi() {
		bs = BookingSystem.getInstance();
	}

	public Vector getTableNumber() {
		return BookingSystem.getTableNumbers();
	}

	public boolean addReservation(int covers, String date, String time, int tno, String name, String phone) {
		Date d = Date.valueOf(date);
		Time t = Time.valueOf(time);

		return bs.makeReservation(covers, d, t, tno, name, phone);
	}

	public Vector getBookingList(String date) {
		Date d = Date.valueOf(date);
		return bs.getBookingList(d);
	}

	public boolean cancelReservation(int index) {
		return bs.removeBooking(index);
	}

	public boolean recordArrival(int index) {
		return bs.recordArrival(index);
	}

	public boolean getUser(String userId, String pw) {
		return bs.getUser(userId, pw);
	}

}
