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

	Vector getBookings(Date date) {
		return bm.getBookings(date);
	}

	boolean getUser(String userId, String pw) {
		return lm.getUser(userId, pw);
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

	public Booking makeReservation(int covers, Date date, Time time, int tno, String name, String phone) {
		Table t = getTable(tno);
		Customer c = getCustomer(name, phone);
		return bm.createReservation(covers, date, time, t, c, null);
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

	public Vector getMenu() {
		Vector menulist = mm.getMenuList();
		return menulist;
	}

	public void addMenu(int mid, String name, int price) {
		mm.createMenu(mid, name, price);
	}

	public void deleteMenu(Menu m) {
		mm.deleteMenu(m);
	}

	public void updateMenu(Menu m) {
		mm.updateMenu(m);
	}
}
