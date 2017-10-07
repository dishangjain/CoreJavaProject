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
import com.capgemini.hotelbooking.bean.RoomBean;
import com.capgemini.hotelbooking.bean.UserBean;
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
	public UserBean login(String username, String password)
			throws BookingException {
		myLogger.info("Execution in Login()");
		String hashedPassword = generatePasswordHash(password);
		String query = "SELECT * FROM users WHERE user_name=? AND password=?";
		ResultSet resultSet = null;
		UserBean userBean = null;
		try(
			PreparedStatement preparedStatement = connect.prepareStatement(query);
		){
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, hashedPassword);
			myLogger.info("Query Execution : " + query);
			resultSet = preparedStatement.executeQuery(); 
			
			while(resultSet.next()){
				int userId = resultSet.getInt("user_id");
				String role = resultSet.getString("role");
				String mobileNumber = resultSet.getString("mobile_no");
				String phoneNumber = resultSet.getString("phone");
				String address = resultSet.getString("address");
				String email = resultSet.getString("email");
				
				userBean = new UserBean(userId, hashedPassword, role, username, mobileNumber, address, email, phoneNumber);
			}
			
		} catch (SQLException e) {
			myLogger.error("Exception from Login()", e);
			throw new BookingException("Problem in Login in the user.", e);
		}
		return userBean;
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
				int hotelID = resultSet.getInt("HOTEL_ID");
				String city = resultSet.getString("CITY");
				String hotelName = resultSet.getString("HOTEL_NAME");
				String address = resultSet.getString("ADDRESS");
				String description = resultSet.getString("DESCRIPTION");
				float avgRatePerNight = resultSet.getFloat("AVG_RATE_PER_NIGHT");
				String phoneNumber1 = resultSet.getString("PHONE_NO1");
				String phoneNumber2 = resultSet.getString("PHONE_NO2");
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
	
	@Override
	public List<RoomBean> retrieveRooms() throws BookingException {
		List<RoomBean> roomList = new ArrayList<RoomBean>();
		myLogger.info("Execution in retrieveRooms()");
		String query = "SELECT * FROM roomdetails";
		try(
			Statement statement = connect.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
		){
			myLogger.info("Query Execution : " + query);
			while(resultSet.next()){
				int hotelID = resultSet.getInt("HOTEL_ID");
				int roomID = resultSet.getInt("ROOM_ID");
				String roomNumber = resultSet.getString("ROOM_NO");
				String roomType = resultSet.getString("ROOM_TYPE");
				float perNightRate = resultSet.getFloat("PER_NIGHT_RATE");
				String availabilityString = resultSet.getString("AVAILABILITY");
				String photo = resultSet.getString("PHOTO");
				boolean availability = false;
				if(availabilityString == "T"){
					availability = true;
				}
				else{
					availability = false;
				}
				
				roomList.add(new RoomBean(hotelID, roomID, roomNumber, roomType, perNightRate, availability, photo));
			}
		} catch (SQLException e) {
			myLogger.error("Exception from retrieveHotels()", e);
			throw new BookingException("Problem in retrieving data.", e);
		}
		return roomList;
	}
}
