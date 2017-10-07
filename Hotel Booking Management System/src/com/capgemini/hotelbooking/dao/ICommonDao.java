package com.capgemini.hotelbooking.dao;

import com.capgemini.hotelbooking.bean.UserBean;
import com.capgemini.hotelbooking.exception.BookingException;

public interface ICommonDao {
	public UserBean login(String username, String password) throws BookingException;
}
