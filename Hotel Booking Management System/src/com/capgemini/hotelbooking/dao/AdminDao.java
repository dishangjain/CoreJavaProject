package com.capgemini.hotelbooking.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.capgemini.hotelbooking.bean.BookingBean;
import com.capgemini.hotelbooking.bean.HotelBean;
import com.capgemini.hotelbooking.bean.RoomBean;
import com.capgemini.hotelbooking.bean.UserBean;
import com.capgemini.hotelbooking.exception.BookingException;
import com.capgemini.hotelbooking.util.ConnectionUtil;

public class AdminDao implements IAdminDao {
	private Connection connect;
	static Logger myLogger = Logger.getLogger("myLogger");
	
	public AdminDao() throws BookingException{
		super();
		ConnectionUtil util = new ConnectionUtil();
		connect = util.getConnection();
		myLogger.info("Connection procured in AdminDao.");
	}
	
	private int getHotelID(){
		int hotelId = 0;
		String query = "SELECT hotel_id_seq.NEXTVAL FROM DUAL";
		try
		{
			PreparedStatement preparedStatement= connect.prepareStatement(query);
			myLogger.info("Query Execution : " + query);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				hotelId = resultSet.getInt(1);
			}
		}
		catch(SQLException e)
		{
			myLogger.error("Unable to generate hotel ID.");
		}
		return hotelId;
	}
	
	private int getRoomID() {
		int roomId = 0;
		String query = "SELECT room_id_seq.NEXTVAL FROM DUAL";
		try
		{
			PreparedStatement preparedStatement = connect.prepareStatement(query);
			myLogger.info("Query Execution : " + query);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				roomId = resultSet.getInt(1);
			}
		}
		catch(SQLException e)
		{
			myLogger.error("Unable to generate room ID.");
		}
		return roomId;
	}
	
	@Override
	public int addRoomDetails(RoomBean roomBean) throws BookingException {
		myLogger.info("Execution in addRoomDetails()");
		
		String query = "insert into ROOMDETAILS(ROOM_ID, HOTEL_ID, ROOM_NO, ROOM_TYPE, PER_NIGHT_RATE, AVAILABILITY, PHOTO)"
						+ "values (?, ?, ?, ?, ?, 'T', ?)";
		int recsAffected = 0;
		
		try(
			PreparedStatement preparedStatement = connect.prepareStatement(query);
		){
			roomBean.setRoomID(getRoomID());
			preparedStatement.setInt(1, roomBean.getRoomID());
			preparedStatement.setInt(2, roomBean.getHotelID());
			preparedStatement.setString(3, roomBean.getRoomNumber());
			preparedStatement.setString(4, roomBean.getRoomType());
			preparedStatement.setFloat(5, roomBean.getPerNightRate());
			preparedStatement.setString(6, roomBean.getPhoto());
						
			myLogger.info("Query Execution : " + query);
			recsAffected = preparedStatement.executeUpdate();
			
			if(recsAffected > 0){
				myLogger.info("New Entry -> Room ID : "+ roomBean.getRoomID()
									+ "\nHotel ID : " + roomBean.getHotelID()
									+ "\nRoom Number: " + roomBean.getRoomNumber()
									+ "\nRoom Type : " + roomBean.getRoomType()
									+ "\nPer Night Rate : " + roomBean.getPerNightRate()
									+ "\nAvailability : " + roomBean.isAvailable()
									+ "\nPhoto : " + roomBean.getPhoto());
			}
			else{
				myLogger.error("System Error");
				throw new BookingException("System Error. Try Again Later.");
			}
			
		} catch (SQLException e) {
			myLogger.error("Exception from addRoomDetails()", e);
			throw new BookingException("Problem in adding data.", e);
		}
		return roomBean.getRoomID();
	}

	@Override
	public int addHotelDetails(HotelBean hotelBean) throws BookingException {
		myLogger.info("Execution in addHotelDetails()");
		
		String query = "insert into HOTELS(HOTEL_ID, CITY, HOTEL_NAME, ADDRESS, DESCRIPTION, AVG_RATE_PER_NIGHT, PHONE_NO1,"
						+ "PHONE_NO2, RATING, EMAIL, FAX) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int recsAffected = 0;
		
		try(
			PreparedStatement preparedStatement = connect.prepareStatement(query);
		){
			hotelBean.setHotelID(getHotelID());
			preparedStatement.setInt(1, hotelBean.getHotelID());
			preparedStatement.setString(2, hotelBean.getCity());
			preparedStatement.setString(3, hotelBean.getHotelName());
			preparedStatement.setString(4, hotelBean.getAddress());
			preparedStatement.setString(5, hotelBean.getDescription());
			preparedStatement.setFloat(6, hotelBean.getAvgRatePerNight());
			preparedStatement.setString(7, hotelBean.getPhoneNumber1());
			preparedStatement.setString(8, hotelBean.getPhoneNumber2());
			preparedStatement.setString(9, hotelBean.getRating());
			preparedStatement.setString(10, hotelBean.getEmail());
			preparedStatement.setString(11, hotelBean.getFax());
						
			myLogger.info("Query Execution : " + query);
			recsAffected = preparedStatement.executeUpdate();
			
			if(recsAffected > 0){
				myLogger.info("New Entry -> Hotel ID : "+ hotelBean.getHotelID()
									+ "\nCity : " + hotelBean.getCity()
									+ "\nHotel Name : " + hotelBean.getHotelName()
									+ "\nAddress : " + hotelBean.getAddress()
									+ "\nDescription : " + hotelBean.getDescription()
									+ "\nAverage Rate per Night : " + hotelBean.getAvgRatePerNight()
									+ "\nPhone Number 1 : " + hotelBean.getPhoneNumber1()
									+ "\nPhone Number 2 : " + hotelBean.getPhoneNumber2()
									+ "\nRating : " + hotelBean.getRating()
									+ "\nFax : " + hotelBean.getFax());
			}
			else{
				myLogger.error("System Error");
				throw new BookingException("System Error. Try Again Later.");
			}
			
		} catch (SQLException e) {
			myLogger.error("Exception from addHotelDetails()", e);
			throw new BookingException("Problem in adding data.", e);
		}
		return hotelBean.getHotelID();
	}

	@Override
	public int updateHotelDetails(int hotelID, String attributeName, String attributeValue) 
			throws BookingException {
		myLogger.info("Execution in updateHotelDetails()");
		String query = "UPDATE hotels SET " + attributeName + "=? WHERE HOTEL_ID=?";
		int recsAffected = 0;
		
		try(
			PreparedStatement preparedStatement = connect.prepareStatement(query);
		){
			preparedStatement.setString(1, attributeValue);
			preparedStatement.setInt(2, hotelID);
						
			myLogger.info("Query Execution : " + query);
			recsAffected = preparedStatement.executeUpdate();
			if(recsAffected > 0){
				myLogger.info("Hotel Table Updated."
							+ "\nHotel ID : " + hotelID
							+ "\nColumn Name : " + attributeName
							+ "Column Value : " + attributeValue);
			}
			else{
				myLogger.error("System Error");
				throw new BookingException("System Error. Try Again Later.");
				
			}
			
		} catch (SQLException e) {
			myLogger.error("Exception from updateHotelDetails()", e);
			throw new BookingException("Problem in updating data.", e);
		}
		return recsAffected;
	}

	@Override
	public int updateRoomDetails(int roomID, String attributeName,
			String attributeValue) throws BookingException {
		myLogger.info("Execution in updateRoomDetails()");
		
		String query = "UPDATE roomdetails SET " +attributeName+"=? WHERE room_ID=?";
		int recsAffected = 0;
		
		try(
			PreparedStatement preparedStatement = connect.prepareStatement(query);
		){
			
			preparedStatement.setString(1, attributeValue);
			preparedStatement.setInt(2, roomID);
						
			myLogger.info("Query Execution : " + query);
			recsAffected = preparedStatement.executeUpdate();
			
			if(recsAffected > 0){
				myLogger.info("Room Table Updated."
						+ "\nRoom ID : " + roomID
						+ "\nColumn Name : " + attributeName
						+ "Column Value : " + attributeValue);
			}
			else{
				myLogger.error("System Error");
				throw new BookingException("System Error. Try Again Later.");
				
			}
			
		} catch (SQLException e) {
			myLogger.error("Exception from updateRoomDetails()", e);
			throw new BookingException("Problem in updating data.", e);
		}
		return recsAffected;
	}

	@Override
	public List<BookingBean> viewBookingsOfHotel(int hotelID) throws BookingException {
		//TODO Change the query and function
		List<BookingBean> bookingList = new ArrayList<BookingBean>();
		myLogger.info("Execution in getBookingsOfHotel()");
		String query = "SELECT * FROM bookingdetails WHERE hotelID = ?";
		ResultSet resultSet = null;
		
		try(
			PreparedStatement preparedStatement = connect.prepareStatement(query);
		){
			preparedStatement.setInt(1, hotelID);
			myLogger.info("Query Execution : " + query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				int bookingID = Integer.parseInt(resultSet.getString("BOOKINGID"));
				int userID = resultSet.getInt("USERID");
				int roomID = resultSet.getInt("ROOMID");
				int numAdults = resultSet.getInt("NUMADULTS");
				int numChildren = resultSet.getInt("NUMCHILDREN");
				float amount = resultSet.getFloat("AMOUNT");
				LocalDate bookedFrom = resultSet.getDate("BOOKEDFROM").toLocalDate();
				LocalDate bookedTo = resultSet.getDate("BOOKEDTO").toLocalDate();
				
				bookingList.add(new BookingBean(bookingID, roomID, userID, numAdults, numChildren, amount, bookedFrom, bookedTo));
			}
			
		} catch (SQLException e) {
			myLogger.error("Exception from getBookingsOfHotel()", e);
			throw new BookingException("Problem in retrieving data.", e);
		}
		return bookingList;
	}

	@Override
	public List<BookingBean> viewBookingsOfDate(LocalDate localDate) throws BookingException {
		//TODO Change the query and function
		List<BookingBean> bookingList = new ArrayList<BookingBean>();
		myLogger.info("Execution in getBookingsOfDate()");
		String query = "SELECT * FROM bookingdetails WHERE bookedFrom = TO_DATE(?)";
		ResultSet resultSet = null;
		
		try(
			PreparedStatement preparedStatement = connect.prepareStatement(query);
		){
			Date sqlDate = Date.valueOf(localDate);
			preparedStatement.setDate(1, sqlDate);
						
			myLogger.info("Query Execution : " + query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				int bookingID = resultSet.getInt("BOOKINGID");
				int userID = resultSet.getInt("USERID");
				int roomID = resultSet.getInt("ROOMID");
				int numAdults = resultSet.getInt("NUMADULTS");
				int numChildren = resultSet.getInt("NUMCHILDREN");
				float amount = resultSet.getFloat("AMOUNT");
				LocalDate bookedFrom = resultSet.getDate("BOOKEDFROM").toLocalDate();
				LocalDate bookedTo = resultSet.getDate("BOOKEDTO").toLocalDate();
				
				bookingList.add(new BookingBean(bookingID, roomID, userID, numAdults, numChildren, amount, bookedFrom, bookedTo));
			}
			
		} catch (SQLException e) {
			myLogger.error("Exception from getBookingsOfDate()", e);
			throw new BookingException("Problem in retrieving data.", e);
		}
		return bookingList;
	}

	@Override
	public boolean deleteHotelDetails(int hotelID) throws BookingException {
		myLogger.info("Execution in deleteHotelDetails()");
		
		String query = "DELETE FROM hotels WHERE hotel_ID=?";
		int recsAffected = 0;
		
		try(
			PreparedStatement preparedStatement = connect.prepareStatement(query);
		){
			preparedStatement.setInt(1, hotelID);
						
			myLogger.info("Query Execution : " + query);
			recsAffected = preparedStatement.executeUpdate();
			
			if(recsAffected > 0){
				myLogger.info("1 row deleted from hotels."
						+ "\nHotel ID of deleted row : " + hotelID);
			}
			else{
				myLogger.error("System Error");
				throw new BookingException("System Error. Try Again Later.");
			}
			
		} catch (SQLException e) {
			myLogger.error("Exception from deleteHotelDetails()", e);
			throw new BookingException("Problem in deleting data.", e);
		}
		return true;
	}

	@Override
	public boolean deleteRoomDetails(int roomID) throws BookingException {
		myLogger.info("Execution in deletRoomDetails()");
		
		String query = "DELETE FROM roomdetails WHERE room_ID=?";
		int recsAffected = 0;
		
		try(
			PreparedStatement preparedStatement = connect.prepareStatement(query);
		){
			preparedStatement.setInt(1, roomID);
						
			myLogger.info("Query Execution : " + query);
			recsAffected = preparedStatement.executeUpdate();
			
			if(recsAffected > 0){
				myLogger.info("1 row deleted from roomdetails."
						+ "\nRoom ID of deleted row : " + roomID);
			}
			else{
				myLogger.error("System Error");
				throw new BookingException("System Error. Try Again Later.");
				
			}
			
		} catch (SQLException e) {
			myLogger.error("Exception from deleteRoomDetails()", e);
			throw new BookingException("Problem in deleting data.", e);
		}
		return true;
	}

	@Override
	public List<UserBean> viewGuestList() throws BookingException {
		// TODO Do the implementation
		return null;
	}
}
