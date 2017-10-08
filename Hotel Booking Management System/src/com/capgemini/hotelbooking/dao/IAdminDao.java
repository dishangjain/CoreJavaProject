package com.capgemini.hotelbooking.dao;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.hotelbooking.bean.BookingBean;
import com.capgemini.hotelbooking.bean.HotelBean;
import com.capgemini.hotelbooking.bean.RoomBean;
import com.capgemini.hotelbooking.bean.UserBean;
import com.capgemini.hotelbooking.exception.BookingException;

public interface IAdminDao {
	public int addHotelDetails(HotelBean hotelBean) throws BookingException;
	public int updateHotelDetails(int hotelID,String attributeName,String attributeValue) throws BookingException;
	public boolean deleteHotelDetails(int hotelID) throws BookingException;
	public int addRoomDetails(RoomBean roomBean) throws BookingException;
	public int updateRoomDetails(int roomID,String attributeName,String attributeValue) throws BookingException;
	public boolean deleteRoomDetails(int roomID) throws BookingException;
	public List<BookingBean> viewBookingsOfHotel(int hotelID) throws BookingException;
	public List<BookingBean> viewBookingsOfDate(LocalDate date) throws BookingException;
	List<UserBean> viewGuestList(int hotelID) throws BookingException;
}
