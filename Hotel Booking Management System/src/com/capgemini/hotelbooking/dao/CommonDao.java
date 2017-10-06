package com.capgemini.hotelbooking.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.capgemini.hotelbooking.bean.HotelBean;
import com.capgemini.hotelbooking.exception.BookingException;
import com.capgemini.hotelbooking.util.ConnectionUtil;

public class CommonDao implements ICommonDao {
	private Connection connect;
	static Logger myLogger = Logger.getLogger("myLogger");
	
	public CommonDao() throws BookingException{
		super();
		ConnectionUtil util = new ConnectionUtil();
		connect = util.getConnection();
		myLogger.info("Connection procured in CommonDao.");
	}
	
	private String generatePasswordHash(String password) throws BookingException{
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			myLogger.error("Algorithm for hash not found.");
			throw new BookingException("System Error.");
		}
		messageDigest.update(password.getBytes());
		byte[] bytes = messageDigest.digest();
		StringBuilder stringBuilder = new StringBuilder();
		for(int i=0;i < bytes.length;i++){
			stringBuilder.append(Integer.toHexString(0xff & bytes[i]));
		}
		return stringBuilder.toString();
	}
	
	@Override
	public boolean Login(String username, String password)
			throws BookingException {
		myLogger.info("Execution in Login()");
		String hashedPassword = generatePasswordHash(password);
		String query = "SELECT username,password FROM users WHERE username=? AND password=?";
		ResultSet resultSet = null;
		
		try(
			PreparedStatement preparedStatement = connect.prepareStatement(query);
		){
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, hashedPassword);
			myLogger.info("Query Execution : " + query);
			resultSet = preparedStatement.executeQuery(); 
			
			if(resultSet.next()){
				return true;
			}
			else{
				return false;
			}
			
		} catch (SQLException e) {
			myLogger.error("Exception from Login()", e);
			throw new BookingException("Problem in Login in the user.", e);
		}
	}
	
	@Override
	public List<HotelBean> retrieveHotels() throws BookingException {
		List<HotelBean> hotelList = new ArrayList<HotelBean>();
		myLogger.info("Execution in retrieveHotels()");
		String query = "SELECT * FROM hotels";
		try(
			Statement statement = connect.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
		){
			myLogger.info("Query Execution : " + query);
			while(resultSet.next()){
				String hotelID = resultSet.getString("HOTELID");
				String city = resultSet.getString("CITY");
				String hotelName = resultSet.getString("HOTELNAME");
				String address = resultSet.getString("ADDRESS");
				String description = resultSet.getString("DESCRIPTION");
				float avgRatePerNight = resultSet.getFloat("AVAILABLENOS");
				String phoneNumber1 = resultSet.getString("PHONE1");
				String phoneNumber2 = resultSet.getString("PHONE2");
				String rating = resultSet.getString("RATING");
				String email = resultSet.getString("EMAIL");
				String fax = resultSet.getString("FAX");
				
				hotelList.add(new HotelBean(hotelID, city, hotelName, address, description, avgRatePerNight, phoneNumber1, 
											phoneNumber2, rating, email, fax));
			}
		} catch (SQLException e) {
			myLogger.error("Exception from retrieveHotels()", e);
			throw new BookingException("Problem in retrieving data.", e);
		}
		return hotelList;
	}

}
