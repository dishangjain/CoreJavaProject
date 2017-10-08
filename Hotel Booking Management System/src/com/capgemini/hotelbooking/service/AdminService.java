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
	private static String hotelIDPattern = "[0-9]{1,4}";
	private static String cityPattern ="[A-Z][A-Z a-z]*";
	private static String numAdultPattern= "[0-9]";
	private static String amountPattern= "[0-9]*";
	private static String roomIDPattern = "[0-9]{1,4}";
	private static String roomNumberPattern = "[0-9]{1,3}";
	private static String roomTypePattern = "[A-Z a-z]{2,15}";
	private static String perNightRatePattern = "[1-9][0-9]{2,10}";
	private static String availablePattern = "(true|false|True|False|T|F)";
	private static String photoPattern ="[A-Za-z 0-9]*[.](png|jpeg|gif)";
	IAdminDao dao;
	
	static Logger myLogger = Logger.getLogger("myLogger");
	
	public AdminService() throws BookingException {
		myLogger.info("Service: Dao injected.");
		dao = new AdminDao();
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
	


	public static boolean validateAdults(String numAdults){
		boolean flag = false;
		if(numAdults.matches(numAdultPattern))
		{
			flag = true;
		}
		return flag;
	}
	
	public static boolean validateAmount(String amount){
		boolean flag = false;
		if(amount.matches(amountPattern))
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
	
	public static boolean validateHotelID(String hotelID){
		boolean flag = false;
		if(hotelID.matches(hotelIDPattern))
		{
			flag = true;
		}
		return flag;
	}
	
	public static boolean validateRoomID(String roomID){
		boolean flag = false;
		if(roomID.matches(roomIDPattern))
		{
			flag = true;
		}
		return flag;
	}
	
	
	public static boolean validateRoomNumber(String roomNumber){
		boolean flag = false;
		if(roomNumber.matches(roomNumberPattern))
		{
			flag = true;
		}
		return flag;
	}
	
	
	public static boolean validateRoomType(String roomType){
		boolean flag = false;
		if(roomType.matches(roomTypePattern))
		{
			flag = true;
		}
		return flag;
	}
	
	public static boolean validatePerNightRate(String perNRate){
		boolean flag = false;
		if(perNRate.matches(perNightRatePattern))
		{
			flag = true;
		}
		return flag;
	}
	
	public static boolean validateAvailable(String avail){
		boolean flag = false;
		if(avail.matches(availablePattern))
		{
			flag = true;
		}
		return flag;
	}
	
	public static boolean validatePhoto(String photo){
		boolean flag = false;
		if(photo.matches(photoPattern))
		{
			flag = true;
		}
		return flag;
	}

	
	/*public static boolean validateDate(String dateString){
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
	}*/

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
