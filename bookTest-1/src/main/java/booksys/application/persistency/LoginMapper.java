package booksys.application.persistency;

import booksys.application.domain.Booking;
import booksys.application.domain.Reservation;
import booksys.application.domain.Customer;
import booksys.application.domain.Table;
import booksys.storage.*;
import booksys.application.domain.User;

import java.sql.*;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class LoginMapper {

	private static LoginMapper uniqueInstance;

	public static LoginMapper getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new LoginMapper();
		}
		return uniqueInstance;
	}

	public String checkUser(String userId, String pw) {
		try {
			Statement stmt = Database.getInstance().getConnection().createStatement();
			ResultSet rset = stmt.executeQuery("SELECT * FROM Users WHERE userId='" + userId + "'");
			if (rset.next()) {
				if (rset.getString(2).equals(pw)) {
					return rset.getString(3);
				}
			}
			return "";			
		} catch (Exception e) {
			System.out.println(e);
			return "";
		}

	}
	
	public String kakaoLogin(String userId, String userName) {
		try {
			Statement stmt = Database.getInstance().getConnection().createStatement();
			ResultSet rset = stmt.executeQuery("SELECT * FROM Users WHERE userId='" + userId + "'");
			if (!rset.next()) {
				StringBuffer sql = new StringBuffer(128);

				sql.append("INSERT INTO Users Values(");
				sql.append("'" + userId + "', ");
				sql.append("'',");
				sql.append("'" + userName + "')");
				performUpdate(sql.toString());
			}
			return userName;	
		} catch (Exception e) {
			System.out.println(e);
			return "";
		}

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
