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
public class RestApiController {
	BookingApi ba = new BookingApi(); 

	
	@RequestMapping("/String")
	public String root_test() throws Exception {
		A a = new A();
		return a.returnString();
	}
	
	@RequestMapping("/Json")
	public JSONObject jsonTest() {
		JSONObject js = new JSONObject();
		js.put("a", "b");
		return js;
	}
	
	@RequestMapping("/get_table")
	public Vector returnTableNumbers() {
		return ba.getTableNumber();
	}
	
	@PostMapping("/table/add_table")
	public boolean addTable(
			@RequestParam String number,
			@RequestParam String places
			) {
		Vector<String> v = new Vector();
		v.add(number);
		v.add(places);
		return ba.addTable(v); 		
	}
	
	@PostMapping("/table/delete_table")
	public boolean deleteTable(
			@RequestParam String number
			) {
		return ba.deleteTable(number); 		
	}
	
	@PostMapping("/table/update_table")
	public boolean updateTable(
			@RequestParam String number,
			@RequestParam String places
			) {
		Vector<String> v = new Vector();
		v.add(number);
		v.add(places);
		return ba.updateTable(v); 		
	}

	@PostMapping("/reservation/new_reservation")
	public boolean setReservation(
			@RequestParam int covers,
			@RequestParam String date,
			@RequestParam String time,
			@RequestParam int tno,
			@RequestParam String name,
			@RequestParam String phone,
			@RequestParam int menu1,
			@RequestParam int menu2,
			@RequestParam int menu3,
			@RequestParam int menu4,
			@RequestParam int menu5,
			@RequestParam int menu6,
			@RequestParam int menu7,
			@RequestParam int menu8,
			@RequestParam int totalPrice,
			HttpServletRequest request
			) {
		HttpSession session = request.getSession();
		System.out.println((String)session.getAttribute("id"));
		int[] menuArr = {menu1, menu2, menu3, menu4, menu5, menu6, menu7, menu8, totalPrice};
		return ba.addReservation(covers, date, time, tno,name,phone, (String)session.getAttribute("id"), menuArr); 		
	}
	
	@PostMapping("/reservation/update_reservation")
	public boolean updateReservation(
			@RequestParam String covers,
			@RequestParam String date,
			@RequestParam String time,
			@RequestParam String tno,
			@RequestParam String oid
			) {
		Vector<String> v = new Vector();
		v.add(covers);
		v.add(date);
		v.add(time);
		v.add(tno);
		v.add(oid);
		return ba.updateReservation(v); 		
	}
	
	@RequestMapping(value = "/booking/get_booking_list")
	public Vector getReservationList(HttpServletRequest request) {
		System.out.println(request.getParameter("date"));
		return ba.getBookingList(request.getParameter("date"));
	}
	
	@RequestMapping(value = "/booking/get_my_booking_list")
	public Vector getMyReservationList(HttpServletRequest request) {
		HttpSession session = request.getSession();
		System.out.println((String)session.getAttribute("id"));
		return ba.getMyBookingList((String)session.getAttribute("id"));
	}
	
	@RequestMapping("/booking/get_reservation_number")
	public Vector reservationNumber(HttpServletRequest request) {
		String sDate = request.getParameter("sDate");
		String eDate = request.getParameter("eDate");
		return ba.getReservationNumber(sDate, eDate);
	}
	
	@RequestMapping("/booking/get_reservation_price")
	public Vector reservationPrice(HttpServletRequest request) {
		String sDate = request.getParameter("sDate");
		String eDate = request.getParameter("eDate");
		return ba.getReservationPrice(sDate, eDate);
	}
	
	@RequestMapping(value = "/booking/get_all_booking_list")
	public Vector getAllReservationList(HttpServletRequest request) {
		return ba.getAllBookingList();
	}
	
	
	@PostMapping("/reservation/cancel_reservation")
	public boolean cancelReservation(
			@RequestParam int index
			) {	
		return ba.cancelReservation(index); 		
	}
	
	@PostMapping("/reservation/delete_reservation")
	public boolean cancelReservation(
			@RequestParam String oid
			) {	
		return ba.deleteReservation(oid); 		
	}
	
	@PostMapping("/reservation/record_arrival")
	public boolean recordArrival(
			@RequestParam int index
			) {	
		return ba.recordArrival(index); 		
	}
	@RequestMapping("/menu/get_menu_list")
	public Vector getMenuList() {
		return ba.getMenuList();
	}

	@PostMapping("/menu/add_menu")
	public boolean addMenu(
			@RequestParam int mid, 
			@RequestParam String name, 
			@RequestParam int price,
			@RequestParam String comment, 
			@RequestParam String image
			) {
		System.out.println(mid);
		System.out.println(name);
		System.out.println(price);
		System.out.println(comment);
		System.out.println(image);
		return ba.addMenu(mid, name, price, comment, image);
	}

	@PostMapping("/menu/delete_menu")
	public boolean deleteMenu(
			@RequestParam int mid
			) {
		System.out.println(mid);
		return ba.deleteMenu(mid);
	}

	@RequestMapping("/review/get_review_list")
	public Vector getReviewList() {
		return ba.getReviewList();
	}

	@PostMapping("/review/add_review")
	public boolean addReview(
			@RequestParam int rid, 
			@RequestParam String date,
			@RequestParam String time, 
			@RequestParam String content, 
			@RequestParam int point,
			HttpServletRequest request
			) {
		HttpSession session = request.getSession();
		System.out.println((String)session.getAttribute("id"));
		System.out.println(date);
		System.out.println(time);
		System.out.println(content);
		System.out.println(point);
		return ba.addReview(rid, (String)session.getAttribute("id"), date, time, content, point);
	}

	@PostMapping("/review/delete_review")
	public boolean deleteReview(
			@RequestParam int rid
			) {
		System.out.println(rid);
		return ba.deleteReview(rid);
	}
}