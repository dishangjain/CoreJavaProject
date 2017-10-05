package com.capgemini.hotelbooking.service;

import com.capgemini.hotelbooking.bean.BookingBean;
import com.capgemini.hotelbooking.bean.UserBean;
import com.capgemini.hotelbooking.exception.BookingException;

public interface ICustomerService {
	public int registerUser(UserBean userBean) throws BookingException;
	public int bookRoom(BookingBean bookingBean) throws BookingException;
	public BookingBean viewBookingStatus(int bookingId) throws BookingException;
}