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
		String userName = la.checkUser(userId, pw); 
		if (userName.length() != 0) {
			session.setAttribute("id", userId);
			session.setAttribute("userName", userName);
			if (userId.equals("admin")) {
				session.setAttribute("role", "admin");
			} else {
				session.setAttribute("role", "user");
			}
			return true;
		} else {
			return false;
		}
		 
	}
	
	@PostMapping("/login/kakao_login")
	public boolean kakaoLoginRequest(
			@RequestParam String userId,
			@RequestParam String userName,
			HttpServletRequest request
			) {
		HttpSession session = request.getSession();
		String returnVal = la.kakaoLogin(userId, userName); 
		
		if (userName.length() != 0) {
			session.setAttribute("id", userId);
			session.setAttribute("userName", userName);
			if (userId.equals("1746254379")) {
				session.setAttribute("role", "admin");
			} else {
				session.setAttribute("role", "user");
			}
			return true;
		} else {
			return false;
		}
		 
	}
	@PostMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("id");
		session.removeAttribute("role");
		session.removeAttribute("userName");
		return "";
		
	}
	
	@RequestMapping(value = "/session_check")
	public Vector<String> sessionCheck(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		Vector<String> v = new Vector<String>();
		if (id != null) {
			v.add((String)session.getAttribute("role"));
			v.add((String)session.getAttribute("userName"));
			return v;
		} else {
			return v;
		}
	}
	
}

