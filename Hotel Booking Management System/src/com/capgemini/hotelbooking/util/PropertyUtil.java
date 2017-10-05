package com.capgemini.hotelbooking.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.capgemini.hotelbooking.exception.BookingException;


public class PropertyUtil {
	private Properties props;
	
	public PropertyUtil() throws BookingException{
		props = new Properties();
		
		try(FileInputStream fis = new FileInputStream("ProjectDB.Properties");){
			props.load(fis);
		} catch (IOException e) {
			throw new BookingException("Property File missing.", e);
		}
	}
	
	public String getPropertyValue(String prop){
		return props.getProperty(prop);
	}
}
