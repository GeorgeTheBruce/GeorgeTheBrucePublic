package com.techelevator.model.campground;

public class Campground {
	
	private Long campId;
	private Long parkId;
	private String name;
	private int open;
	private int close;
	private double fee;
	public Long getCampId() {
		return campId;
	}
	public Long getParkId() {
		return parkId;
	}
	public String getName() {
		return name;
	}
	public int getOpen() {
		return open;
	}
	public void setCampId(Long campId) {
		this.campId = campId;
	}
	public void setParkId(Long parkId) {
		this.parkId = parkId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setOpen(int open) {
		this.open = open;
	}
	public void setClose(int close) {
		this.close = close;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public int getClose() {
		return close;
	}
	public double getFee() {
		return fee;
	}
	@Override
	public String toString() {
		return this.name + "\t\t"+ getMonth(this.getOpen())+ "\t\t "+getMonth(this.getClose())+ "\t\t$"+ this.getFee();
	}

	
	public String getMonth(int open) {
		String month;
		switch(open) {
		case 1:
			month = "January";
			return month;
		case 2:
			month ="February";
			return month;
		case 3:
			month = "March";
			return month;
		case 4:
			month = "April";
			return month;
		case 5:
			month = "May";
			return month;
		case 6:
			month = "June";
			return month;
		case 7:
			month = "July";
			return month;
		case 8:
			month = "August";
			return month;
		case 9:
			month = "September";
			return month;
		case 10:
			month = "October";
			return month;
		case 11:
			month = "November";
			return month;
		case 12:
			month = "December";
			return month;
		default: 
			return month = "Invalid Month";
			
				
			
			
			
		}

	}}
