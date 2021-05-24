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

	public boolean makeReservation(int covers, Date date, Time time, int tno, String name, String phone) {
		try {
			restaurant.makeReservation(covers, date, time, tno, name, phone);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	public Vector getBookingList(Date date) {
		currentBookings = restaurant.getBookings(date);
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
	
	public boolean getUser(String userId, String pw) {
		return restaurant.getUser(userId, pw);
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

	public void addMenu(int mid, String name, int price) {
		restaurant.addMenu(mid, name, price);
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
