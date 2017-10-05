package com.capgemini.hotelbooking.dao;

import java.util.List;

import com.capgemini.hotelbooking.bean.HotelBean;
import com.capgemini.hotelbooking.exception.BookingException;

public interface ICommonDao {
	public boolean Login(String username, String password) throws BookingException;
	public List<HotelBean> retrieveHotels() throws BookingException;
}
