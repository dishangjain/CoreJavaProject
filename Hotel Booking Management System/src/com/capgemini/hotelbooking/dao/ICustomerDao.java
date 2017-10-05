package com.capgemini.hotelbooking.dao;

import com.capgemini.hotelbooking.bean.BookingBean;
import com.capgemini.hotelbooking.bean.UserBean;
import com.capgemini.hotelbooking.exception.BookingException;

public interface ICustomerDao{
	public int registerUser(UserBean userBean) throws BookingException;
	public int bookRoom(BookingBean bookingBean) throws BookingException;
	public BookingBean viewBookingStatus(int bookingId) throws BookingException;
}
