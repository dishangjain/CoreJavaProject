package com.capgemini.hotelbooking.service;

import org.apache.log4j.Logger;

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
}
