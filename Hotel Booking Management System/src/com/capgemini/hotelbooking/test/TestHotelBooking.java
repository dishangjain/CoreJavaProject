package com.capgemini.hotelbooking.test;

import static org.junit.Assert.*;

import java.time.LocalDate;

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

	/*@Test			//Test to Pass 
	public void testAddBookingDetailsPass(){
		//Initialize the variables.
		int bookingID;
		int roomID;
		int userID;
		int numAdults;
		int numChildren;
		float amount;
		LocalDate bookedFrom;
		LocalDate bookedTo;
		BookingBean bookingBean = new BookingBean(10000, roomID, userID, numAdults, numChildren, amount, bookedFrom, bookedTo);
		try {
			bookingID=service.bookRoom(bookingBean);
			assertEquals(0000, bookingID); //Enter the correct value here.
		} catch (BookingException e) {
			
			fail(e.getMessage());
		}
	}
	
	@Test			//Test to Fail
	public void testAddBookingDetailsFail(){	
		//Initialize the variables.
		int bookingID;
		int roomID;
		int userID;
		int numAdults;
		int numChildren;
		float amount;
		LocalDate bookedFrom;
		LocalDate bookedTo;
		BookingBean bookingBean = new BookingBean(10000, roomID, userID, numAdults, numChildren, amount, bookedFrom, bookedTo);
		try {
			bookingID=service.bookRoom(bookingBean);
			assertEquals(0000, bookingID); //Enter the wrong value here.
		} catch (BookingException e) {
			
			fail(e.getMessage());
		}
	}
	@Test 		//Test to pass
    public void testAddHotelDetailsPass(){
		
		// Initialize the variables
		int hotelID;
		String city;
		String hotelName;
		String address;
		String description;
		float avgRatePerNight;
		String phoneNumber1;
		String phoneNumber2;
		String rating;
		String email;
		String fax;
		HotelBean hotelBean = new HotelBean(hotelID, city, hotelName, address, description, avgRatePerNight, phoneNumber1, phoneNumber2, rating, email, fax);
		try {
			hotelID = adminService.addHotelDetails(hotelBean);
			assertEquals(0000, hotelID);  //Enter correct value to be checked
		} catch (BookingException e) {
			fail(e.getMessage());
		}
	}
	@Test		//Test to fail
	public void testAddHotelDetailsfail(){
		// Initialize the variables
				int hotelID;
				String city;
				String hotelName;
				String address;
				String description;
				float avgRatePerNight;
				String phoneNumber1;
				String phoneNumber2;
				String rating;
				String email;
				String fax;
				HotelBean hotelBean = new HotelBean(hotelID, city, hotelName, address, description, avgRatePerNight, phoneNumber1, phoneNumber2, rating, email, fax);
				try {
					hotelID = adminService.addHotelDetails(hotelBean);
					assertEquals(0000, hotelID);  //Enter wrong value to be checked
				} catch (BookingException e) {
					fail(e.getMessage());
				}
	}*/
	
}
