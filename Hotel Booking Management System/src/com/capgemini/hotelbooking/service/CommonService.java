package com.capgemini.hotelbooking.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.capgemini.hotelbooking.bean.HotelBean;
import com.capgemini.hotelbooking.bean.RoomBean;
import com.capgemini.hotelbooking.bean.UserBean;
import com.capgemini.hotelbooking.dao.CommonDao;
import com.capgemini.hotelbooking.dao.ICommonDao;
import com.capgemini.hotelbooking.exception.BookingException;

public class CommonService implements ICommonService {
	private ICommonDao dao;
	
	static Logger myLogger = Logger.getLogger("myLogger");
	
	public CommonService() throws BookingException {
		myLogger.info("Service: Dao injected.");
		dao = new CommonDao();
	}
	
	@Override
	public UserBean login(String username, String password)
			throws BookingException {
		return dao.login(username, password);
	}

	@Override
	public List<HotelBean> retrieveHotels() throws BookingException {
		return dao.retrieveHotels();
	}

	@Override
	public List<RoomBean> retrieveRooms() throws BookingException {
		return dao.retrieveRooms();
	}

	@Override
	public int registerUser(UserBean userBean) throws BookingException {
		return dao.registerUser(userBean);
	}
}
