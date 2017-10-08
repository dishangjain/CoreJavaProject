package com.capgemini.hotelbooking.service;

import java.util.List;

import com.capgemini.hotelbooking.bean.BookingBean;
import com.capgemini.hotelbooking.bean.RoomBean;
import com.capgemini.hotelbooking.exception.BookingException;

public interface ICustomerService {
	public int bookRoom(BookingBean bookingBean) throws BookingException;
	public List<RoomBean> searchAvailableRooms(String city) throws BookingException;
	public List<List<Object>> viewBookingStatus(int bookingId, int userId) throws BookingException;
}