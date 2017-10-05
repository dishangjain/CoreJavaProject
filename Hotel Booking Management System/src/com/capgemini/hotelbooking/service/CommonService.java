package com.capgemini.hotelbooking.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.capgemini.hotelbooking.bean.HotelBean;
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
	public boolean Login(String username, String password)
			throws BookingException {
		return dao.Login(username, password);
	}

	@Override
	public List<HotelBean> retrieveHotels() throws BookingException {
		return dao.retrieveHotels();
	}
}
