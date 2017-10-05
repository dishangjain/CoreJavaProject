package com.capgemini.hotelbooking.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDate;

import org.junit.Test;

import com.capgemini.hotelbooking.bean.BookingBean;
import com.capgemini.hotelbooking.exception.BookingException;
import com.capgemini.hotelbooking.service.CustomerService;
import com.capgemini.hotelbooking.service.ICustomerService;

public class TestTransportTruck{
	ICustomerService service;
	
	public TestTransportTruck() throws BookingException {
		super();
		service = new CustomerService();
	}

	@Test			//Test to Pass 
	public void testAddDetailsPass() 
	{
		LocalDate date = LocalDate.of(2017, 12, 12);
		long phone = Long.parseLong("7721849377");
		BookingBean bookingBean = new BookingBean("A111111",phone,1000,2,date);
		int bookingId;
		try 
		{
			bookingId = service.bookTrucks(bookingBean);
			assertEquals(1013, bookingId);			//add Next Value of sequence
		}
		catch (BookingException e) 
		{
			fail(e.getMessage());
		}
		
		
	}
	
	@Test			//Test to Fail
	public void testAddDetailsFail() 
	{
		LocalDate date = LocalDate.of(2017, 12, 12);
		long phone = Long.parseLong("7721849377");
		BookingBean bookingBean = new BookingBean("A111111",phone,1000,2,date);
		int bookingId;
		try 
		{
			bookingId = service.bookTrucks(bookingBean);
			assertEquals(0, bookingId);					//Patient Id is  always generated. So test will fail!
		} 
		catch (BookingException e) 
		{
			fail(e.getMessage());
		}
	}
}
