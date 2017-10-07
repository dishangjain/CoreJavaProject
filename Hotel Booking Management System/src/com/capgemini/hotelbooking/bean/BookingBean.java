package com.capgemini.hotelbooking.bean;

import java.time.LocalDate;

public class BookingBean {
	private int bookingID;
	private int roomID;
	private int userID;
	private int numAdults;
	private int numChildren;
	private float amount;
	private LocalDate BookedFrom;
	private LocalDate BookedTo;
	
	public BookingBean(int bookingID, int roomID, int userID,
			int numAdults, int numChildren, float amount, LocalDate bookedFrom,
			LocalDate bookedTo) {
		super();
		this.bookingID = bookingID;
		this.roomID = roomID;
		this.userID = userID;
		this.numAdults = numAdults;
		this.numChildren = numChildren;
		this.amount = amount;
		BookedFrom = bookedFrom;
		BookedTo = bookedTo;
	}
	
	public int getBookingID() {
		return bookingID;
	}
	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}
	public int getRoomID() {
		return roomID;
	}
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getNumAdults() {
		return numAdults;
	}
	public void setNumAdults(int numAdults) {
		this.numAdults = numAdults;
	}
	public int getNumChildren() {
		return numChildren;
	}
	public void setNumChildren(int numChildren) {
		this.numChildren = numChildren;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public LocalDate getBookedFrom() {
		return BookedFrom;
	}
	public void setBookedFrom(LocalDate bookedFrom) {
		BookedFrom = bookedFrom;
	}
	public LocalDate getBookedTo() {
		return BookedTo;
	}
	public void setBookedTo(LocalDate bookedTo) {
		BookedTo = bookedTo;
	}

	@Override
	public String toString() {
		return "BookingBean [bookingID=" + bookingID + ", roomID=" + roomID
				+ ", userID=" + userID + ", numAdults=" + numAdults
				+ ", numChildren=" + numChildren + ", amount=" + amount
				+ ", BookedFrom=" + BookedFrom + ", BookedTo=" + BookedTo + "]";
	}
	
}
