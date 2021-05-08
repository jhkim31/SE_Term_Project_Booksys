package com.example.demo.controller;
import booksys.api.BookingApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


import com.example.demo.A;
import org.json.simple.JSONObject;

import java.sql.Date;
import java.sql.Time;
import java.util.*;
import javax.servlet.http.HttpServletRequest;


@RestController
public class testController {
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
	
	@PostMapping("/reservation/new_reservation")
	public boolean setReservation(
			@RequestParam int covers,
			@RequestParam String date,
			@RequestParam String time,
			@RequestParam int tno,
			@RequestParam String name,
			@RequestParam String phone
			) {
		System.out.println(covers);
		System.out.println(date);
		System.out.println(time);
		System.out.println(tno);
		System.out.println(name);
		System.out.println(phone);		
		return ba.addReservation(covers, date, time, tno,name,phone); 		
	}
	
	@RequestMapping(value = "/booking/get_booking_list")
	public Vector getReservationList(HttpServletRequest request) {
		System.out.println(request.getParameter("date"));
		return ba.getBookingList(request.getParameter("date"));
	}
	
	@PostMapping("/reservation/cancel_reservation")
	public boolean cancelReservation(
			@RequestParam int index
			) {	
		return ba.cancelReservation(index); 		
	}
	
	@PostMapping("/reservation/record_arrival")
	public boolean recordArrival(
			@RequestParam int index
			) {	
		return ba.recordArrival(index); 		
	}
	
	
}