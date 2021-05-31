/*
 * Restaurant Booking System: example code to accompany
 *
 * "Practical Object-oriented Design with UML"
 * Mark Priestley
 * McGraw-Hill (2004)
 */

package booksys.application.domain;

import booksys.application.persistency.*;

import java.sql.Date;
import java.sql.Time;
import java.util.Vector;

class Restaurant {
	BookingMapper bm = BookingMapper.getInstance();
	CustomerMapper cm = CustomerMapper.getInstance();
	TableMapper tm = TableMapper.getInstance();
	LoginMapper lm = LoginMapper.getInstance();
	MenuMapper mm = MenuMapper.getInstance();
	ReviewMapper rm = ReviewMapper.getInstance();

	public Vector getReservationNumber(Date sD, Date eD) {
		return bm.getReservationNumber(sD, eD);
	}
	
	public Vector getReservationPrice(Date sD, Date eD) {
		return bm.getReservationPrice(sD, eD);
	}

	Vector getBookings(Date date) {
		return bm.getBookings(date);
	}

	boolean addTable(Vector<String> v) {
		return tm.addTable(v);
	}

	boolean deleteTable(String number) {
		return tm.deleteTable(number);
	}

	boolean updateTable(Vector<String> v) {
		return tm.updateTable(v);
	}

	Vector getMyBookings(String userId) {
		return bm.getMyBookings(userId);
	}

	Vector getAllBookings() {
		return bm.getAllBookings();
	}

	String checkUser(String userId, String pw) {
		return lm.checkUser(userId, pw);
	}

	String kakaoLogin(String userId, String userName) {
		return lm.kakaoLogin(userId, userName);
	}

	Customer getCustomer(String name, String phone) {
		return cm.getCustomer(name, phone);
	}

	Table getTable(int n) {
		return tm.getTable(n);
	}

	static Vector getTableNumbers() {
		return TableMapper.getInstance().getTableNumbers();
	}

	public Booking makeReservation(int covers, Date date, Time time, int tno, String name, String phone,
			String userId, int[] menuArr) {
		Table t = getTable(tno);
		Customer c = getCustomer(name, phone);
		return bm.createReservation(covers, date, time, t, c, null, userId, menuArr);
	}

	public void updateReservation(Vector<String> v) {
		bm.updateReservation(v);
	}

	public Booking makeWalkIn(int covers, Date date, Time time, int tno) {
		Table t = getTable(tno);
		return bm.createWalkIn(covers, date, time, t);
	}

	public void updateBooking(Booking b) {
		bm.updateBooking(b);
	}

	public void removeBooking(Booking b) {
		bm.deleteBooking(b);
	}

	public void removeBooking(String oid) {
		bm.deleteBooking_oid(oid);
	}

	public Vector getMenu() {
		Vector menulist = mm.getMenuList();
		return menulist;
	}

	public void addMenu(int mid, String name, int price, String comment, String image) {
		mm.createMenu(mid, name, price, comment, image);
	}

	public void deleteMenu(Menu m) {
		mm.deleteMenu(m);
	}

	public void updateMenu(Menu m) {
		mm.updateMenu(m);
	}

	public Vector getReview() {
		Vector reviewlist = rm.getReviewList();
		return reviewlist;
	}

	public void addReview(int rid, String user, Date date, Time time, String content, int point) {
		rm.addReview(rid, user, date, time, content, point);
	}

	public void deleteReview(int o) {
		rm.deleteReview(o);

	}
}
