package com.capgemini.hotelbooking.bean;

public class UserBean {
	private int userID;
	private String password;
	private String role;
	private String userName;
	private String mobileNumber;
	private String address;
	private String email;
	private String phoneNumber;
	
	
	public UserBean(int userID, String password, String role,
			String userName, String mobileNumber, String address, String email,
			String phoneNumber) {
		super();
		this.userID = userID;
		this.password = password;
		this.role = role;
		this.userName = userName;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Override
	public String toString() {
		return "User(User ID=" + userID + ", Username=" + userName
				+ ", Mobile number=" + mobileNumber + ", Address=" + address
				+ ", Email=" + email + ", Phone number=" + phoneNumber + ")\n";
	}
}
