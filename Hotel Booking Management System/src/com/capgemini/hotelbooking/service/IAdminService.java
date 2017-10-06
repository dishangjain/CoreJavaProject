package com.capgemini.hotelbooking.service;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.hotelbooking.bean.BookingBean;
import com.capgemini.hotelbooking.bean.HotelBean;
import com.capgemini.hotelbooking.exception.BookingException;

public interface IAdminService {
	public int addHotelDetails(HotelBean hotelBean) throws BookingException;
	public int updateHotelDetails(String hotelID,String attributeName,String attributeValue) throws BookingException;
	public int updateRoomDetails(String roomID,String attributeName,String attributeValue) throws BookingException;
	public List<BookingBean> getBookingsOfHotel(String hotelID) throws BookingException;
	public List<BookingBean> getBookingsOfDate(LocalDate date) throws BookingException;
	List<HotelBean> retrieveHotels() throws BookingException;
}