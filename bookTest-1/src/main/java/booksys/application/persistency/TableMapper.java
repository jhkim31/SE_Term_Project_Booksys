/*
 * Restaurant Booking System: example code to accompany
 *
 * "Practical Object-oriented Design with UML"
 * Mark Priestley
 * McGraw-Hill (2004)
 */

package booksys.application.persistency;

import booksys.storage.Database;

import java.sql.*;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class TableMapper {
	// Implementation of hidden cache

	private Hashtable cache;

	private PersistentTable getFromCache(int oid) {
		Integer key = new Integer(oid);
		return (PersistentTable) cache.get(key);
	}

	private PersistentTable getFromCacheByNumber(int tno) {
		PersistentTable t = null;
		Enumeration enums = cache.elements();
		while (t == null & enums.hasMoreElements()) {
			PersistentTable tmp = (PersistentTable) enums.nextElement();
			if (tmp.getNumber() == tno) {
				t = tmp;
			}
		}
		return t;
	}

	private void addToCache(PersistentTable t) {
		Integer key = new Integer(t.getId());
		cache.put(key, t);
	}

	// Constructor:

	private TableMapper() {
		cache = new Hashtable();
	}

	// Singleton:
	private void performUpdate(String sql) {
		try {
			Statement stmt = Database.getInstance().getConnection().createStatement();
			int updateCount = stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static TableMapper uniqueInstance;

	public static TableMapper getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new TableMapper();
		}
		return uniqueInstance;
	}

	public boolean addTable(Vector<String> v) {
		try {
			performUpdate("insert into `Table` (`number`, places) values(" + v.get(0) + ", " + v.get(1) + ")");
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteTable(String number) {
		try {
			performUpdate("delete from `Table` where oid = " + number);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean updateTable(Vector<String> v) {
		try {
			StringBuffer sql = new StringBuffer(128);

			sql.append("update `Table` set places =");
			sql.append(v.get(1));
			sql.append(" WHERE number = ");
			sql.append(v.get(0));

			performUpdate(sql.toString());

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public PersistentTable getTable(int tno) {
		PersistentTable t = getFromCacheByNumber(tno);
		if (t == null) {
			t = getTable("SELECT * FROM `Table` WHERE number='" + tno + "'");
			if (t != null) {
				addToCache(t);
			}
		}
		return t;
	}

	PersistentTable getTableForOid(int oid) {
		PersistentTable t = getFromCache(oid);
		if (t == null) {
			t = getTable("SELECT * FROM `Table` WHERE oid ='" + oid + "'");
			if (t != null) {
				addToCache(t);
			}
		}
		return t;
	}

	private PersistentTable getTable(String sql) {
		PersistentTable t = null;
		try {
			Statement stmt = Database.getInstance().getConnection().createStatement();
			ResultSet rset = stmt.executeQuery(sql);
			while (rset.next()) {
				int oid = rset.getInt(1);
				int number = rset.getInt(2);
				int places = rset.getInt(3);
				t = new PersistentTable(oid, number, places);
			}
			rset.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}

	public Vector getTableNumbers() {
		Vector<int[]> v = new Vector<int[]>();
		try {
			Statement stmt = Database.getInstance().getConnection().createStatement();
			ResultSet rset = stmt.executeQuery("SELECT * FROM `Table` ORDER BY number");
			while (rset.next()) {
				int[] tmp = { rset.getInt(1), rset.getInt(2), rset.getInt(3) };
//				v.addElement(new Integer(rset.getInt(2)));
				v.addElement(tmp);
			}
			rset.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return v;
	}

}
