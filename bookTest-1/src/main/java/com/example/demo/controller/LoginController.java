package com.example.demo.controller;
import booksys.api.BookingApi;
import booksys.api.LoginApi;

import org.springframework.web.bind.annotation.*;


import com.example.demo.A;
import org.json.simple.JSONObject;

import java.sql.Date;
import java.sql.Time;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@CrossOrigin(origins = "*")
@RestController
public class LoginController {
	LoginApi la = new LoginApi();
	
	@PostMapping("/login/loginRequest")
	public boolean loginRequest(
			@RequestParam String userId,
			@RequestParam String pw,
			HttpServletRequest request
			) {
		HttpSession session = request.getSession();
		boolean returnVal = la.checkUser(userId, pw); 
		if (returnVal) {
			session.setAttribute("id", userId);
			if (userId.equals("admin")) {
				session.setAttribute("role", "admin");
			}
			return true;
		} else {
			return false;
		}
		 
	}
	@PostMapping("/logout")
	public void logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("id");
		session.removeAttribute("role");
	}
	
}

