package com.capgemini.hotelbooking.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.capgemini.hotelbooking.bean.BookingBean;
import com.capgemini.hotelbooking.bean.RoomBean;
import com.capgemini.hotelbooking.bean.UserBean;
import com.capgemini.hotelbooking.exception.BookingException;
import com.capgemini.hotelbooking.util.ConnectionUtil;

public class CustomerDao implements ICustomerDao {
	private Connection connect;
	static Logger myLogger = Logger.getLogger("myLogger");
	
	public CustomerDao() throws BookingException{
		super();
		ConnectionUtil util = new ConnectionUtil();
		connect = util.getConnection();
		myLogger.info("Connection procured in CustomerDao.");
	}
	
	private int getBookingID(){
		int bookingId = 0;
		String query = "SELECT booking_id_seq.NEXTVAL FROM DUAL";
		try
		{
			PreparedStatement pstmt= connect.prepareStatement(query);
			myLogger.info("Query Execution : " + query);
			ResultSet resultSet = pstmt.executeQuery();
			if(resultSet.next())
			{
				bookingId = resultSet.getInt(1);
			}
		}
		catch(SQLException e)
		{
			myLogger.error("Unable to generate booking ID.");
		}
		return bookingId;
	}
	
	private int getUserID(){
		int userId = 0;
		String query = "SELECT user_id_seq.NEXTVAL FROM DUAL";
		try
		{
			PreparedStatement preparedStatement = connect.prepareStatement(query);
			myLogger.info("Query Execution : " + query);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				userId = resultSet.getInt(1);
			}
		}
		catch(SQLException e)
		{
			myLogger.error("Unable to generate user ID.");
		}
		return userId;
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
	public int registerUser(UserBean userBean) throws BookingException {
		myLogger.info("Execution in registerUser()");
		
		String query = "insert into users(user_id, password, role, user_name, mobile_no, phone, address, email)"
						+ "values (?, ?, 'customer', ?, ?, ?, ?, ?)";
		int recsAffected = 0;
		
		try(
			PreparedStatement preparedStatement = connect.prepareStatement(query);
		){
			userBean.setUserID(getUserID());
			preparedStatement.setInt(1, userBean.getUserID());
			preparedStatement.setString(2, generatePasswordHash(userBean.getPassword()));
			preparedStatement.setString(3,userBean.getUserName());
			preparedStatement.setString(4, userBean.getMobileNumber());
			preparedStatement.setString(5, userBean.getPhoneNumber());
			preparedStatement.setString(6, userBean.getAddress());
			preparedStatement.setString(7, userBean.getEmail());
						
			myLogger.info("Query Execution : " + query);
			recsAffected = preparedStatement.executeUpdate();
			
			if(recsAffected > 0){
				myLogger.info("New Entry -> User ID : "+ userBean.getUserID()
									+ "\nPassword Hash: " + generatePasswordHash(userBean.getPassword())
									+ "\nRole : " + userBean.getRole()
									+ "\nUser Name : " + userBean.getUserName()
									+ "\nMobile Number : " + userBean.getMobileNumber()
									+ "\nPhone Number : " + userBean.getPhoneNumber()
									+ "\nAddress : " + userBean.getAddress()
									+ "\nEmail : " + userBean.getEmail());
			}
			else{
				myLogger.error("System Error");
				throw new BookingException("System Error. Try Again Later.");
			}
			
		} catch (SQLException e) {
			myLogger.error("Exception from registerUser()", e);
			throw new BookingException("Problem in registering user.", e);
		}
		return userBean.getUserID();
	}

	

