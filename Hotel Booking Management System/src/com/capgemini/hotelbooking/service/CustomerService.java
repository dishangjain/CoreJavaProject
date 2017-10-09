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
	
	private static String dateStringPattern = "(0[1-9]|1[0-9]|2[0-9]|3[0-1])-(0[1-9]|1[012])-[0-9]{4}";
	private static String cityPattern = "[A-Z][A-Z a-z]*";
	private static String roomIDPattern = "[0-9]{1,4}";
	private static String numAdultPattern= "[0-9]{1,2}";
	private static String numChildrenPattern= "[0-9]{1,2}";

	static Logger myLogger = Logger.getLogger("myLogger");
	
	public CustomerService() throws BookingException {
		myLogger.info("Service: Dao injected.");
		dao = new CustomerDao();
	}
	
	/*private static boolean isGreaterThanEqual(LocalDate localDate1,LocalDate localDate2)
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
	
	public static boolean validateRoomID(String roomID){
		boolean flag = false;
		if(roomID.matches(roomIDPattern))
		{
			flag = true;
		}
		return flag;
	}
	
	public static boolean validateCity(String city){
		boolean flag = false;
		if(city.matches(cityPattern))
		{
			flag = true;
		}
		return flag;
	}
	
	public static boolean validateNumAdults(String numAdults){
		boolean flag = false;
		if(numAdults.matches(numAdultPattern))
		{
			flag = true;
		}
		return flag;
	}
	
	public static boolean validateNumChildren(String numChildren){
		boolean flag = false;
		if(numChildren.matches(numChildrenPattern))
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
		int year = Integer.parseInt(dateString.substring(6, 10));
		int month = Integer.parseInt(dateString.substring(3, 5));
		int date = Integer.parseInt(dateString.substring(0, 2));
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

	@Override
	public List<Integer> getBookingIDs(int userId) throws BookingException {
		return dao.getBookingIDs(userId);
	}
	
}
