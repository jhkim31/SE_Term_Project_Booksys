package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.A;
import org.json.simple.JSONObject;


@RestController
public class testController {

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
}