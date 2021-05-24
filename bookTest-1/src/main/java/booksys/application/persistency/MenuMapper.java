package booksys.application.persistency;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Vector;

import booksys.application.domain.Booking;
import booksys.application.domain.Customer;
import booksys.application.domain.Menu;
import booksys.application.domain.Reservation;
import booksys.application.domain.Table;
import booksys.storage.Database;

public class MenuMapper {
	// Singleton:

	private static MenuMapper uniqueInstance;

	public static MenuMapper getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new MenuMapper();
		}
		return uniqueInstance;
	}

	public Vector getMenuList() {
		Vector v = new Vector();
		try {
			Statement stmt = Database.getInstance().getConnection().createStatement();
			ResultSet rset = stmt.executeQuery("SELECT * FROM Menu");
			while (rset.next()) {
				int mid = rset.getInt(1);
				String name = rset.getString(2);
				int price = rset.getInt(3);
				PersistentMenu r = new PersistentMenu(mid, name, price);
				v.add(r);
			}
			rset.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return v;
	}

	public PersistentMenu createMenu(int mid, String name, int price) {
		int oid = Database.getInstance().getId();
		performUpdate("INSERT INTO Menu (mid, name, price)" + "VALUES ('" + mid + "', '" + name + "', '" + price
				+ "', '" + ")");

		return new PersistentMenu(mid, name, price);
	}

	public void updateMenu(Menu m) {
		PersistentMenu pm = (PersistentMenu) m;
		StringBuffer sql = new StringBuffer(128);

		sql.append("UPDATE ");
		sql.append("Menu");
		sql.append(" SET ");
		sql.append("mid = ");
		sql.append(pm.getId());
		sql.append(", name = '");
		sql.append(pm.getName().toString());
		sql.append("', price = '");
		sql.append(pm.getPrice());

		performUpdate(sql.toString());
	}

	public void deleteMenu(Menu m) {
		performUpdate("DELETE FROM " + "Menu" + " WHERE mid = '" + ((PersistentMenu) m).getId() + "'");
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