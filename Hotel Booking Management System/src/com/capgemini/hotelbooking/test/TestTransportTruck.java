package com.capgemini.hotelbooking.test;

import org.junit.Test;

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
	public void testAddDetailsPass(){	
	}
	
	@Test			//Test to Fail
	public void testAddDetailsFail(){	
	}
}
