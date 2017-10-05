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
		int bookingId = 0;
		String query = "SELECT hotel_id_seq.NEXTVAL FROM DUAL";
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

	@Override
	public int addHotelDetails(HotelBean hotelBean) throws BookingException {
		myLogger.info("Execution in addHotelDetails()");
		
		String query = "insert into BOOKINGDETAILS(HOTELID, CITY, HOTELNAME, ADDRESS, DESCRIPTION, AVGPATEPERNIGHT, PHONE1,"
						+ "PHONE2, RATING, EMAIL, FAX) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int recsAffected = 0;
		
		try(
			PreparedStatement preparedStatement = connect.prepareStatement(query);
		){
			hotelBean.setHotelID(Integer.toString(getHotelID()));
			preparedStatement.setString(1, hotelBean.getHotelID());
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
			recsAffected = preparedStatement.executeUpdate(); // 1 for successful insert
			
			if(recsAffected > 0){
				//Logging the New Entry
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
		return Integer.parseInt(hotelBean.getHotelID());
	}

	@Override
	public int updateHotelDetails(String hotelID, String attributeName, String attributeValue) 
			throws BookingException {
		myLogger.info("Execution in updateHotelDetails()");
		
		String query = "UPDATE hotels SET ?=? WHERE hotelID=?";
		int recsAffected = 0;
		
		try(
			PreparedStatement preparedStatement = connect.prepareStatement(query);
		){
			preparedStatement.setString(1, attributeName);
			preparedStatement.setString(2, attributeValue);
			preparedStatement.setString(3, hotelID);
						
			myLogger.info("Query Execution : " + query);
			recsAffected = preparedStatement.executeUpdate(); // 1 for successful insert
			
			if(recsAffected > 0){
				//Logging the New Entry
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
	public int updateRoomDetails(String roomID, String attributeName,
			String attributeValue) throws BookingException {
		myLogger.info("Execution in updateRoomDetails()");
		
		String query = "UPDATE roomdetails SET ?=? WHERE roomID=?";
		int recsAffected = 0;
		
		try(
			PreparedStatement preparedStatement = connect.prepareStatement(query);
		){
			preparedStatement.setString(1, attributeName);
			preparedStatement.setString(2, attributeValue);
			preparedStatement.setString(3, roomID);
						
			myLogger.info("Query Execution : " + query);
			recsAffected = preparedStatement.executeUpdate(); // 1 for successful insert
			
			if(recsAffected > 0){
				//Logging the New Entry
				myLogger.info("Hotel Table Updated."
						+ "\nHotel ID : " + roomID
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
	public List<BookingBean> getBookingsOfHotel(String hotelID) throws BookingException {
		//TODO Change the query and function
		List<BookingBean> bookingList = new ArrayList<BookingBean>();
		myLogger.info("Execution in getBookingsOfHotel()");
		String query = "SELECT * FROM bookingdetails WHERE hotelID = ?";
		ResultSet resultSet = null;
		
		try(
			PreparedStatement preparedStatement = connect.prepareStatement(query);
		){
			preparedStatement.setString(1, hotelID);
						
			myLogger.info("Query Execution : " + query);
			resultSet = preparedStatement.executeQuery(); // 1 for successful insert
			
			while(resultSet.next()){
				int bookingID = Integer.parseInt(resultSet.getString("BOOKINGID"));
				String userID = resultSet.getString("USERID");
				String roomID = resultSet.getString("ROOMID");
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
	public List<BookingBean> getBookingsOfDate(LocalDate localDate) throws BookingException {
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
			resultSet = preparedStatement.executeQuery(); // 1 for successful insert
			
			while(resultSet.next()){
				int bookingID = Integer.parseInt(resultSet.getString("BOOKINGID"));
				String userID = resultSet.getString("USERID");
				String roomID = resultSet.getString("ROOMID");
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
}