	@Override
	public int bookRoom(BookingBean bookingBean) throws BookingException {
		//TODO Change the query and function
		myLogger.info("Execution in bookRoom()");
		
		String query = "insert into BOOKINGDETAILS(BOOKING_ID, ROOM_ID, USER_ID, BOOKED_FROM, BOOKED_TO, NO_OF_ADULTS, NO_OF_CHILDREN, "
						+ "AMOUNT) values (?, ?, ?, ?, ?, ?, ?, ?)";
		int recsAffected = 0;
		
		try(
			PreparedStatement preparedStatement = connect.prepareStatement(query);
		){
			bookingBean.setBookingID(getBookingID());
			preparedStatement.setInt(1, bookingBean.getBookingID());
			preparedStatement.setInt(2, bookingBean.getRoomID());
			preparedStatement.setInt(3, bookingBean.getUserID());
			preparedStatement.setDate(4, Date.valueOf(bookingBean.getBookedFrom()));
			preparedStatement.setDate(5, Date.valueOf(bookingBean.getBookedTo()));
			preparedStatement.setInt(6, bookingBean.getNumAdults());
			preparedStatement.setInt(7, bookingBean.getNumChildren());
			preparedStatement.setFloat(8, bookingBean.getAmount());
						
			myLogger.info("Query Execution : " + query);
			recsAffected = preparedStatement.executeUpdate();
			
			if(recsAffected > 0){
				myLogger.info("New Entry -> Booking ID : "+ bookingBean.getBookingID()
							+ "\nRoom ID : " + bookingBean.getRoomID()
							+ "\nUser ID : " + bookingBean.getUserID()
							+ "\nBooked From Date : " + bookingBean.getBookedFrom()
							+ "\nBooked To Date : " + bookingBean.getBookedTo()
							+ "\nNumber of adults : " + bookingBean.getNumAdults()
							+ "\nNumber of children : " + bookingBean.getNumChildren()
							+ "\nAmount : " + bookingBean.getAmount());
			}
			else{
				myLogger.error("System Error");
				throw new BookingException("System Error. Try Again Later.");
			}
			
		} catch (SQLException e) {
			myLogger.error("Exception from bookRoom()", e);
			throw new BookingException("Problem in booking room.", e);
		}
		return bookingBean.getBookingID();
	}

	@Override  
	public BookingBean viewBookingStatus(int bookingId) throws BookingException {
		//TODO Change the query and function
		BookingBean bookingBean = null;
		myLogger.info("Execution in viewBookingStatus()");
		
		String query = "SELECT * FROM bookingdetails WHERE bookingid = ?";
		ResultSet resultSet = null;
		
		try(
			PreparedStatement preparedStatement = connect.prepareStatement(query);
		){
			preparedStatement.setInt(1, bookingId);		
			myLogger.info("Query Execution : " + query);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				return bookingBean;
			}
			else{
				myLogger.error("System Error");
				throw new BookingException("System Error. Try Again Later.");
			}
		} catch (SQLException e) {
			myLogger.error("Exception from viewBookingStatus()", e);
			throw new BookingException("Problem in retrieving booking status.", e);
		}
	}

	@Override
	public List<RoomBean> searchAvailableRooms(String city) throws BookingException {
		//TODO change the query and function
		List<RoomBean> roomList = new ArrayList<RoomBean>();
		myLogger.info("Execution in searchAvailableRooms()");
		
		ResultSet resultSet=null;
		String query = "SELECT * FROM roomdetails where availability='T' and hotel_id in (select hotel_id from hotels where city= ?) ";
		try(
			PreparedStatement preparedStatement = connect.prepareStatement(query);
				
		){
			preparedStatement.setString(1, city); 
			resultSet = preparedStatement.executeQuery();
			myLogger.info("Query Execution : " + query);
			
			while(resultSet.next()){
				int roomId = resultSet.getInt("room_Id");
				int hotelId = resultSet.getInt("hotel_Id");
				String roomNumber = resultSet.getString("room_No");
				String roomType = resultSet.getString("room_type");
				float perNightRate = resultSet.getFloat("per_night_rate");
				String availabilityString = resultSet.getString("availability");
				String photo = resultSet.getString("photo");
				
				boolean availability = false;
				if(availabilityString.equals('T')){
					availability = true;
				}
				else{
					availability = false;
				}
				
				roomList.add(new RoomBean(hotelId, roomId, roomNumber, roomType, perNightRate, availability, photo));
			}
		} catch (SQLException e) {
			myLogger.error("Exception from searchAvailableRooms()", e);
			throw new BookingException("Problem in retrieving data.", e);
		}
		return roomList;
	}

}
