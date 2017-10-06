package com.capgemini.hotelbooking.dao;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.hotelbooking.bean.BookingBean;
import com.capgemini.hotelbooking.bean.HotelBean;
import com.capgemini.hotelbooking.bean.RoomBean;
import com.capgemini.hotelbooking.exception.BookingException;

public interface IAdminDao {
	//TODO Add 1 functionality
	public int addHotelDetails(HotelBean hotelBean) throws BookingException;
	public int updateHotelDetails(String hotelID,String attributeName,String attributeValue) throws BookingException;
	public boolean deleteHotelDetails(String hotelID) throws BookingException;
	public int addRoomDetails(RoomBean roomBean) throws BookingException;
	public int updateRoomDetails(String roomID,String attributeName,String attributeValue) throws BookingException;
	public boolean deleteRoomDetails(String roomID) throws BookingException;
	public List<BookingBean> getBookingsOfHotel(String hotelID) throws BookingException;
	public List<BookingBean> getBookingsOfDate(LocalDate date) throws BookingException;
	public List<HotelBean> retrieveHotels() throws BookingException;
}
