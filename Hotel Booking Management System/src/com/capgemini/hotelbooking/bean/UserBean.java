package com.capgemini.hotelbooking.bean;

public class UserBean {
	private String userID;
	private String password;
	private String role;
	private String userName;
	private String mobileNumber;
	private String address;
	private String email;
	private String phoneNumber;
	
	
	public UserBean(String userID, String password, String role,
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
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
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
		return "UserBean [userID=" + userID + ", password=" + password
				+ ", role=" + role + ", userName=" + userName
				+ ", mobileNumber=" + mobileNumber + ", address=" + address
				+ ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
	}
}
