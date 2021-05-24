package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

@CrossOrigin(origins = "*")
@Controller
public class PageController {
 
	@RequestMapping(value = "/booking/booking_page")
	public String booking_page(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		String a = (String)session.getAttribute("id");
		if (a != null) {
			return "/booking/booking.html";
		} else {
			return "member/login.html";
		}
		
	}
	
	@RequestMapping(value = "/booking/cancel_booking")
	public String cancel_booking(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		String a = (String)session.getAttribute("id");
		if (a != null) {
			return "/booking/cancelBooking.html";
		} else {
			return "member/login.html";
		}
		
	}
	
	@RequestMapping(value = "/booking/change_booking")
	public String change_booking(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		String a = (String)session.getAttribute("id");
		if (a != null) {
			return "/booking/changeBooking.html";
		} else {
			return "member/login.html";
		}
		
	}
	
	@RequestMapping(value = "/booking/show_booking_list")
	public String show_booking_list(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		String a = (String)session.getAttribute("id");
		if (a != null) {
			return "/booking/showBooking.html";
		} else {
			return "member/login.html";
		}
		
	}
	
	@RequestMapping(value = "/")
	public String Main_page(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		String a = (String)session.getAttribute("id");
		if (a != null) {
			return "/Main.html";
		} else {
			return "member/login.html";
		}
		
	}
	
	@RequestMapping(value = "/menu")
	public String menu_page(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		String a = (String)session.getAttribute("id");
		if (a != null) {
			return "/menu/menu.html";
		} else {
			return "member/login.html";
		}
		
	}
	
	@RequestMapping(value = "/review")
	public String review_page(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		String a = (String)session.getAttribute("id");
		if (a != null) {
			return "/review/review.html";
		} else {
			return "member/login.html";
		}
		
	}
	
	@RequestMapping(value = "/sample/booking")
	public String booking_sample(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		String a = (String)session.getAttribute("id");
		if (a != null) {
			return "/sample/booking_sample.html";
		} else {
			return "member/login.html";
		}
		
	}
	
	@RequestMapping(value = "/sample/menu")
	public String menu_sample(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		String a = (String)session.getAttribute("id");
		if (a != null) {
			return "/sample/menu_sample.html";
		} else {
			return "member/login.html";
		}
		
	}
	
	@RequestMapping(value = "/login_page")
	public String login_page() throws Exception {
		return "/member/login.html";
	}
	
	@RequestMapping(value = "/register_page")
	public String register_page(HttpServletRequest request) throws Exception {
		return "/member/register.html";
	}
	
	@RequestMapping(value = "/String2")
	public String root_test2(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();	
		session.setAttribute("id", "kim");
		return "test2/index";
	}

	@RequestMapping(value = "/getSession")
	public String root_test3(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		String a = (String)session.getAttribute("id");
		if (a != null) {
			return "passPage";
		} else {
			return "login";
		}
	}
	
	@RequestMapping(value = "/logout")
	public String login(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute("id");
		return "login";
	}
}