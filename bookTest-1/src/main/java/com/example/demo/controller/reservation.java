package com.example.demo.controller;
import java.sql.Date ;
import java.sql.Time ;
import java.util.* ;

public class reservation {
	private int covers;
	private java.sql.Date date;
	private java.sql.Time time;
	private int tableNumber;
	private String name;
	private String phoneNumber;
	
	public reservation(int covers, java.sql.Date date, java.sql.Time time, int tableNumber,
			String name, String phoneNumber) {
		this.covers = covers;
		this.date = date;
		this.time = time;
		this.tableNumber = tableNumber;
		this.name = name;
		this.phoneNumber = phoneNumber;
				
	}
	
	public void setCovers(int covers) {
		this.covers = covers;
	}
	public int getCovers() {
		return this.covers;
	}
	
	public void setDate(java.sql.Date date) {
		this.date = date;
	}
	public java.sql.Date getDate(){
		return this.date;
	}
	
	public void setTime(java.sql.Time time) {
		this.time = time;
	}
	public java.sql.Time getTime(){
		return this.time;
	}
	
	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}
	public int getTableNumber() {
		return this.tableNumber;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
}
