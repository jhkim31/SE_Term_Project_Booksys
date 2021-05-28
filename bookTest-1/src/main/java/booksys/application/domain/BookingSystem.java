/*
 * Restaurant Booking System: example code to accompany
 *
 * "Practical Object-oriented Design with UML"
 * Mark Priestley
 * McGraw-Hill (2004)
 */

package booksys.application.domain;


import java.sql.Date;
import java.sql.Time;
import java.util.*;

public class BookingSystem {
	// Attributes:

	Date currentDate;
	Date today;

	// Associations:

	Restaurant restaurant = null;
	Vector currentBookings;
	Booking selectedBooking;
	
	// Singleton:

	private static BookingSystem uniqueInstance;

	public static BookingSystem getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new BookingSystem();
		}
		return uniqueInstance;
	}

	private BookingSystem() {
		today = new Date(Calendar.getInstance().getTimeInMillis());
		restaurant = new Restaurant();
		currentBookings = restaurant.getBookings(today);
	}

	// Observer: this is `Subject/ConcreteSubject'

	Vector observers = new Vector();

	public void addObserver(BookingObserver o) {
		observers.addElement(o);
	}

	public void notifyObservers() {
		Enumeration enums = observers.elements();
		while (enums.hasMoreElements()) {
			BookingObserver bo = (BookingObserver) enums.nextElement();
			bo.update();
		}
	}

	public boolean observerMessage(String message, boolean confirm) {
		BookingObserver bo = (BookingObserver) observers.elementAt(0);
		return bo.message(message, confirm);
	}

	// System messages:

	public void display(Date date) {
		currentDate = date;
		currentBookings = restaurant.getBookings(currentDate);
		selectedBooking = null;
		notifyObservers();
	}

	public boolean makeReservation(int covers, Date date, Time time, int tno, String name, String phone, String userId) {
		try {
			restaurant.makeReservation(covers, date, time, tno, name, phone, userId);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	public boolean addTable(Vector<String> v) {
		return restaurant.addTable(v);
	}
	
	public boolean deleteTable(String number) {
		return restaurant.deleteTable(number);
	}
	
	public boolean updateTable(Vector<String> v) {
		return restaurant.updateTable(v);
	}
	
	public boolean updateReservation(Vector<String> v) {
		try {
			restaurant.updateReservation(v);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	public Vector getReservationNumber(Date sD, Date eD) {
		return restaurant.getReservationNumber(sD, eD);
	}
	
	public Vector getBookingList(Date date) {
		currentBookings = restaurant.getBookings(date);
		return currentBookings;
	}
	
	public Vector getMyBookingList(String userId) {
		currentBookings = restaurant.getMyBookings(userId);
		return currentBookings;
	}
	
	public Vector getAllBookingList() {
		currentBookings = restaurant.getAllBookings();
		return currentBookings;
	}
	
	public boolean removeBooking(int index) {
		try {
			restaurant.removeBooking((Booking)currentBookings.get(index));
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	public boolean deleteBooking(String oid) {
		try {
			restaurant.removeBooking(oid);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public void makeWalkIn(int covers, Date date, Time time, int tno) {
		if (!doubleBooked(time, tno, null) && !overflow(tno, covers)) {
			Booking b = restaurant.makeWalkIn(covers, date, time, tno);
			currentBookings.addElement(b);
			notifyObservers();
		}
	}

	public boolean recordArrival(int index) {
		try {
			Calendar now = Calendar.getInstance();
			((Booking) currentBookings.get(index)).setArrivalTime(new Time(now.getTimeInMillis()));
			restaurant.updateBooking((Booking) currentBookings.get(index));
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	public String checkUser(String userId, String pw) {
		return restaurant.checkUser(userId, pw);
	}
	
	public String kakaoLogin(String userId, String userName) {
		return restaurant.kakaoLogin(userId, userName);
	}
	

	public void transfer(Time time, int tno) {
		if (selectedBooking != null) {
			if (selectedBooking.getTableNumber() != tno) {
				if (!doubleBooked(selectedBooking.getTime(), tno, selectedBooking)
						&& !overflow(tno, selectedBooking.getCovers())) {
					selectedBooking.setTable(restaurant.getTable(tno));
					restaurant.updateBooking(selectedBooking);
				}
			}
			notifyObservers();
		}
	}
	
	public Vector getMenuList() {
		Vector a = restaurant.getMenu();
		return a;
	}

	public void addMenu(int mid, String name, int price, String comment, String image) {
		restaurant.addMenu(mid, name, price, comment, image);
	}
	
	public void deleteMenu(Menu m) {
		restaurant.deleteMenu(m);
	}
	
	public void updateMenu(Menu m) {
		restaurant.updateMenu(m);
	}
	
	public Menu findMenu(int mid) {
		Vector menulist = restaurant.getMenu();
		for (Object m : menulist) {
			if(((Menu)m).getMid()==mid) return (Menu)m;
		}
		return null;
	}
	
	public Vector getReviewList() {
		Vector a = restaurant.getReview();
		return a;
	}
	
	public void addReview(int rid, String user, Date date, Time time, String content, int point) {
		restaurant.addReview(rid, user, date, time, content, point);
	}
	
	public void deleteReview(int o) {
		restaurant.deleteReview(o);
	}
	
	public Review findReview(int rid) {
		Vector reviewlist = restaurant.getReview();
		for (Object r : reviewlist) {
			if(((Review)r).getRid()==rid) return (Review)r;
		}
		return null;
	}

	private boolean doubleBooked(Time startTime, int tno, Booking ignore) {
		boolean doubleBooked = false;

		Time endTime = (Time) startTime.clone();
		endTime.setHours(endTime.getHours() + 2);

		Enumeration enums = currentBookings.elements();
		while (!doubleBooked && enums.hasMoreElements()) {
			Booking b = (Booking) enums.nextElement();
			if (b != ignore && b.getTableNumber() == tno && startTime.before(b.getEndTime())
					&& endTime.after(b.getTime())) {
				doubleBooked = true;
				observerMessage("Double booking!", false);
			}
		}
		return doubleBooked;
	}

	private boolean overflow(int tno, int covers) {
		boolean overflow = false;
		Table t = restaurant.getTable(tno);

		if (t.getPlaces() < covers) {
			overflow = !observerMessage("Ok to overfill table?", true);
		}

		return overflow;
	}

	// Other Operations:

	public Date getCurrentDate() {
		return currentDate;
	}

	public Enumeration getBookings() {
		return currentBookings.elements();
	}

	public Booking getSelectedBooking() {
		return selectedBooking;
	}

	public static Vector getTableNumbers() {
		return Restaurant.getTableNumbers();
	}
}
