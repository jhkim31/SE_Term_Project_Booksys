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

	public Vector getReservationNumber(String sDate, String eDate) {
		Date sD = Date.valueOf(sDate);
		Date eD = Date.valueOf(eDate);
		return bs.getReservationNumber(sD, eD);
	}
	public Vector getBookingList(String date) {
		Date d = Date.valueOf(date);
		return bs.getBookingList(d);
	}
	
	public Vector getAllBookingList() {
		return bs.getAllBookingList();
	}

	public boolean cancelReservation(int index) {
		return bs.removeBooking(index);
	}

	public boolean recordArrival(int index) {
		return bs.recordArrival(index);
	}

	
	public Vector getMenuList() {
		return bs.getMenuList();
	}
	
	public boolean addMenu(int mid, String name, int price) {
		try {
			bs.addMenu(mid, name, price);
		}catch(Exception e) { 
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean deleteMenu(int mid) {
		Menu m = bs.findMenu(mid);
		if(m != null) {
			try {
				bs.deleteMenu(m);
			}catch(Exception e) { 
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	public boolean UpdateMenu(int mid, String name, int price) {
		Menu m = new Menu(mid, name, price);
		if(m != null) {
			try {
				bs.updateMenu(m);
			}catch(Exception e) { 
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

}
