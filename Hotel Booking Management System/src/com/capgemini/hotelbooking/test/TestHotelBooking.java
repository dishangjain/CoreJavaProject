package com.capgemini.hotelbooking.test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;
import com.capgemini.hotelbooking.bean.BookingBean;
import com.capgemini.hotelbooking.bean.HotelBean;
import com.capgemini.hotelbooking.exception.BookingException;
import com.capgemini.hotelbooking.service.AdminService;
import com.capgemini.hotelbooking.service.CustomerService;
import com.capgemini.hotelbooking.service.IAdminService;
import com.capgemini.hotelbooking.service.ICustomerService;

public class TestHotelBooking{
	ICustomerService service;
	IAdminService adminService;
	
	public TestHotelBooking() throws BookingException {
		super();
		service = new CustomerService();
		adminService = new AdminService();
	}

	@Test			
	public void testAddBookingDetailsPass(){
		
		int bookingID;
		int roomID=1;
		int userID=1;
		int numAdults=1;
		int numChildren=0;
		float amount=9000;
		LocalDate bookedFrom= LocalDate.of(2011, Month.JULY, 13);
		LocalDate bookedTo= LocalDate.of(2011,Month.JULY , 15);
		BookingBean bookingBean = new BookingBean(7, roomID, userID, numAdults, numChildren, amount, bookedFrom, bookedTo);
		try {
			bookingID=service.bookRoom(bookingBean);
			assertEquals(7, bookingID); 
		} catch (BookingException e) {
			
			fail(e.getMessage());
		}
	}
	
	@Test			
	public void testAddBookingDetailsFail(){	
		
		int bookingID;
		int roomID=2;
		int userID=1;
		int numAdults=1;
		int numChildren=0;
		float amount=9000;
		LocalDate bookedFrom= LocalDate.of(2011, Month.JULY, 13);;
		LocalDate bookedTo=LocalDate.of(2011,Month.JULY , 15);;
		BookingBean bookingBean = new BookingBean(0, roomID, userID, numAdults, numChildren, amount, bookedFrom, bookedTo);
		try {
			bookingID=service.bookRoom(bookingBean);
			assertEquals(0, bookingID); 
		} catch (BookingException e) {
			
			fail(e.getMessage());
		}
	}
	@Test 		
    public void testAddHotelDetailsPass(){
		
		int hotelID;
		String city="Pune";
		String hotelName="Oberoi";
		String address="anywhere";
		String description="nice";
		float avgRatePerNight=2000;
		String phoneNumber1="7891234560";
		String phoneNumber2="8991234560";
		String rating="4.6";
		String email="welcome@oberoi.com";
		String fax="123456";
		HotelBean hotelBean = new HotelBean(4, city, hotelName, address, description, avgRatePerNight, phoneNumber1, phoneNumber2, rating, email, fax);
		try {
			hotelID = adminService.addHotelDetails(hotelBean);
			assertEquals(4, hotelID);  
		} catch (BookingException e) {
			fail(e.getMessage());
		}
	}
	@Test		
	public void testAddHotelDetailsfail(){
		
				int hotelID;
				String city="Delhi";
				String hotelName="marriot";
				String address="anywhere";
				String description="cosly";
				float avgRatePerNight=1500;
				String phoneNumber1="9001234567";
				String phoneNumber2="8001234567";
				String rating="5";
				String email="helloguests@gmail.com";
				String fax="654321";
				HotelBean hotelBean = new HotelBean(0, city, hotelName, address, description, avgRatePerNight, phoneNumber1, phoneNumber2, rating, email, fax);
				try {
					hotelID = adminService.addHotelDetails(hotelBean);
					assertEquals(0, hotelID);  
				} catch (BookingException e) {
					fail(e.getMessage());
				}
	}
	
}
