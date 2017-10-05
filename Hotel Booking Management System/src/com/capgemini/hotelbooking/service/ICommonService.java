package com.capgemini.hotelbooking.service;

import java.util.List;

import com.capgemini.hotelbooking.bean.HotelBean;
import com.capgemini.hotelbooking.exception.BookingException;

public interface ICommonService {
	public boolean Login(String username, String password) throws BookingException;
	List<HotelBean> retrieveHotels() throws BookingException;
}
