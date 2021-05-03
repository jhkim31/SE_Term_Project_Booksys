package booksys.api;
import booksys.application.domain.* ;

import java.sql.Date ;
import java.sql.Time ;

import java.util.* ;


public class BookingApi {
	private BookingSystem bs;
	public BookingApi() {
		bs = BookingSystem.getInstance();
	}
	public Vector getTableNumber() {
		return BookingSystem.getTableNumbers();
	}
	
	public void addReservation() {
		Date d = Date.valueOf("2021-05-04");
		Time t = Time.valueOf("18:30:00");
		System.out.println(d.getClass().getName());
		System.out.println(d);
		System.out.println(t.getClass().getName());
		System.out.println(t);
		
		bs.makeReservation(1,d, t, 1, "kim", "010");
	}
	

}
