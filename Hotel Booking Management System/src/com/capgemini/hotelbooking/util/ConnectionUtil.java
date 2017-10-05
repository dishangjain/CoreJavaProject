package com.capgemini.hotelbooking.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.capgemini.hotelbooking.exception.BookingException;

public class ConnectionUtil {
	private Connection connect;
	public ConnectionUtil() throws BookingException{
		PropertyUtil propertyServices = new PropertyUtil();
		String url = propertyServices.getPropertyValue("url");
		String userName = propertyServices.getPropertyValue("username");
		String password = propertyServices.getPropertyValue("password");
		
		try {
			connect = DriverManager.getConnection(url,userName,password);
		} catch (SQLException e) {
			throw new BookingException("Connection opening failed.", e);
		}
	}
	
	public Connection getConnection(){
		return connect;
	}
}
