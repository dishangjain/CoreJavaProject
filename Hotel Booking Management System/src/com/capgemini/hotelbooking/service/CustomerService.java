package com.capgemini.hotelbooking.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.capgemini.hotelbooking.bean.BookingBean;
import com.capgemini.hotelbooking.bean.RoomBean;
import com.capgemini.hotelbooking.dao.CustomerDao;
import com.capgemini.hotelbooking.dao.ICustomerDao;
import com.capgemini.hotelbooking.exception.BookingException;

public class CustomerService implements ICustomerService {
private ICustomerDao dao;

	
	private static String userIDPattern = "[0-9]{1,4}";
	private static String mobileNumberPattern = "[7-9][0-9]{9}";
	private static String emailPattern = "[A-Z a-z]*[@][A-Z a-z]*[.](com|org|in|co.in)";
	private static String phoneNumberPattern = "[0-9]{10}";
	
	private static String dateStringPattern = "[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])";
	private static String faxPattern = "[0-9]{6}";

	
	
	static Logger myLogger = Logger.getLogger("myLogger");
	
	public CustomerService() throws BookingException {
		myLogger.info("Service: Dao injected.");
		dao = new CustomerDao();
	}
	
/*	private static boolean isGreaterThanEqual(LocalDate localDate1,LocalDate localDate2)
	{
		int diffYears = Math.abs(localDate1.getYear() - localDate2.getYear());
		int diffMonths = Math.abs(localDate1.getMonthValue() - localDate2.getMonthValue());
		int diffDays = Math.abs(localDate1.getDayOfMonth() - localDate2.getDayOfMonth());
		if(diffYears > 0){
			return true;
		}
		else if(diffYears == 0 && diffMonths > 0){
			return true;
		}
		else if(diffYears == 0 && diffMonths==0 && diffDays >= 0){
			return true;
		}
		else{
			return false;
		}
	}*/
	
	
	public static boolean validateuserID(String userID){
		boolean flag = false;
		if(userID.matches(userIDPattern))
		{
			flag = true;
		}
		return flag;
	}
	
	public static boolean validatefax(String fax){
		boolean flag = false;
		if(fax.matches(faxPattern))
		{
			flag = true;
		}
		return flag;
	}
	
	public static boolean validatemobileNumber(String mobileNumber){
		boolean flag = false;
		if(mobileNumber.matches(mobileNumberPattern))
		{
			flag = true;
		}
		return flag;
	}
	
	public static boolean validateemail(String email){
		boolean flag = false;
		if(email.matches(emailPattern))
		{
			flag = true;
		}
		return flag;
	}
	

	

	
	public static boolean validatephoneNumber(String phoneNumber){
		boolean flag = false;
		if(phoneNumber.matches(phoneNumberPattern))
		{
			flag = true;
		}
		return flag;
	}
	
	public static boolean validateDate(String dateString){
		boolean flag = false;
		if(dateString.matches(dateStringPattern))
		{
			flag = true;
		}
		int year = Integer.parseInt(dateString.substring(0, 4));
		int month = Integer.parseInt(dateString.substring(5, 7));
		int date = Integer.parseInt(dateString.substring(8, 10));
		if(month > 12 || month <= 0 || date > 31 || date <= 0 || year < 0){
			flag = false;
		}
		
		return flag;
	}

	@Override
	public int bookRoom(BookingBean bookingBean) throws BookingException {
		return dao.bookRoom(bookingBean);
	}

	@Override
	public List<List<Object>> viewBookingStatus(int bookingId,int userId) throws BookingException {
		return dao.viewBookingStatus(bookingId,userId);
	}

	@Override
	public List<RoomBean> searchAvailableRooms(String city) throws BookingException {
		return dao.searchAvailableRooms(city);
	}
	
}
