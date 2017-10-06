package com.capgemini.hotelbooking.dao;

import java.util.List;

import com.capgemini.hotelbooking.bean.BookingBean;
import com.capgemini.hotelbooking.bean.RoomBean;
import com.capgemini.hotelbooking.bean.UserBean;
import com.capgemini.hotelbooking.exception.BookingException;

public interface ICustomerDao{
	public int registerUser(UserBean userBean) throws BookingException;
	public int bookRoom(BookingBean bookingBean) throws BookingException;
	public BookingBean viewBookingStatus(int bookingId) throws BookingException;
	public List<RoomBean> searchAvailableRooms() throws BookingException;
}
