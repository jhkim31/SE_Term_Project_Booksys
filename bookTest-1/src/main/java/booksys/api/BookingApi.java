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

	public boolean addTable(Vector<String> v) {
		return bs.addTable(v);
	}

	public boolean deleteTable(String number) {
		return bs.deleteTable(number);
	}

	public boolean updateTable(Vector<String> v) {
		return bs.updateTable(v);
	}

	public boolean addReservation(int covers, String date, String time, int tno, String name, String phone,
			String userId, int[] menuArr) {
		Date d = Date.valueOf(date);
		Time t = Time.valueOf(time);

		return bs.makeReservation(covers, d, t, tno, name, phone, userId, menuArr);
	}

	public boolean updateReservation(Vector<String> v) {

		return bs.updateReservation(v);
	}

	public Vector getReservationNumber(String sDate, String eDate) {
		Date sD = Date.valueOf(sDate);
		Date eD = Date.valueOf(eDate);
		return bs.getReservationNumber(sD, eD);
	}
	
	public Vector getReservationPrice(String sDate, String eDate) {
		Date sD = Date.valueOf(sDate);
		Date eD = Date.valueOf(eDate);
		return bs.getReservationPrice(sD, eD);
	}
	

	public Vector getBookingList(String date) {
		Date d = Date.valueOf(date);
		return bs.getBookingList(d);
	}

	public Vector getMyBookingList(String userId) {

		return bs.getMyBookingList(userId);
	}

	public Vector getAllBookingList() {
		return bs.getAllBookingList();
	}

	public boolean cancelReservation(int index) {
		return bs.removeBooking(index);
	}

	public boolean deleteReservation(String oid) {
		return bs.deleteBooking(oid);
	}

	public boolean recordArrival(int index) {
		return bs.recordArrival(index);
	}

	public Vector getMenuList() {
		return bs.getMenuList();
	}

	public boolean addMenu(int mid, String name, int price, String comment, String image) {
		try {
			bs.addMenu(mid, name, price, comment, image);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean deleteMenu(int mid) {
		Menu m = bs.findMenu(mid);
		if (m != null) {
			try {
				bs.deleteMenu(m);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public boolean UpdateMenu(int mid, String name, int price, String comment, String image) {
		Menu m = new Menu(mid, name, price, comment, image);
		if (m != null) {
			try {
				bs.updateMenu(m);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public boolean addReview(int rid, String user, String date, String time, String content, int point) {
		Date d = Date.valueOf(date);
		Time t = Time.valueOf(time);
		try {
			bs.addReview(rid, user, d, t, content, point);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	public boolean deleteReview(int rid) {
		try {
			bs.deleteReview(rid);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Vector getReviewList() {
		return bs.getReviewList();
	}

}
