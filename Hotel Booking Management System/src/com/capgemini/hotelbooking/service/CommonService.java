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
	
	private static String mobileNumberPattern = "[7-9][0-9]{9}";
	private static String emailPattern = "[A-Z a-z]*[@][A-Z a-z]*[.](com|org|in|co.in)";
	
	static Logger myLogger = Logger.getLogger("myLogger");
	
	public CommonService() throws BookingException {
		myLogger.info("Service: Dao injected.");
		dao = new CommonDao();
	}
	
	public static boolean validateMobileNumber(String mobileNumber){
		boolean flag = false;
		if(mobileNumber.matches(mobileNumberPattern))
		{
			flag = true;
		}
		return flag;
	}
	
	public static boolean validateEmail(String email){
		boolean flag = false;
		if(email.matches(emailPattern))
		{
			flag = true;
		}
		return flag;
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

	@Override
	public List<String> retrieveUserNames() throws BookingException {
		return dao.retrieveUserNames();
	}
}
