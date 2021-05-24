package booksys.api;

import booksys.application.domain.*;

import java.sql.Date;
import java.sql.Time;

import java.util.*;

public class LoginApi {
	private BookingSystem bs;

	public LoginApi() {
		bs = BookingSystem.getInstance();
	}
	
	public boolean checkUser(String userId, String pw) {
		return bs.checkUser(userId, pw);
	}
}
