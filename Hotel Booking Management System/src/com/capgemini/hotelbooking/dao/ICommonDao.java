package com.capgemini.hotelbooking.dao;

import java.util.List;

import com.capgemini.hotelbooking.bean.HotelBean;
import com.capgemini.hotelbooking.bean.RoomBean;
import com.capgemini.hotelbooking.bean.UserBean;
import com.capgemini.hotelbooking.exception.BookingException;

public interface ICommonDao {
	public int registerUser(UserBean userBean) throws BookingException;
	public UserBean login(String username, String password) throws BookingException;
	List<HotelBean> retrieveHotels() throws BookingException;
	List<RoomBean> retrieveRooms() throws BookingException;
	public List<String> retrieveUserNames() throws BookingException;
}
