package com.capgemini.hotelbooking.service;

import java.time.LocalDate;
import java.util.List;

import org.apache.log4j.Logger;

import com.capgemini.hotelbooking.bean.BookingBean;
import com.capgemini.hotelbooking.bean.HotelBean;
import com.capgemini.hotelbooking.bean.RoomBean;
import com.capgemini.hotelbooking.dao.AdminDao;
import com.capgemini.hotelbooking.dao.IAdminDao;
import com.capgemini.hotelbooking.exception.BookingException;

public class AdminService implements IAdminService{
	private IAdminDao dao;
	private static String customerIDPattern = "[A-Z]{1}[0-9]{6}";
	private static String contactPattern = "[0-9]{10}";
	private static String datePattern = "[0-9]{4}[-][0-9]{2}[-][0-9]{2}";
	
	static Logger myLogger = Logger.getLogger("myLogger");
	
	public AdminService() throws BookingException {
		myLogger.info("Service: Dao injected.");
		dao = new AdminDao();
	}
	
	private static boolean isGreaterThanEqual(LocalDate localDate1,LocalDate localDate2)
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
	}
	
	
	public static boolean validateCustomerID(String customerID){
		boolean flag = false;
		if(customerID.matches(customerIDPattern))
		{
			flag = true;
		}
		return flag;
	}
	
	public static boolean validateNumberOfTrucks(int neededCount, int actualCount){
		boolean flag = false;
		if(neededCount > 0 && neededCount <= actualCount)
		{
			flag = true;
		}
		return flag;
	}
	
	public static boolean validatePhoneNumber(String phoneNumber){
		boolean flag = false;
		if(phoneNumber.matches(contactPattern))
		{
			flag = true;
		}
		return flag;
	}
	
	public static boolean validateDate(String dateString){
		boolean flag = false;
		if(dateString.matches(datePattern))
		{
			flag = true;
		}
		int year = Integer.parseInt(dateString.substring(0, 4));
		int month = Integer.parseInt(dateString.substring(5, 7));
		int date = Integer.parseInt(dateString.substring(8, 10));
		if(month > 12 || month <= 0 || date > 31 || date <= 0){
			flag = false;
		}
		if(flag == true){
			flag = isGreaterThanEqual(LocalDate.of(year, month, date),LocalDate.now());
		}
		return flag;
	}

	@Override
	public int addHotelDetails(HotelBean hotelBean) throws BookingException {
		return dao.addHotelDetails(hotelBean);
	}

	@Override
	public int updateHotelDetails(int hotelID, String attributeName,
			String attributeValue) throws BookingException {
		return dao.updateHotelDetails(hotelID, attributeName, attributeValue);
	}

	@Override
	public int updateRoomDetails(int roomID, String attributeName,
			String attributeValue) throws BookingException {
		return dao.updateRoomDetails(roomID, attributeName, attributeValue);
	}

	@Override
	public List<BookingBean> viewBookingsOfHotel(int hotelID) throws BookingException {
		return dao.viewBookingsOfHotel(hotelID);
	}

	@Override
	public List<BookingBean> viewBookingsOfDate(LocalDate date) throws BookingException {
		return dao.viewBookingsOfDate(date);
	}

	@Override
	public boolean deleteHotelDetails(int hotelID) throws BookingException {
		return dao.deleteHotelDetails(hotelID);
	}

	@Override
	public int addRoomDetails(RoomBean roomBean) throws BookingException {
		return dao.addRoomDetails(roomBean);
	}

	@Override
	public boolean deleteRoomDetails(int roomID) throws BookingException {
		return dao.deleteRoomDetails(roomID);
	}
	
}
