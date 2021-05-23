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

@CrossOrigin(origins = "*")
@RestController
public class LoginController {
	BookingApi ba = new BookingApi(); 
	
	@RequestMapping("/asdfasdf")
	public String root_test() throws Exception {
		A a = new A();
		return a.returnString();
	}
	
}

