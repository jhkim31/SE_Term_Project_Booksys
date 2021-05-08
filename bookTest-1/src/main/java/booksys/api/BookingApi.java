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
	
	public Vector getReservationList(String date) {
		Date d = Date.valueOf(date);
		return bs.getReservationList(d);
	}
	
	public boolean cancelReservation() {
		return bs.removeReservation();
	}

}
