package com.capgemini.hotelbooking.bean;

public class HotelBean {
	private int hotelID;
	private String city;
	private String hotelName;
	private String address;
	private String description;
	private float avgRatePerNight;
	private String phoneNumber1;
	private String phoneNumber2;
	private String rating;
	private String email;
	private String fax;
	
	public HotelBean(int hotelID, String city, String hotelName,
			String address, String description, float avgRatePerNight,
			String phoneNumber1, String phoneNumber2, String rating,
			String email, String fax) {
		super();
		this.hotelID = hotelID;
		this.city = city;
		this.hotelName = hotelName;
		this.address = address;
		this.description = description;
		this.avgRatePerNight = avgRatePerNight;
		this.phoneNumber1 = phoneNumber1;
		this.phoneNumber2 = phoneNumber2;
		this.rating = rating;
		this.email = email;
		this.fax = fax;
	}
	public int getHotelID() {
		return hotelID;
	}
	public void setHotelID(int hotelID) {
		this.hotelID = hotelID;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getAvgRatePerNight() {
		return avgRatePerNight;
	}
	public void setAvgRatePerNight(float avgRatePerNight) {
		this.avgRatePerNight = avgRatePerNight;
	}
	public String getPhoneNumber1() {
		return phoneNumber1;
	}
	public void setPhoneNumber1(String phoneNumber1) {
		this.phoneNumber1 = phoneNumber1;
	}
	public String getPhoneNumber2() {
		return phoneNumber2;
	}
	public void setPhoneNumber2(String phoneNumber2) {
		this.phoneNumber2 = phoneNumber2;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	
	@Override
	public String toString() {
		return "Hotel(Hotel ID =" + hotelID + "City=" + city
				+ ", Hotel name=" + hotelName + ", Address=" + address
				+ ", description=" + description + ", Average cost of a room="
				+ avgRatePerNight + ", Phone number 1=" + phoneNumber1
				+ ", Phone number 2=" + phoneNumber2 + ", Rating=" + rating
				+ ", Email=" + email + ", Fax=" + fax + "]";
	}
	
}
