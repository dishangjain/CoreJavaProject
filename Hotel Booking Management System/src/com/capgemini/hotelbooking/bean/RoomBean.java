package com.capgemini.hotelbooking.bean;


public class RoomBean {
	private int hotelID;
	private int roomID;
	private String roomNumber;
	private String roomType;
	private float perNightRate;
	private boolean available;
	private String photo;
	
	public RoomBean(int hotelID, int roomID, String roomNumber,
			String roomType, float perNightRate, boolean availability,
			String photo) {
		super();
		this.hotelID = hotelID;
		this.roomID = roomID;
		this.roomNumber = roomNumber;
		this.roomType = roomType;
		this.perNightRate = perNightRate;
		this.available = availability;
		this.photo = photo;
	}
	
	public int getHotelID() {
		return hotelID;
	}
	public void setHotelID(int hotelID) {
		this.hotelID = hotelID;
	}
	public int getRoomID() {
		return roomID;
	}
	public void setRoomID(int roomID) {
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
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean availability) {
		this.available = availability;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "RoomBean [hotelID=" + hotelID + ", roomID=" + roomID
				+ ", roomNumber=" + roomNumber + ", roomType=" + roomType
				+ ", perNightRate=" + perNightRate + ", availability="
				+ available + ", photo=" + photo + "]";
	}
}
