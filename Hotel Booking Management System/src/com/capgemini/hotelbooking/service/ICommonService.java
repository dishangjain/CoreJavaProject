package com.capgemini.hotelbooking.service;

import com.capgemini.hotelbooking.bean.UserBean;
import com.capgemini.hotelbooking.exception.BookingException;

public interface ICommonService {
	UserBean login(String username, String password) throws BookingException;
}
