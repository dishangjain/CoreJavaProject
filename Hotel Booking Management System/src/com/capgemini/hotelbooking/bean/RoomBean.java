package com.capgemini.hotelbooking.bean;

import java.sql.Blob;

public class RoomBean {
	private String hotelID;
	private String roomID;
	private String roomNumber;
	private String roomType;
	private float perNightRate;
	private boolean availability;
	private Blob photo;
	
	public RoomBean(String hotelID, String roomID, String roomNumber,
			String roomType, float perNightRate, boolean availability,
			Blob photo) {
		super();
		this.hotelID = hotelID;
		this.roomID = roomID;
		this.roomNumber = roomNumber;
		this.roomType = roomType;
		this.perNightRate = perNightRate;
		this.availability = availability;
		this.photo = photo;
	}
	
	public String getHotelID() {
		return hotelID;
	}
	public void setHotelID(String hotelID) {
		this.hotelID = hotelID;
	}
	public String getRoomID() {
		return roomID;
	}
	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}
	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public float getPerNightRate() {
		return perNightRate;
	}
	public void setPerNightRate(float perNightRate) {
		this.perNightRate = perNightRate;
	}
	public boolean isAvailability() {
		return availability;
	}
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
	public Blob getPhoto() {
		return photo;
	}
	public void setPhoto(Blob photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "RoomBean [hotelID=" + hotelID + ", roomID=" + roomID
				+ ", roomNumber=" + roomNumber + ", roomType=" + roomType
				+ ", perNightRate=" + perNightRate + ", availability="
				+ availability + ", photo=" + photo + "]";
	}
}
