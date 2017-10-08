package com.capgemini.hotelbooking.service;

import java.time.LocalDate;
import java.util.List;

import org.apache.log4j.Logger;

import com.capgemini.hotelbooking.bean.BookingBean;
import com.capgemini.hotelbooking.bean.HotelBean;
import com.capgemini.hotelbooking.bean.RoomBean;
import com.capgemini.hotelbooking.bean.UserBean;
import com.capgemini.hotelbooking.dao.AdminDao;
import com.capgemini.hotelbooking.dao.IAdminDao;
import com.capgemini.hotelbooking.exception.BookingException;

public class AdminService implements IAdminService{
	IAdminDao dao;
	
	private static String hotelIDPattern = "[0-9]{1,4}";
	private static String roomIDPattern = "[0-9]{1,4}";
	private static String roomNumberPattern = "[0-9]{1,3}";
	private static String perNightRatePattern = "[1-9][0-9]{2,10}";
	private static String photoPattern ="[A-Za-z 0-9]*[.](png|jpeg|gif)";
	private static String mobileNumberPattern = "[7-9][0-9]{9}";
	private static String ratingPattern = "[0-4][.][0-9]";
	private static String emailPattern = "[A-Z a-z]*[@][A-Z a-z]*[.](com|org|in|co.in)";
	private static String faxPattern = "[0-9]{6}";
	private static String cityPattern = "[A-Z][A-Z a-z]*";
	private static String dateStringPattern = "(0[1-9]|1[0-9]|2[0-9]|3[01])-(0[1-9]|1[012])-[0-9]{4}";
	
	static Logger myLogger = Logger.getLogger("myLogger");
	
	public AdminService() throws BookingException {
		myLogger.info("Service: Dao injected.");
		dao = new AdminDao();
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
	}*/
	
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
	
	public static boolean validatePerNightRate(String perNRate){
		boolean flag = false;
		if(perNRate.matches(perNightRatePattern))
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
	
	public static boolean validateMobileNumber(String primaryPhone) {
		boolean flag = false;
		if(primaryPhone.matches(mobileNumberPattern))
		{
			flag = true;
		}
		return flag;
	}
	
	public static boolean validateRating(String rating) {
		boolean flag = false;
		if(rating.matches(ratingPattern))
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
	
	public static boolean validateFax(String fax){
		boolean flag = false;
		if(fax.matches(faxPattern))
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
		int date = Integer.parseInt(dateString.substring(0, 2));
		int month = Integer.parseInt(dateString.substring(3, 5));
		int year = Integer.parseInt(dateString.substring(6, 10));
		if(month > 12 || month <= 0 || date > 31 || date <= 0 || year < 0){
			flag = false;
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

	@Override
	public List<UserBean> viewGuestList(int hotelID) throws BookingException {
		return dao.viewGuestList(hotelID);
	}
}
