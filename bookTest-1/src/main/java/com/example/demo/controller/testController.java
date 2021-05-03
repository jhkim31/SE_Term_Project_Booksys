package com.example.demo.controller;
import booksys.api.BookingApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import com.example.demo.A;
import org.json.simple.JSONObject;
import java.util.*;


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
	
	@RequestMapping("/booking/tableNumbers")
	public Vector returnTableNumbers() {
		return ba.getTableNumber();
	}
	
	@PostMapping("/reservation")
	public int setReservation(
			@RequestParam int covers
			) {
		System.out.println(covers);
		ba.addReservation();
		return covers;		
	}
	
}